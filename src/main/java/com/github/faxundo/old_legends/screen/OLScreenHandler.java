package com.github.faxundo.old_legends.screen;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class OLScreenHandler {

    public static ScreenHandlerType<BookOfTheLegendsScreenHandler> BOOK_OF_THE_LEGENDS_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(OldLegends.MOD_ID, "book_of_the_legends"),
                    BookOfTheLegendsScreenHandler::new);

}
