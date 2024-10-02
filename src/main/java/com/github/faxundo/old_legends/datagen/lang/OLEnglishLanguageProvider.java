package com.github.faxundo.old_legends.datagen.lang;

import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.custom.ReliquaryBlock;
import com.github.faxundo.old_legends.block.custom.RuneTableBlock;
import com.github.faxundo.old_legends.effect.OLEffect;
import com.github.faxundo.old_legends.event.KeyInputHandler;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.item.custom.FlutterEcho;
import com.github.faxundo.old_legends.item.custom.SwallowsStorm;
import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningAwake;
import com.github.faxundo.old_legends.item.custom.awake.FlutterEchoAwake;
import com.github.faxundo.old_legends.item.custom.awake.SwallowsStormAwake;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.villager.OLVillager;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class OLEnglishLanguageProvider extends FabricLanguageProvider {

    public OLEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(OLItem.ICON, "Old Legends");

        translationBuilder.add(OLItem.BOOK_OF_THE_LEGENDS, "Book Of The Legends");
        translationBuilder.add(OLItem.PALE_GEM, "Pale Gem");
        translationBuilder.add(OLItem.END_EXTRACT, "End Extract");
        translationBuilder.add(OLItem.AWAKENING_UPGRADE, "Awakening Upgrade");

        //Runes
        translationBuilder.add(OLItem.BLANK_RUNE, "Rune");
        translationBuilder.add(OLItem.DEATH_RUNE, "Death Rune");
        translationBuilder.add(OLItem.SKY_RUNE, "Sky Rune");
        translationBuilder.add(OLItem.TIME_RUNE, "Time Rune");

        //Book of The Legends
        translationBuilder.add(BookOfTheLegends.PAGES, "Pages");
        translationBuilder.add(BookOfTheLegends.OF, "of");
        translationBuilder.add(BookOfTheLegends.NO_OWNER, "You are not the owner of this book");
        translationBuilder.add(BookOfTheLegends.HAS_PAGE, "This book already have this page");
        translationBuilder.add(BookOfTheLegends.NEW_PAGE, "has been added to this book");
        translationBuilder.add(BookOfTheLegends.USAGE, "For add pages do left click in the book, then do right click in the page");

        //Emerald Mourning
        translationBuilder.add(OLItem.EMERALD_MOURNING, "Emerald Mourning");
        translationBuilder.add(OLItem.EMERALD_MOURNING_AWAKE, "Emerald Mourning Awake");
        translationBuilder.add(EmeraldMourning.ABILITY_NAME_1, "Emerald Revenge");
        translationBuilder.add(EmeraldMourning.ABILITY_1, "Has +30% damage vs Illagers");
        translationBuilder.add(EmeraldMourning.ABILITY_NAME_2, "Heritage of the Fallen");
        translationBuilder.add(EmeraldMourning.ABILITY_2, "At the kill a Illager obtaining 1 Emerald");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_AWAKE_1, "Has +60% damage vs Illagers");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_AWAKE_2, "At the kill a Illager obtaining 1-3 Emerald");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_NAME_AWAKE, "Time of Revenge");
        translationBuilder.add(EmeraldMourningAwake.ABILITY_AWAKE, "Summon 3 Villagers to carry out their revenge");

        //Swallows Storm
        translationBuilder.add(OLItem.SWALLOWS_STORM, "Swallows Storm");
        translationBuilder.add(OLItem.SWALLOWS_STORM_AWAKE, "Swallows Storm Awake");
        translationBuilder.add(SwallowsStorm.CHARGES, "Storm Charges");
        translationBuilder.add(SwallowsStorm.ABILITY_NAME_1, "Storm Energy");
        translationBuilder.add(SwallowsStorm.ABILITY_1, "Gain 1 Storm Charge when blocking damage. If it block a lighting bolt it is full charge. During a storm there is a 15% chance of being struck by lightning when blocking");
        translationBuilder.add(SwallowsStorm.ABILITY_NAME_2, "Refreshing Electroshock");
        translationBuilder.add(SwallowsStorm.ABILITY_2, "If you have less than 50% Health and have Swallows Storm charged, when you block, you will recover 6 Health");
        translationBuilder.add(SwallowsStorm.ABILITY_NAME_3, "Electrified");
        translationBuilder.add(SwallowsStorm.ABILITY_3, "When you blocking, the attacker takes damage and soft knockback. For every 10 Storm Charges what you have, deal +1 Damage");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE_1, "Gain 2 Storm Charges when blocking damage. If it block a lighting bolt it is full charge. During a storm there is a 30% chance of being struck by lightning when blocking");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE_2, "If you have less than 50% Health and have Swallows Storm charged, when you block, you will recover 10 Health");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE_3, "When you blocking, the attacker takes damage and soft knockback. For every 10 Storm Charges what you have, deal +2 Damage");
        translationBuilder.add(SwallowsStormAwake.ABILITY_NAME_AWAKE, "Explosive Expulsion");
        translationBuilder.add(SwallowsStormAwake.ABILITY_AWAKE, "Cause a electric explosion around you what deal 15 of Damage and have Knockback");

        //Flutter Echo
        translationBuilder.add(OLItem.FLUTTER_ECHO, "Flutter Echo");
        translationBuilder.add(OLItem.FLUTTER_ECHO_AWAKE, "Flutter Echo Awake");
        translationBuilder.add(FlutterEcho.ABILITY_NAME_1, "Echo Mine");
        translationBuilder.add(FlutterEcho.ABILITY_1, "Has 30% to generate Echo Ore");
        translationBuilder.add(FlutterEcho.ABILITY_NAME_2, "Echo Hit");
        translationBuilder.add(FlutterEcho.ABILITY_2, "Has 30% hit again with 50% of base attack");
        translationBuilder.add(FlutterEchoAwake.ABILITY_AWAKE_1, "Has 50% to generate Echo Ore");
        translationBuilder.add(FlutterEchoAwake.ABILITY_AWAKE_2, "Has 50% hit again with 50% of base attack");
        translationBuilder.add(FlutterEchoAwake.ABILITY_NAME_AWAKE, "Shooting Echo");
        translationBuilder.add(FlutterEchoAwake.ABILITY_AWAKE, "Throws an echo that hits entities (14 damage) and breaks blocks");

        //Blocks
        translationBuilder.add(OLBlock.RUNE_TABLE, "Rune Table");
        translationBuilder.add(RuneTableBlock.INVALID_WISE, "Expand the village for keep other Wise");
        translationBuilder.add(OLBlock.ECHO_BLOCK, "Echo Block");
        translationBuilder.add(OLBlock.ECHO_ORE, "Echo Ore");

        //Reliquary
        translationBuilder.add(OLBlock.RELIQUARY_BLOCK, "Reliquary");
        translationBuilder.add(ReliquaryBlock.DESCRIPTION, "Only sparkle items can be put inside");
        translationBuilder.add(ReliquaryBlock.LOCK, "Lock");
        translationBuilder.add(ReliquaryBlock.LOCK_DESCRIPTION, "Wait at night for see what happens");
        translationBuilder.add(ReliquaryBlock.ALERT, "This reliquary is locked!");

        //Blueprints
        translationBuilder.add(OLItem.BLANK_BLUEPRINT, "Blueprint");
        translationBuilder.add(OLItem.RELIQUARY_BLUEPRINT, "Reliquary Blueprint");

        //Pages
        translationBuilder.add(OLItem.RELIQUARY_PAGE, "Reliquary Page");
        translationBuilder.add(OLItem.DEATH_RUNE_PAGE, "Death Rune Page");
        translationBuilder.add(OLItem.SKY_RUNE_PAGE, "Sky Rune Page");
        translationBuilder.add(OLItem.TIME_RUNE_PAGE, "Time Rune Page");
        translationBuilder.add(OLItem.EMERALD_MOURNING_PAGE, "Emerald Mourning Page");
        translationBuilder.add(OLItem.SWALLOWS_STORM_PAGE, "Swallows Storm Page");
        translationBuilder.add(OLItem.FLUTTER_ECHO_PAGE, "Flutter Echo Page");

        //Tooltip
        translationBuilder.add(OLHelper.SHIFT_TOOLTIP, "▶ Shift");

        //Villager
        translationBuilder.add(OLVillager.SAGE_NAME,"Sage");

        //Effects
        translationBuilder.add(OLEffect.BANE_OF_THE_VILLAGE,"Bane of the Village");

        //Advancements
        translationBuilder.add(advancementName("icon"), "Old Legends");
        translationBuilder.add(advancementName("icon.description"), "Reveal the mysteries of the past");
        translationBuilder.add(advancementName("emerald_mourning"), "By an Emerald");
        translationBuilder.add(advancementName("emerald_mourning.description"), "Obtain the Emerald Mourning Sword");
        translationBuilder.add(advancementName("emerald_mourning_awake"), "An emerald for an emerald");
        translationBuilder.add(advancementName("emerald_mourning_awake.description"), "Use a awakening upgrade and awake the Emerald Mourning");
        translationBuilder.add(advancementName("swallows_storm"), "Storm Hunger");
        translationBuilder.add(advancementName("swallows_storm.description"), "Obtain the Swallows Storm Shield");
        translationBuilder.add(advancementName("swallows_storm_awake"), "Storwned");
        translationBuilder.add(advancementName("swallows_storm_awake.description"), "Use a awakening upgrade and awake the Swallows Storm");
        translationBuilder.add(advancementName("flutter_echo"), "Echoes");
        translationBuilder.add(advancementName("flutter_echo.description"), "Obtain the Flutter Echo Pickaxe");
        translationBuilder.add(advancementName("flutter_echo_awake"), "Echoes Echoe Echo Ech Ec E  ");
        translationBuilder.add(advancementName("flutter_echo_awake.description"), "Use a awakening upgrade and awake the Flutter Echo");
        translationBuilder.add(advancementName("rune"), "Old Power");
        translationBuilder.add(advancementName("rune.description"), "Craft a Rune");
        translationBuilder.add(advancementName("rune_table"), "RuneMaster");
        translationBuilder.add(advancementName("rune_table.description"), "Craft the Rune Table");
        translationBuilder.add(advancementName("craft_all_runes"), "Rune Collector");
        translationBuilder.add(advancementName("craft_all_runes.description"), "Obtain all Runes");
        translationBuilder.add(advancementName("book_of_the_legends"), "A Distant Past");
        translationBuilder.add(advancementName("book_of_the_legends.description"), "Obtain a Book of the Legends on a Forest Hut");
        translationBuilder.add(advancementName("owner_of_book"), "Owner");
        translationBuilder.add(advancementName("owner_of_book.description"), "Hold the book and right click to become the owner of the book");
        translationBuilder.add(advancementName("pages_of_book"), "Pages");
        translationBuilder.add(advancementName("pages_of_book.description"), "Get a page. Place the book over the page to add it.");
        translationBuilder.add(advancementName("pale_gem"), "Gemoff");
        translationBuilder.add(advancementName("pale_gem.description"), "Be an archaeologist and obtain a Pale Gem");
        translationBuilder.add(advancementName("end_extract"), "End Essence");
        translationBuilder.add(advancementName("end_extract.description"), "Defeat at the end dragon and obtain the end essence");
        translationBuilder.add(advancementName("awakening_upgrade"), "Awakening");
        translationBuilder.add(advancementName("awakening_upgrade.description"), "Craft the Awakening Upgrade with End Extract and Pale Gem");

        //Keys
        translationBuilder.add(KeyInputHandler.OL_KEY_CATEGORY, "Old Legends Key");
        translationBuilder.add(KeyInputHandler.MAIN_HAND_ABILITY, "Main Hand Ability");
        translationBuilder.add(KeyInputHandler.OFF_HAND_ABILITY, "Off Hand Ability");

        //Config
        translationBuilder.add("text.autoconfig.old_legends.title", "Old Legends");
        translationBuilder.add(configName("enableAwakening"), "Enable Awakening");
        translationBuilder.add(configTooltip("enableAwakening"), "This disable drop of Pale Gem and End Extract");
        translationBuilder.add(configName("paleGemDesertWellWeight"), "Pale Gem Desert Well Weight");
        translationBuilder.add(configName("paleGemDesertPyramidWeight"), "Pale Gem Desert Pyramid Weight");
        translationBuilder.add(configName("paleGemOceanRuinColdWeight"), "Pale Gem Ocean Ruins Cold Weight");
        translationBuilder.add(configName("paleGemOceanRuinWarmWeight"), "Pale Gem Ocean Ruins Warm Weight");
        translationBuilder.add(configName("paleGemTrailsRuinsCommonWeight"), "Pale Gem Trails Ruins Common Weight");
        translationBuilder.add(configName("paleGemTrailsRuinsRareWeight"), "Pale Gem Trails Ruins Rare Weight");
        translationBuilder.add(configName("deathRuneSnifferDigging"), "Death Rune Weight when Sniffer Digging");
        translationBuilder.add(configName("skyRuneSnifferDigging"), "Sky Rune Weight when Sniffer Digging");

        translationBuilder.add(configName("runeTable"), "Rune Table");
        translationBuilder.add(configName("runeTable.range"), "Range");
        translationBuilder.add(configTooltip("runeTable.range"), "This range defines the abstract size of a 'village'");
        translationBuilder.add(configName("runeTable.maxVillagers"), "Max Villagers");
        translationBuilder.add(configTooltip("runeTable.maxVillagers"), "How much villagers need for keep other wise");

        translationBuilder.add(configName("emeraldMourning"), "Emerald Mourning");
        translationBuilder.add(configName("emeraldMourning.enable"), "Enable Emerald Mourning");
        translationBuilder.add(configName("emeraldMourning.weight"), "Loot Weight");
        translationBuilder.add(configName("emeraldMourning.percentageIllager"), "% Damage Increase vs Illagers");
        translationBuilder.add(configName("emeraldMourning.percentageIllagerAwake"), "[Awake] % Damage Increase vs Illagers");
        translationBuilder.add(configName("emeraldMourning.consumeDurability"), "[Awake] % Durability consumed to use Active Ability");
        translationBuilder.add(configName("emeraldMourning.mobTime"), "[Awake] Life Time Mourning Mob");
        translationBuilder.add(configTooltip("emeraldMourning.mobTime"), "Time in Ticks. 20 Ticks = 1 second");
        translationBuilder.add(configName("emeraldMourning.cooldown"), "[Awake] Cooldown for Active Ability");
        translationBuilder.add(configTooltip("emeraldMourning.cooldown"), "Time in Ticks. 20 Ticks = 1 second");
        translationBuilder.add(configName("emeraldMourning.weightPage"), "Page Loot Weight");

        translationBuilder.add(configName("swallowsStorm"), "Swallows Storm");
        translationBuilder.add(configName("swallowsStorm.enable"), "Enable Swallows Storm");
        translationBuilder.add(configName("swallowsStorm.weigth"), "Loot Weight");
        translationBuilder.add(configName("swallowsStorm.maxCharges"), "Max Storm Charges");
        translationBuilder.add(configName("swallowsStorm.heal"), "Refreshing Electroshock Heal");
        translationBuilder.add(configName("swallowsStorm.healLost"), "Refreshing Electroshock Heal Lost");
        translationBuilder.add(configTooltip("swallowsStorm.healLost"), "Amount of Health what you need lose for active the heal");
        translationBuilder.add(configName("swallowsStorm.chance"), "Storm Energy Chance of Lightning Bolt");
        translationBuilder.add(configName("swallowsStorm.cooldown"), "Lightning Bolt cooldown");
        translationBuilder.add(configTooltip("swallowsStorm.stormCooldown"), "How much time in ticks must pass for another lightning bolt to fall");
        translationBuilder.add(configName("swallowsStorm.consumeDurability"), "% Durability consumed");
        translationBuilder.add(configTooltip("swallowsStorm.consumeDurability"), "This apply to Active Ability (Awake) and Heal Ability");
        translationBuilder.add(configName("swallowsStorm.electrifiedDamage"), "Electrified Damage");
        translationBuilder.add(configTooltip("swallowsStorm.electrifiedDamage"), "This number is divided for Max Storm Charges and result is the damage");
        translationBuilder.add(configName("swallowsStorm.electrifiedStrength"), "Knockback Strength of Electrified");
        translationBuilder.add(configName("swallowsStorm.stormHealAwake"), "[Awake] Refreshing Electroshock Heal");
        translationBuilder.add(configName("swallowsStorm.maxChargesAwake"), "[Awake] Max Storm Charges");
        translationBuilder.add(configName("swallowsStorm.electrifiedStrengthAwake"), "[Awake] Knockback Strength of Electrified");
        translationBuilder.add(configName("swallowsStorm.explosiveDamage"), "[Awake] Knockback Damage of Explosive Expulsion");
        translationBuilder.add(configName("swallowsStorm.explosiveRange"), "[Awake] Knockback Range of Explosive Expulsion");
        translationBuilder.add(configName("swallowsStorm.explosiveKnockback"), "[Awake] Knockback Strength of Explosive Expulsion");
        translationBuilder.add(configName("swallowsStorm.explosiveCooldown"), "[Awake] Cooldown of Explosive Expulsion");
        translationBuilder.add(configName("swallowsStorm.weightPage"), "Page Loot Weight");

        translationBuilder.add(configName("flutterEcho"), "Flutter Echo");
        translationBuilder.add(configName("flutterEcho.enable"), "Enable Flutter Echo");
        translationBuilder.add(configName("flutterEcho.weight"), "Loot Weight");
        translationBuilder.add(configName("flutterEcho.mineChance"), "Eco Mine chance");
        translationBuilder.add(configName("flutterEcho.attackChance"), "Eco Hit chance");
        translationBuilder.add(configName("flutterEcho.mineChanceAwake"), "[Awake] Eco Mine chance");
        translationBuilder.add(configName("flutterEcho.attackChanceAwake"), "[Awake] Eco Hit chance");
        translationBuilder.add(configName("flutterEcho.cooldown"), "[Awake] Cooldown for Active Ability");
        translationBuilder.add(configName("flutterEcho.consumeDurability"), "[Awake] % Durability consumed");
        translationBuilder.add(configTooltip("flutterEcho.consumeDurability"), "% Durability consumed to use Active Ability");
        translationBuilder.add(configName("flutterEcho.countLimit"), "[Awake] Count Limit");
        translationBuilder.add(configTooltip("flutterEcho.countLimit"), "Limit of blocks and entities whats can hitting");
        translationBuilder.add(configName("flutterEcho.weightPage"), "Page Loot Weight");

        translationBuilder.add(configName("reliquary"), "Reliquary");
        translationBuilder.add(configName("reliquary.grinningHoarderTime"), "Time it activates");
        translationBuilder.add(configTooltip("reliquary.grinningHoarderTime"), "Time in ticks when the Grinning Hoarder come for the Reliquary");
        translationBuilder.add(configName("reliquary.ancientCityWeight"), "Blueprint Ancient City Weight");
        translationBuilder.add(configName("reliquary.strongholdWeight"), "Blueprint Stronghold Weight");
        translationBuilder.add(configName("reliquary.desertPyramidWeight"), "Blueprint Pyramid Weight");
        translationBuilder.add(configName("reliquary.ruinsCommonWeight"), "Blueprint Ruins Common Weight");
        translationBuilder.add(configName("reliquary.ruinsRareWeight"), "Blueprint Ruins Rare Weight");
    }

    private String advancementName(String name) {
        return "advancement.old_legends." + name;
    }

    private String configName(String name) {
        return "text.autoconfig.old_legends.option." + name;
    }

    private String configTooltip(String name) {
        return "text.autoconfig.old_legends.option." + name + ".@Tooltip";
    }
}
