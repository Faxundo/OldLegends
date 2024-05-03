package com.github.faxundo.old_legends.screen;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.screen.custom.BookOfTheLegendsScreenHandler;
import com.github.faxundo.old_legends.screen.custom.ItemScreenHandler;
import com.github.faxundo.old_legends.screen.custom.ReliquaryScreenHandler;
import com.github.faxundo.old_legends.screen.custom.RuneTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public final class OLScreenHandler {

    public static ScreenHandlerType<BookOfTheLegendsScreenHandler> BOOK_OF_THE_LEGENDS_SCREEN_HANDLER;
    public static ScreenHandlerType<ItemScreenHandler> ITEM_SCREEN_HANDLER;
    public static ScreenHandlerType<ReliquaryScreenHandler> RELIQUARY_SCREEN_HANDLER;
    public static ScreenHandlerType<RuneTableScreenHandler> RUNE_TABLE_SCREEN_HANDLER;

    public static void registerScreenHandler() {
        BOOK_OF_THE_LEGENDS_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
                OldLegends.identifier("book_of_the_legends"),
                new ScreenHandlerType<>(BookOfTheLegendsScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
        ITEM_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
                OldLegends.identifier("item"),
                new ScreenHandlerType<>(ItemScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
        RELIQUARY_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
                OldLegends.identifier("reliquary"),
                new ExtendedScreenHandlerType<>(ReliquaryScreenHandler::new));
        RUNE_TABLE_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER,
                OldLegends.identifier("rune_table"),
                new ExtendedScreenHandlerType<>(RuneTableScreenHandler::new));
    }
}
