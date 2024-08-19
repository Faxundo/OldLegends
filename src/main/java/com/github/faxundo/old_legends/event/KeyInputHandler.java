package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.networking.packet.MainHandAbilityC2SPacket;
import com.github.faxundo.old_legends.networking.packet.OffHandAbilityC2SPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static final String OL_KEY_CATEGORY = "key.category.old_legends";
    public static final String MAIN_HAND_ABILITY = "key.old_legends.main_hand_ability";
    public static final String OFF_HAND_ABILITY = "key.old_legends.off_hand_ability";
    public static KeyBinding mainAbilityKey;
    public static KeyBinding offAbilityKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (mainAbilityKey.wasPressed() && MainHandAbilityC2SPacket.canSend()) {
                new MainHandAbilityC2SPacket(true).send();
            }
            if (offAbilityKey.wasPressed() && OffHandAbilityC2SPacket.canSend()) {
                new OffHandAbilityC2SPacket(true).send();
            }
        });
    }

    public static void register() {
        mainAbilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                MAIN_HAND_ABILITY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z,
                OL_KEY_CATEGORY
        ));
        offAbilityKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                OFF_HAND_ABILITY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                OL_KEY_CATEGORY
        ));
        registerKeyInputs();
    }

}
