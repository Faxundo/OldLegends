package com.github.faxundo.old_legends.screen.custom;

import com.github.faxundo.old_legends.block.entity.RuneTableBlockEntity;
import com.github.faxundo.old_legends.screen.OLScreenHandler;
import com.github.faxundo.old_legends.screen.data.RuneTableData;
import com.github.faxundo.old_legends.screen.slot.BookOfTheLegendsSlot;
import com.github.faxundo.old_legends.screen.slot.PillarSlot;
import com.github.faxundo.old_legends.screen.slot.RuneTableOutputSlot;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class RuneTableScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    public final RuneTableBlockEntity blockEntity;

    public RuneTableScreenHandler(int syncId, PlayerInventory inventory, RuneTableData data) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(data.pos()));
    }

    public RuneTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(OLScreenHandler.RUNE_TABLE_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 11);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.blockEntity = ((RuneTableBlockEntity) blockEntity);


        this.addSlot(new BookOfTheLegendsSlot(inventory, 0, 13, 16));
        this.addSlot(new Slot(inventory, 1, 80, 54));
        this.addSlot(new PillarSlot(inventory, 2, 80, 17, this, "north"));
        this.addSlot(new Slot(inventory, 3, 108, 26));
        this.addSlot(new PillarSlot(inventory, 4, 117, 54, this, "east"));
        this.addSlot(new Slot(inventory, 5, 108, 82));
        this.addSlot(new PillarSlot(inventory, 6, 80, 91, this, "south"));
        this.addSlot(new Slot(inventory, 7, 52, 82));
        this.addSlot(new PillarSlot(inventory, 8, 43, 54, this, "west"));
        this.addSlot(new Slot(inventory, 9, 52, 26));
        this.addSlot(new RuneTableOutputSlot(inventory, 10, 147, 92));

        addPlayerInventorySlots(playerInventory);
    }

    public RuneTableBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (index < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventorySlots(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 121 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 179));
        }
    }
}
