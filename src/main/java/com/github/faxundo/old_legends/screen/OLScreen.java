package com.github.faxundo.old_legends.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class OLScreen {

    public static void registerScreen () {
        HandledScreens.register(OLScreenHandler.BOOK_OF_THE_LEGENDS_SCREEN_HANDLER, BookOfTheLegendsScreen::new);
        HandledScreens.register(OLScreenHandler.RELIQUARY_SCREEN_HANDLER, ReliquaryScreen::new);
    }
}
