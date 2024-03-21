package com.github.faxundo.old_legends.util;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableModifiers {

    private static final Identifier PILLAGER_OUTPOST =
            new Identifier("minecraft", "chests/pillager_outpost");
    private static final Identifier END_DRAGON =
            new Identifier("minecraft", "entities/ender_dragon");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (OldLegends.CONFIG.emeraldMourning.enableEmeraldMourning && PILLAGER_OUTPOST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(OldLegends.CONFIG.emeraldMourning.emeraldMourningWeight))
                        .with(ItemEntry.builder(OLItem.EMERALD_MOURNING))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
            if (OldLegends.CONFIG.enableAwakening) {
                if (END_DRAGON.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .with(ItemEntry.builder(OLItem.END_EXTRACT));
                    tableBuilder.pool(poolBuilder.build());
                }
                if (LootTables.DESERT_WELL_ARCHAEOLOGY.equals(id)) {
                    addPaleGem(tableBuilder, OldLegends.CONFIG.paleGemDesertWellWeight);
                }
                if (LootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(id)) {
                    addPaleGem(tableBuilder, OldLegends.CONFIG.paleGemDesertPyramidWeight);
                }
                if (LootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.equals(id)) {
                    addPaleGem(tableBuilder, OldLegends.CONFIG.paleGemOceanRuinColdWeight);
                }
                if (LootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.equals(id)) {
                    addPaleGem(tableBuilder, OldLegends.CONFIG.paleGemOceanRuinWarmWeight);
                }
                if (LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY.equals(id)) {
                    addPaleGem(tableBuilder, OldLegends.CONFIG.paleGemTrailsRuinsCommonWeight);
                }
                if (LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY.equals(id)) {
                    addPaleGem(tableBuilder, OldLegends.CONFIG.paleGemTrailsRuinsRareWeight);
                }
            }
        });
    }

    private static void addPaleGem(LootTable.Builder tableBuilder, int weight) {
        tableBuilder.modifyPools((poolBuilder) -> poolBuilder.with(ItemEntry.builder(OLItem.PALE_GEM).weight(weight)));
    }
}
