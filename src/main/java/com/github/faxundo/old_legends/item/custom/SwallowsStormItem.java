package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.item.OLGenericShield;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class SwallowsStormItem extends OLGenericShield {
    public SwallowsStormItem(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings, coolDownTicks, enchantability, repairItemTag);
    }
}
