package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningAwake;
import com.github.faxundo.old_legends.item.custom.awake.SwallowsStormAwakeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLItem {

    public static final TagKey<Item> SKY_RUNE_TAG =
            TagKey.of(RegistryKeys.ITEM, new Identifier("old_legends", "sky_rune"));

    public static final Item ICON = registerItem("icon",
            new Item(new FabricItemSettings()));

    public static final Item EMERALD_MOURNING = registerItem("emerald_mourning",
            new EmeraldMourning(ToolMaterials.DIAMOND, 3, -2.4f,
                    new FabricItemSettings().fireproof()));
    public static final Item EMERALD_MOURNING_AWAKE = registerItem("emerald_mourning_awake",
            new EmeraldMourningAwake(ToolMaterials.NETHERITE, 3, -2.4f,
                    new FabricItemSettings().fireproof()));

    public static final Item SWALLOWS_STORM = registerItem("swallows_storm",
            new SwallowsStormItem(new FabricItemSettings().maxDamage(672).fireproof(), 90, 15, SKY_RUNE_TAG));
    public static final Item SWALLOWS_STORM_AWAKE = registerItem("swallows_storm_awake",
            new SwallowsStormAwakeItem(new FabricItemSettings().maxDamage(672+256).fireproof(), 80, 17, SKY_RUNE_TAG));
    public static final Item SKY_RUNE = registerItem("sky_rune",
            new Item(new FabricItemSettings().fireproof()));

    public static final Item PALE_GEM = registerItem("pale_gem",
            new Item(new FabricItemSettings()));
    public static final Item END_EXTRACT = registerItem("end_extract",
            new Item(new FabricItemSettings().fireproof()));
    public static final Item AWAKENING_UPGRADE = registerItem("awakening_upgrade",
            new Item(new FabricItemSettings().fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OldLegends.MOD_ID, name), item);
    }

    public static void registerOldLegendsItems() {
        LOGGER.info(">>> Registering " + MOD_NAME + " items.");
    }
}
