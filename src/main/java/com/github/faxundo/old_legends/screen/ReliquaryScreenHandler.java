package com.github.faxundo.old_legends.screen;

import com.github.faxundo.old_legends.block.entity.ReliquaryBlockEntity;
import com.github.faxundo.old_legends.screen.slot.ReliquarySlot;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class ReliquaryScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final ReliquaryBlockEntity blockEntity;

    private final ScreenHandlerContext context;

    public ReliquaryScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2), ScreenHandlerContext.EMPTY, 0);
    }

    public ReliquaryScreenHandler(int syncId, PlayerInventory playerInventory,
                                  BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate,
                                  ScreenHandlerContext context, int storedRopes) {
        super(OLScreenHandler.RELIQUARY_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 3);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.addProperties(propertyDelegate);
        this.blockEntity = ((ReliquaryBlockEntity) blockEntity);

        this.context = context;

        this.addSlot(new ReliquarySlot(inventory, 0, 44, 31));
        this.addSlot(new ReliquarySlot(inventory, 1, 80, 31));
        this.addSlot(new ReliquarySlot(inventory, 2, 116, 31));

        addPlayerInventorySlots(playerInventory);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id == 0) {
            if (!inventory.isEmpty()) {
                player.playSound(SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.PLAYERS, 5.0f, 0f);
                this.propertyDelegate.set(1, 1);
                this.propertyDelegate.set(0, scoreSystem(inventory));
                return true;
            }
        }
        return super.onButtonClick(player, id);

    }

    public int scoreSystem(Inventory inventory) {
        int points = 0;
        if (!inventory.isEmpty()) {
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack stack = inventory.getStack(i);
                Item item = stack.getItem();
                int multiplier = getMultiplierForSlot(i);
                points += (int) (getItemPoints(item) * (multiplier / 100.0));
            }
        }
        return points;
    }

    private int getItemPoints(Item item) {
        return switch (item.getTranslationKey()) {
            case "item.minecraft.experience_bottle" -> -10;
            case "item.minecraft.emerald_block" -> -4;
            case "item.minecraft.glistering_melon_slice" -> -3;
            case "item.minecraft.emerald" -> -2;
            case "item.minecraft.golden_carrot" -> -1;
            case "item.minecraft.diamond" -> +1;
            case "item.minecraft.gold_ingot" -> +2;
            case "item.minecraft.amethyst_shard" -> +3;
            case "item.minecraft.diamond_block" -> +4;
            case "item.minecraft.golden_apple" -> +5;
            case "item.minecraft.gold_block" -> +6;
            case "item.minecraft.totem_of_undying" -> +7;
            case "item.minecraft.enchanted_golden_apple" -> +10;
            default -> 0;
        };
    }

    private int getMultiplierForSlot(int index) {
        return switch (index) {
            case 1 -> 200;
            case 2 -> 50;
            default -> 100;
        };
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
    //Work in progress
//            for (int i = 0; i < 3; i++) {
//                slot = this.slots.get(i);
//                if (!originalStack.isEmpty() && slot instanceof ReliquarySlot reliquarySlot) {
//
//                    if (!reliquarySlot.isFull()) {
//
//                        if (reliquarySlot.canInsert(originalStack)) {
//
//                            originalStack.decrement(1);
//                            newStack.setCount(1);
//                            reliquarySlot.setStack(newStack);
//                            slot.markDirty();
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }
//        return ItemStack.EMPTY;


    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player) && propertyDelegate.get(1) == 0;
    }

    private void addPlayerInventorySlots(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }
}
