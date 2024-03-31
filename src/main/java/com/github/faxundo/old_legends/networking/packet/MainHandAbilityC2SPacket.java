package com.github.faxundo.old_legends.networking.packet;

import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningAwake;
import com.github.faxundo.old_legends.item.custom.awake.SwallowsStormAwakeItem;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class MainHandAbilityC2SPacket {

    public static void receive (MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                PacketByteBuf buf, PacketSender responseSender) {
        ItemStack item = player.getMainHandStack();
        if (item.getItem() instanceof EmeraldMourningAwake emeraldMourningAwake) {
            emeraldMourningAwake.useAbility(player);
        }
        if (item.getItem() instanceof SwallowsStormAwakeItem swallowsStormAwakeItem){
            swallowsStormAwakeItem.useAbility(player);
        }
    }
}
