package com.github.faxundo.old_legends.networking.packet;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record MainHandAbilityPacket(boolean pressKey) implements CustomPayload {

    public static final CustomPayload.Id<MainHandAbilityPacket> PACKET_ID = new CustomPayload.Id<>(
            OldLegends.identifier("main_hand_ability"));

    public static final PacketCodec<RegistryByteBuf, MainHandAbilityPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, MainHandAbilityPacket::pressKey, MainHandAbilityPacket::new);

    public static boolean canSend() {
        return ClientPlayNetworking.canSend(PACKET_ID);
    }

    public void send() {
        ClientPlayNetworking.send(this);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
