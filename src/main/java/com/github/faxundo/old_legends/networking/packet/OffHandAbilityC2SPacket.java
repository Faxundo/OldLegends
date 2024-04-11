package com.github.faxundo.old_legends.networking.packet;

import com.github.faxundo.old_legends.item.custom.awake.Ability;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class OffHandAbilityC2SPacket {

    public static void receive (MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        Item item = player.getOffHandStack().getItem();
        if (item instanceof Ability abilityUser) {
            abilityUser.useAbility(player);
        }
    }
}
