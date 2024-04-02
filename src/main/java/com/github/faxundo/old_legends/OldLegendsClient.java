package com.github.faxundo.old_legends;

import com.github.faxundo.old_legends.event.KeyInputHandler;
import com.github.faxundo.old_legends.networking.OLPacket;
import com.github.faxundo.old_legends.screen.BookOfTheLegendsScreen;
import com.github.faxundo.old_legends.screen.OLScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class OldLegendsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        OLPacket.registerS2CPackets();

        ScreenRegistry.register(OLScreenHandler.BOOK_OF_THE_LEGENDS_SCREEN_HANDLER, BookOfTheLegendsScreen::new);
    }
}
