package com.github.faxundo.old_legends.screen.slot;

import com.github.faxundo.old_legends.block.entity.RuneTableBlockEntity;
import com.github.faxundo.old_legends.screen.custom.RuneTableScreenHandler;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class PillarSlot extends Slot {

    private final RuneTableScreenHandler runeTableScreenHandler;
    private final String direction;

    public PillarSlot(Inventory inventory, int index, int x, int y, RuneTableScreenHandler runeTableScreenHandler, String direction) {
        super(inventory, index, x, y);
        this.runeTableScreenHandler = runeTableScreenHandler;
        this.direction = direction;
    }

    @Override
    public boolean isEnabled() {
        RuneTableBlockEntity runeTableBlockEntity = runeTableScreenHandler.getBlockEntity();
        switch (direction) {
            case "north":
                if (runeTableBlockEntity.hasPillar(runeTableBlockEntity.getWorld(), runeTableBlockEntity.getPos().add(0, 0, -3))) {
                    return true;
                }
                break;
            case "south":
                if (runeTableBlockEntity.hasPillar(runeTableBlockEntity.getWorld(), runeTableBlockEntity.getPos().add(0, 0, 3))) {
                    return true;
                }
                break;
            case "east":
                if (runeTableBlockEntity.hasPillar(runeTableBlockEntity.getWorld(), runeTableBlockEntity.getPos().add(3, 0, 0))) {
                    return true;
                }
                break;
            case "west":
                if (runeTableBlockEntity.hasPillar(runeTableBlockEntity.getWorld(), runeTableBlockEntity.getPos().add(-3, 0, 0))) {
                    return true;
                }
                break;
        }
        return false;
    }
}

