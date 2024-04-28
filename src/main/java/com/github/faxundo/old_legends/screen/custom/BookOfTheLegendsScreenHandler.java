package com.github.faxundo.old_legends.screen.custom;

import com.github.faxundo.old_legends.screen.OLScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class BookOfTheLegendsScreenHandler extends ScreenHandler {

    private PlayerEntity player;
    private PlayerInventory inventory;

    public BookOfTheLegendsScreenHandler(int syncId, PlayerInventory inventory) {
        super(OLScreenHandler.BOOK_OF_THE_LEGENDS_SCREEN_HANDLER, syncId);
        this.player = inventory.player;
        this.inventory = inventory;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public PlayerEntity getPlayer() {
        return player;
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
