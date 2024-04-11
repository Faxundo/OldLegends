package com.github.faxundo.old_legends.networking;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.networking.packet.MainHandAbilityC2SPacket;
import com.github.faxundo.old_legends.networking.packet.OffHandAbilityC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class OLPacket {

    public static final Identifier MAIN_HAND_ABILITY = new Identifier(OldLegends.MOD_ID, "main_hand_ability");
    public static final Identifier OFF_HAND_ABILITY = new Identifier(OldLegends.MOD_ID, "off_hand_ability");


    public static void registerC2SPackets () {
        ServerPlayNetworking.registerGlobalReceiver(MAIN_HAND_ABILITY, MainHandAbilityC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(OFF_HAND_ABILITY, OffHandAbilityC2SPacket::receive);
    }

    public static void registerS2CPackets () {

    }
}

