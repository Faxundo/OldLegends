package com.github.faxundo.old_legends.screen.slot;

import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BookOfTheLegendsSlot extends Slot {

    public BookOfTheLegendsSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof BookOfTheLegends;
    }
}
