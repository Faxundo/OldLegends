package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

import static com.github.faxundo.old_legends.OldLegends.CONFIG;

public class SwallowsStormAwakeItem extends SwallowsStormItem {
    public SwallowsStormAwakeItem(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings, coolDownTicks, enchantability, repairItemTag);
        setId("swallows_storm");
        setAwake(true);
        setUseCharges(true);
        setMaxCharges(CONFIG.swallowsStorm.swallowsStormAwakeMaxCharges);
        setAmountPassives(3);
    }

}
