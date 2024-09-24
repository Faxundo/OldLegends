package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.util.OLTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.advancement.criterion.RecipeCraftedCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class OLAdvancementProvider extends FabricAdvancementProvider {


    public OLAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry oldLegends = Advancement.Builder.create()
                .display(
                        OLItem.ICON,
                        Text.translatable("advancement.old_legends.icon"),
                        Text.translatable("advancement.old_legends.icon.description"),
                        OldLegends.identifier("textures/gui/advancements/backgrounds/old_legends_background.png"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )

                .criterion("icon", InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
                .build(consumer, OldLegends.MOD_ID + "/root");

        AdvancementEntry obtainEmeraldMourning = Advancement.Builder.create().parent(oldLegends)
                .display(
                        OLItem.EMERALD_MOURNING,
                        Text.translatable("advancement.old_legends.emerald_mourning"),
                        Text.translatable("advancement.old_legends.emerald_mourning.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("emerald_mourning", InventoryChangedCriterion.Conditions.items(OLItem.EMERALD_MOURNING))
                .build(consumer, OldLegends.MOD_ID + "/emerald_mourning");

        AdvancementEntry obtainEmeraldMourningAwake = Advancement.Builder.create().parent(obtainEmeraldMourning)
                .display(
                        OLItem.EMERALD_MOURNING_AWAKE,
                        Text.translatable("advancement.old_legends.emerald_mourning_awake"),
                        Text.translatable("advancement.old_legends.emerald_mourning_awake.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("emerald_mourning_awake", InventoryChangedCriterion.Conditions.items(OLItem.EMERALD_MOURNING_AWAKE))
                .build(consumer, OldLegends.MOD_ID + "/emerald_mourning_awake");

        AdvancementEntry obtainSwallowsStorm = Advancement.Builder.create().parent(oldLegends)
                .display(
                        OLItem.SWALLOWS_STORM,
                        Text.translatable("advancement.old_legends.swallows_storm"),
                        Text.translatable("advancement.old_legends.swallows_storm.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("swallows_storm", InventoryChangedCriterion.Conditions.items(OLItem.SWALLOWS_STORM))
                .build(consumer, OldLegends.MOD_ID + "/swallows_storm");

        AdvancementEntry obtainSwallowsStormAwake = Advancement.Builder.create().parent(obtainSwallowsStorm)
                .display(
                        OLItem.SWALLOWS_STORM_AWAKE,
                        Text.translatable("advancement.old_legends.swallows_storm_awake"),
                        Text.translatable("advancement.old_legends.swallows_storm_awake.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("swallows_storm_awake", InventoryChangedCriterion.Conditions.items(OLItem.SWALLOWS_STORM_AWAKE))
                .build(consumer, OldLegends.MOD_ID + "/swallows_storm_awake");

        AdvancementEntry obtainFlutterEcho = Advancement.Builder.create().parent(oldLegends)
                .display(
                        OLItem.FLUTTER_ECHO,
                        Text.translatable("advancement.old_legends.flutter_echo"),
                        Text.translatable("advancement.old_legends.flutter_echo.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("flutter_echo", InventoryChangedCriterion.Conditions.items(OLItem.FLUTTER_ECHO))
                .build(consumer, OldLegends.MOD_ID + "/flutter_echo");

        AdvancementEntry obtainFlutterEchoAwake = Advancement.Builder.create().parent(obtainFlutterEcho)
                .display(
                        OLItem.FLUTTER_ECHO_AWAKE,
                        Text.translatable("advancement.old_legends.flutter_echo_awake"),
                        Text.translatable("advancement.old_legends.flutter_echo_awake.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("flutter_echo_awake", InventoryChangedCriterion.Conditions.items(OLItem.FLUTTER_ECHO_AWAKE))
                .build(consumer, OldLegends.MOD_ID + "/flutter_echo_awake");

        AdvancementEntry craftRune = Advancement.Builder.create().parent(oldLegends)
                .display(
                        OLItem.BLANK_RUNE,
                        Text.translatable("advancement.old_legends.rune"),
                        Text.translatable("advancement.old_legends.rune.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("rune", RecipeCraftedCriterion.Conditions.create(OldLegends.identifier("blank_rune")))
                .build(consumer, OldLegends.MOD_ID + "/rune");

        AdvancementEntry placeRuneTable = Advancement.Builder.create().parent(craftRune)
                .display(
                        OLBlock.RUNE_TABLE,
                        Text.translatable("advancement.old_legends.rune_table"),
                        Text.translatable("advancement.old_legends.rune_table.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("rune_table", ItemCriterion.Conditions.createPlacedBlock(OLBlock.RUNE_TABLE))
                .build(consumer, OldLegends.MOD_ID + "/rune_table");

        AdvancementEntry obtainBookOfTheLegends = Advancement.Builder.create().parent(placeRuneTable)
                .display(
                        OLItem.BOOK_OF_THE_LEGENDS,
                        Text.translatable("advancement.old_legends.book_of_the_legends"),
                        Text.translatable("advancement.old_legends.book_of_the_legends.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("book_of_the_legends", InventoryChangedCriterion.Conditions.items(OLItem.BOOK_OF_THE_LEGENDS))
                .build(consumer, OldLegends.MOD_ID + "/book_of_the_legends");

        AdvancementEntry ownerOfBook = Advancement.Builder.create().parent(obtainBookOfTheLegends)
                .display(
                        OLItem.BOOK_OF_THE_LEGENDS,
                        Text.translatable("advancement.old_legends.owner_of_book"),
                        Text.translatable("advancement.old_legends.owner_of_book.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("owner_of_book", InventoryChangedCriterion.Conditions.items(OLItem.BOOK_OF_THE_LEGENDS))
                .build(consumer, OldLegends.MOD_ID + "/owner_of_book");

        AdvancementEntry pagesOfBook = Advancement.Builder.create().parent(ownerOfBook)
                .display(
                        OLItem.DEATH_RUNE_PAGE,
                        Text.translatable("advancement.old_legends.pages_of_book"),
                        Text.translatable("advancement.old_legends.pages_of_book.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("pages_of_book", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().tag(OLTag.Items.PAGE).build()
                ))
                .build(consumer, OldLegends.MOD_ID + "/pages_of_book");

        AdvancementEntry craftAllRunes = Advancement.Builder.create().parent(pagesOfBook)
                .display(
                        OLItem.DEATH_RUNE,
                        Text.translatable("advancement.old_legends.craft_all_runes"),
                        Text.translatable("advancement.old_legends.craft_all_runes.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("craft_all_runes", InventoryChangedCriterion.Conditions.items(
                        OLItem.DEATH_RUNE
                        , OLItem.TIME_RUNE
                        , OLItem.SKY_RUNE))
                .build(consumer, OldLegends.MOD_ID + "/craft_all_runes");

        AdvancementEntry paleGem = Advancement.Builder.create().parent(oldLegends)
                .display(
                        OLItem.PALE_GEM,
                        Text.translatable("advancement.old_legends.pale_gem"),
                        Text.translatable("advancement.old_legends.pale_gem.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("pale_gem", InventoryChangedCriterion.Conditions.items(OLItem.PALE_GEM))
                .build(consumer, OldLegends.MOD_ID + "/pale_gem");

        AdvancementEntry endExtract = Advancement.Builder.create().parent(paleGem)
                .display(
                        OLItem.END_EXTRACT,
                        Text.translatable("advancement.old_legends.end_extract"),
                        Text.translatable("advancement.old_legends.end_extract.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("end_extract", InventoryChangedCriterion.Conditions.items(OLItem.END_EXTRACT))
                .build(consumer, OldLegends.MOD_ID + "/end_extract");

        AdvancementEntry awakeningUpgrade = Advancement.Builder.create().parent(endExtract)
                .display(
                        OLItem.AWAKENING_UPGRADE,
                        Text.translatable("advancement.old_legends.awakening_upgrade"),
                        Text.translatable("advancement.old_legends.awakening_upgrade.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("awakening_upgrade", InventoryChangedCriterion.Conditions.items(OLItem.AWAKENING_UPGRADE))
                .build(consumer, OldLegends.MOD_ID + "/awakening_upgrade");

    }
}
