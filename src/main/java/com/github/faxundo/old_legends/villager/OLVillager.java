package com.github.faxundo.old_legends.villager;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLVillager {

    public static final String SAGE_NAME = "entity.minecraft.villager.sage";

    public static final RegistryKey<PointOfInterestType> SAGE_POI_KEY = poiKey("sage");
    public static final PointOfInterestType SAGE_POI = registerPoi("sage", OLBlock.RUNE_TABLE);
    public static final VillagerProfession SAGE = registerProfession("sage", SAGE_POI_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, OldLegends.identifier(name),
                new VillagerProfession(name, entry -> entry.matchesKey(type)
                        , entry -> entry.matchesKey(type)
                        , ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_CLERIC));
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(OldLegends.identifier(name), 1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, OldLegends.identifier(name));
    }

    public static void registerVillagers() {
        LOGGER.info(">>> Registering " + MOD_NAME + " villagers.");
    }
}
