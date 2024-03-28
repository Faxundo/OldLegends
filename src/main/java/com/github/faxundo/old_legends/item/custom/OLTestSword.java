package com.github.faxundo.old_legends.item.custom;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class OLTestSword extends SwordItem implements OLGenericItemInterface {
    public OLTestSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
