package com.github.faxundo.old_legends.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class BookOfTheLegendsScreenHandler extends ScreenHandler {

    public BookOfTheLegendsScreenHandler(
            ScreenHandlerType<? extends BookOfTheLegendsScreenHandler> type, int syncId, PlayerEntity player) {
        super(type, syncId);
    }

    public BookOfTheLegendsScreenHandler(int syncId, PlayerInventory inventory) {
        super(OLScreenHandler.BOOK_OF_THE_LEGENDS_SCREEN_HANDLER, syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
