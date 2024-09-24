package com.github.faxundo.old_legends.screen.slot;

import com.github.faxundo.old_legends.util.OLTag;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ReliquarySlot extends Slot {

    public ReliquarySlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(OLTag.Items.SPARKLE);
    }

    public boolean isFull () {
        return this.getStack().getCount() == this.getMaxItemCount();
    }
}
