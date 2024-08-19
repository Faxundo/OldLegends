package com.github.faxundo.old_legends.screen.data;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record RuneTableData (BlockPos pos) implements CustomPayload {

    public static final CustomPayload.Id<RuneTableData> PACKET_ID = new CustomPayload.Id<>(
            OldLegends.identifier("rune_table"));

    public static final PacketCodec<RegistryByteBuf, RuneTableData> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, RuneTableData::pos, RuneTableData::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
