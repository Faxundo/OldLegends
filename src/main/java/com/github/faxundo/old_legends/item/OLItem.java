package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningAwake;
import com.github.faxundo.old_legends.item.custom.awake.SwallowsStormAwakeItem;
import com.github.faxundo.old_legends.util.OLTag;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLItem {

    public static final Item ICON = registerItem("icon",
            new Item(new FabricItemSettings()));
    public static final Item BOOK_OF_THE_LEGENDS = registerItem("book_of_the_legends",
            new BookOfTheLegends(new FabricItemSettings()));

    public static final Item EMERALD_MOURNING = registerItem("emerald_mourning",
            new EmeraldMourning(ToolMaterials.DIAMOND, 3, -2.4f,
                    new FabricItemSettings()));
    public static final Item EMERALD_MOURNING_AWAKE = registerItem("emerald_mourning_awake",
            new EmeraldMourningAwake(ToolMaterials.NETHERITE, 4, -2.4f,
                    new FabricItemSettings()));

    public static final Item SWALLOWS_STORM = registerItem("swallows_storm",
            new SwallowsStormItem(new FabricItemSettings().maxDamage(672), 90, 15, OLTag.Items.SKY_REPAIR));
    public static final Item SWALLOWS_STORM_AWAKE = registerItem("swallows_storm_awake",
            new SwallowsStormAwakeItem(new FabricItemSettings().maxDamage(672+256), 80, 17, OLTag.Items.SKY_REPAIR));

    public static final Item DEATH_RUNE = registerItem("death_rune",
            new OLGenericRune(new FabricItemSettings()));
    public static final Item SKY_RUNE = registerItem("sky_rune",
            new OLGenericRune(new FabricItemSettings()));

    public static final Item PALE_GEM = registerItem("pale_gem",
            new OLGenericItem(new FabricItemSettings().maxCount(8)));
    public static final Item END_EXTRACT = registerItem("end_extract",
            new OLGenericItem(new FabricItemSettings().maxCount(4)));
    public static final Item AWAKENING_UPGRADE = registerItem("awakening_upgrade",
            new OLGenericItem(new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OldLegends.MOD_ID, name), item);
    }

    public static void registerOldLegendsItems() {
        LOGGER.info(">>> Registering " + MOD_NAME + " items.");
    }
}
