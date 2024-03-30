package com.github.faxundo.old_legends;

import com.github.faxundo.old_legends.event.KeyInputHandler;
import com.github.faxundo.old_legends.networking.OLPackets;
import net.fabricmc.api.ClientModInitializer;

public class OldLegendsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        OLPackets.registerS2CPackets();
    }
}
