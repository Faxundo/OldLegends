package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.item.custom.FlutterEcho;
import com.github.faxundo.old_legends.item.custom.SwallowsStorm;
import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningAwake;
import com.github.faxundo.old_legends.item.custom.awake.FlutterEchoAwake;
import com.github.faxundo.old_legends.item.custom.awake.SwallowsStormAwake;
import com.github.faxundo.old_legends.item.generic.OLGenericBlueprint;
import com.github.faxundo.old_legends.item.generic.OLGenericItem;
import com.github.faxundo.old_legends.item.generic.OLGenericPage;
import com.github.faxundo.old_legends.item.generic.OLGenericRune;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLItem {

    public static final Item ICON = registerItem("icon",
            new Item(new Item.Settings()));
    public static final Item BOOK_OF_THE_LEGENDS = registerItem("book_of_the_legends",
            new BookOfTheLegends(new Item.Settings()));

    public static final Item PALE_GEM = registerItem("pale_gem",
            new OLGenericItem(new Item.Settings().maxCount(8)));
    public static final Item END_EXTRACT = registerItem("end_extract",
            new OLGenericItem(new Item.Settings().maxCount(4)));
    public static final Item AWAKENING_UPGRADE = registerItem("awakening_upgrade",
            new OLGenericItem(new Item.Settings().maxCount(1)));

    public static final Item EMERALD_MOURNING = registerItem("emerald_mourning",
            new EmeraldMourning(ToolMaterials.DIAMOND, new Item.Settings()));
    public static final Item EMERALD_MOURNING_AWAKE = registerItem("emerald_mourning_awake",
            new EmeraldMourningAwake(ToolMaterials.NETHERITE, new Item.Settings()));
    public static final Item EMERALD_MOURNING_PAGE = registerItem("emerald_mourning_page",
            new OLGenericPage(new Item.Settings()));

    public static final Item SWALLOWS_STORM = registerItem("swallows_storm",
            new SwallowsStorm(new Item.Settings().maxDamage(672)));
    public static final Item SWALLOWS_STORM_AWAKE = registerItem("swallows_storm_awake",
            new SwallowsStormAwake(new Item.Settings().maxDamage(672 + 256)));
    public static final Item SWALLOWS_STORM_PAGE = registerItem("swallows_storm_page",
            new OLGenericPage(new Item.Settings()));

    public static final Item FLUTTER_ECHO = registerItem("flutter_echo",
            new FlutterEcho(ToolMaterials.DIAMOND, BlockTags.PICKAXE_MINEABLE, new Item.Settings()));
    public static final Item FLUTTER_ECHO_AWAKE = registerItem("flutter_echo_awake",
            new FlutterEchoAwake(ToolMaterials.NETHERITE, BlockTags.PICKAXE_MINEABLE, new Item.Settings()));
    public static final Item FLUTTER_ECHO_PAGE = registerItem("flutter_echo_page",
            new OLGenericPage(new Item.Settings()));

    public static final Item BLANK_BLUEPRINT = registerItem("blank_blueprint",
            new OLGenericBlueprint(new Item.Settings()));
    public static final Item RELIQUARY_BLUEPRINT = registerItem("reliquary_blueprint",
            new OLGenericBlueprint(new Item.Settings()));
    public static final Item RELIQUARY_PAGE = registerItem("reliquary_page",
            new OLGenericPage(new Item.Settings()));
    public static final Item BLANK_RUNE = registerItem("blank_rune",
            new OLGenericRune(new Item.Settings()));
    public static final Item DEATH_RUNE = registerItem("death_rune",
            new OLGenericRune(new Item.Settings()));
    public static final Item DEATH_RUNE_PAGE = registerItem("death_rune_page",
            new OLGenericPage(new Item.Settings()));
    public static final Item SKY_RUNE = registerItem("sky_rune",
            new OLGenericRune(new Item.Settings()));
    public static final Item SKY_RUNE_PAGE = registerItem("sky_rune_page",
            new OLGenericPage(new Item.Settings()));
    public static final Item TIME_RUNE = registerItem("time_rune",
            new OLGenericRune(new Item.Settings()));
    public static final Item TIME_RUNE_PAGE = registerItem("time_rune_page",
            new OLGenericPage(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(OldLegends.MOD_ID, name), item);
    }

    public static void registerOLItems() {
        LOGGER.info(">>> Registering " + MOD_NAME + " items.");
    }
}
