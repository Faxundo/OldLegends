package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
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
                        Text.translatable("advancement.old_legends.obtain.emerald_mourning"),
                        Text.translatable("advancement.old_legends.obtain.emerald_mourning.description"),
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
                        Text.translatable("advancement.old_legends.obtain.emerald_mourning_awake"),
                        Text.translatable("advancement.old_legends.obtain.emerald_mourning_awake.description"),
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
                        Text.translatable("advancement.old_legends.obtain.swallows_storm"),
                        Text.translatable("advancement.old_legends.obtain.swallows_storm.description"),
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
                        Text.translatable("advancement.old_legends.obtain.swallows_storm_awake"),
                        Text.translatable("advancement.old_legends.obtain.swallows_storm_awake.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("swallows_storm_awake", InventoryChangedCriterion.Conditions.items(OLItem.SWALLOWS_STORM_AWAKE))
                .build(consumer, OldLegends.MOD_ID + "/swallows_storm_awake");


    }

}
