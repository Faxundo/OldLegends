package com.github.faxundo.old_legends.screen;

import com.github.faxundo.old_legends.OldLegends;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public final class OLScreenHandler {

    public static ScreenHandlerType<BookOfTheLegendsScreenHandler> BOOK_OF_THE_LEGENDS_SCREEN_HANDLER;
    public static ScreenHandlerType<ReliquaryScreenHandler> RELIQUARY_SCREEN_HANDLER;

    public static void registerScreenHandler() {
        BOOK_OF_THE_LEGENDS_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
                OldLegends.identifier("book_of_the_legends"),
                new ScreenHandlerType<>(BookOfTheLegendsScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
        RELIQUARY_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
                OldLegends.identifier("reliquary"),
                new ExtendedScreenHandlerType<>(ReliquaryScreenHandler::new));
    }
}
