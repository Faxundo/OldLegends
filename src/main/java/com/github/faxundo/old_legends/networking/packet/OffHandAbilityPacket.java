package com.github.faxundo.old_legends.networking.packet;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record OffHandAbilityPacket(boolean pressKey) implements CustomPayload {


    public static final CustomPayload.Id<OffHandAbilityPacket> PACKET_ID = new CustomPayload.Id<>(
            OldLegends.identifier("off_hand_ability"));

    public static final PacketCodec<RegistryByteBuf, OffHandAbilityPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, OffHandAbilityPacket::pressKey, OffHandAbilityPacket::new);

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
