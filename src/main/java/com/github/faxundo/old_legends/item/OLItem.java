package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.EmeraldMourningSword;
import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningSwordAwake;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OLItem {

    public static final Item ICON = registerItem("icon",
            new Item(new FabricItemSettings()));

    public static final Item EMERALD_MOURNING = registerItem("emerald_mourning",
            new EmeraldMourningSword(ToolMaterials.DIAMOND, 3, -2.4f,
                    new FabricItemSettings()));
    public static final Item EMERALD_MOURNING_AWAKE = registerItem("emerald_mourning_awake",
            new EmeraldMourningSwordAwake(ToolMaterials.DIAMOND, 5, -2.4f,
                    new FabricItemSettings()));

    public static final Item PALE_GEM = registerItem("pale_gem",
            new Item(new FabricItemSettings()));
    public static final Item END_EXTRACT = registerItem("end_extract",
            new Item(new FabricItemSettings()));
    public static final Item AWAKENING_UPGRADE = registerItem("awakening_upgrade",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OldLegends.MOD_ID, name), item);
    }

    public static void registerOldLegendsItems() {
        OldLegends.LOGGER.info("Registering Mod Items for" + OldLegends.MOD_NAME);
    }
}
