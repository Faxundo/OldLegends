package com.github.faxundo.old_legends.screen.data;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record ReliquaryData(BlockPos pos) implements CustomPayload {

    public static final CustomPayload.Id<ReliquaryData> PACKET_ID = new CustomPayload.Id<>(
            OldLegends.identifier("reliquary"));

    public static final PacketCodec<RegistryByteBuf, ReliquaryData> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, ReliquaryData::pos, ReliquaryData::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
