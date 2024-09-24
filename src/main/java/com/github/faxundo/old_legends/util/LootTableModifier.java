package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.WeatherCheckLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class LootTableModifier {

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (OldLegends.CONFIG.enableAwakening) {
                if (EntityType.ENDER_DRAGON.getLootTableId() == key) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(OLItem.END_EXTRACT));
                    tableBuilder.pool(poolBuilder);
                }
                if (LootTables.DESERT_WELL_ARCHAEOLOGY.equals(key)) {
                    addItemToLootTable(tableBuilder, OLItem.PALE_GEM, OldLegends.CONFIG.paleGemDesertWellWeight);
                }
                if (LootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(key)) {
                    addItemToLootTable(tableBuilder, OLItem.PALE_GEM, OldLegends.CONFIG.paleGemDesertPyramidWeight);
                    addItemToLootTable(tableBuilder, OLItem.RELIQUARY_BLUEPRINT, OldLegends.CONFIG.reliquary.desertPyramidWeight);
                }
                if (LootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(key)) {
                    addItemToLootTable(tableBuilder, OLItem.PALE_GEM, OldLegends.CONFIG.paleGemOceanRuinColdWeight);
                }
                if (LootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.equals(key)) {
                    addItemToLootTable(tableBuilder, OLItem.PALE_GEM, OldLegends.CONFIG.paleGemOceanRuinWarmWeight);
                }
                if (LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY.equals(key)) {
                    addItemToLootTable(tableBuilder, OLItem.PALE_GEM, OldLegends.CONFIG.paleGemTrailsRuinsCommonWeight);
                    addItemToLootTable(tableBuilder, OLItem.RELIQUARY_BLUEPRINT, OldLegends.CONFIG.reliquary.ruinsCommonWeight);
                }
                if (LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY.equals(key)) {
                    addItemToLootTable(tableBuilder, OLItem.PALE_GEM, OldLegends.CONFIG.paleGemTrailsRuinsRareWeight);
                    addItemToLootTable(tableBuilder, OLItem.RELIQUARY_BLUEPRINT, OldLegends.CONFIG.reliquary.ruinsRareWeight);
                }
                if (LootTables.SNIFFER_DIGGING_GAMEPLAY.equals(key)) {
                    addItemSnifferDig(tableBuilder, OLItem.DEATH_RUNE, OldLegends.CONFIG.deathRuneSnifferDigging);
                    addItemSnifferDig(tableBuilder, OLItem.SKY_RUNE, OldLegends.CONFIG.skyRuneSnifferDigging);
                }
            }
            if (OldLegends.CONFIG.emeraldMourning.enable) {
                if (LootTables.PILLAGER_OUTPOST_CHEST == key) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.emeraldMourning.weight))
                            .with(ItemEntry.builder(OLItem.EMERALD_MOURNING))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (LootTables.VILLAGE_CARTOGRAPHER_CHEST == key) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.emeraldMourning.weightPage))
                            .with(ItemEntry.builder(OLItem.EMERALD_MOURNING_PAGE))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
            }

            if (OldLegends.CONFIG.swallowsStorm.enable) {
                if (LootTables.ANCIENT_CITY_CHEST == key) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.swallowsStorm.weight))
                            .with(ItemEntry.builder(OLItem.SWALLOWS_STORM))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (LootTables.VILLAGE_CARTOGRAPHER_CHEST == key) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.swallowsStorm.weightPage))
                            .with(ItemEntry.builder(OLItem.SWALLOWS_STORM_PAGE))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
            }
            if (LootTables.ANCIENT_CITY_CHEST == key) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.reliquary.ancientCityWeight))
                        .with(ItemEntry.builder(OLItem.RELIQUARY_BLUEPRINT))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.STRONGHOLD_CORRIDOR_CHEST == key
                    || LootTables.STRONGHOLD_CROSSING_CHEST == key
                    || LootTables.STRONGHOLD_LIBRARY_CHEST == key) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.reliquary.strongholdWeight))
                        .with(ItemEntry.builder(OLItem.RELIQUARY_BLUEPRINT))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (LootTables.ABANDONED_MINESHAFT_CHEST == key) {
                if (OldLegends.CONFIG.flutterEcho.enable) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.flutterEcho.weight))
                            .with(ItemEntry.builder(OLItem.FLUTTER_ECHO))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
                if (LootTables.VILLAGE_CARTOGRAPHER_CHEST == key) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.flutterEcho.weightPage))
                            .with(ItemEntry.builder(OLItem.FLUTTER_ECHO_PAGE))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
            }
        });
    }

    private static void addItemToLootTable(LootTable.Builder tableBuilder, Item item, int weight) {
        tableBuilder.modifyPools((poolBuilder) -> poolBuilder.with(ItemEntry.builder(item).weight(weight)));
    }

    private static void addItemSnifferDig(LootTable.Builder tableBuilder, Item item, int weight) {
        tableBuilder.modifyPools((poolBuilder) -> poolBuilder.with(ItemEntry
                .builder(item)
                .weight(weight)
                .conditionally(WeatherCheckLootCondition.create().raining(true))
        ));
    }
}
