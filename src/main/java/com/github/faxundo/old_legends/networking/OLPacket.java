package com.github.faxundo.old_legends.networking;

import com.github.faxundo.old_legends.networking.packet.MainHandAbilityC2SPacket;
import com.github.faxundo.old_legends.networking.packet.OffHandAbilityC2SPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class OLPacket {

    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(MainHandAbilityC2SPacket.PACKET_ID, MainHandAbilityC2SPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(OffHandAbilityC2SPacket.PACKET_ID, OffHandAbilityC2SPacket.CODEC);
    }

    public static void registerS2CPackets() {

    }
}

