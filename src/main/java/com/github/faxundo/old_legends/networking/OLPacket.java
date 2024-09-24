package com.github.faxundo.old_legends.networking;

import com.github.faxundo.old_legends.item.custom.awake.Ability;
import com.github.faxundo.old_legends.networking.packet.MainHandAbilityPacket;
import com.github.faxundo.old_legends.networking.packet.OffHandAbilityPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OLPacket {

    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(MainHandAbilityPacket.PACKET_ID, MainHandAbilityPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(OffHandAbilityPacket.PACKET_ID, OffHandAbilityPacket.CODEC);
    }

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(MainHandAbilityPacket.PACKET_ID, (payload, context) -> {
            PlayerEntity player = context.player();
            ItemStack stack = player.getMainHandStack();
            Item item = stack.getItem();
            if (item instanceof Ability abilityUser) {
                abilityUser.useAbility(player);
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(OffHandAbilityPacket.PACKET_ID, (payload, context) -> {
            PlayerEntity player = context.player();
            ItemStack stack = player.getOffHandStack();
            Item item = stack.getItem();
            if (item instanceof Ability abilityUser) {
                abilityUser.useAbility(player);
            }
        });
    }

    public static void registerS2CPackets() {

    }
}

