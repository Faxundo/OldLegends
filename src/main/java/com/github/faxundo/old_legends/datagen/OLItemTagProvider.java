package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.integration.MythicMetals;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.util.OLTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class OLItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public OLItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {

        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(OLTag.Items.RUNE)
                .add(OLItem.SKY_RUNE)
                .add(OLItem.DEATH_RUNE)
                .add(OLItem.TIME_RUNE);
        getOrCreateTagBuilder(OLTag.Items.BLUEPRINT)
                .add(OLItem.RELIQUARY_BLUEPRINT);
        getOrCreateTagBuilder(OLTag.Items.PAGE)
                .add(OLItem.DEATH_RUNE_PAGE)
                .add(OLItem.SKY_RUNE_PAGE)
                .add(OLItem.TIME_RUNE_PAGE)
                .add(OLItem.RELIQUARY_PAGE)
                .add(OLItem.EMERALD_MOURNING_PAGE)
                .add(OLItem.SWALLOWS_STORM_PAGE)
                .add(OLItem.FLUTTER_ECHO_PAGE);
        getOrCreateTagBuilder(OLTag.Items.SKY_REPAIR)
                .add(OLItem.SKY_RUNE);
        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(OLItem.AWAKENING_UPGRADE);
        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(OLItem.END_EXTRACT);
        getOrCreateTagBuilder(OLTag.Items.CAN_AWAKE)
                .add(OLItem.EMERALD_MOURNING)
                .add(OLItem.SWALLOWS_STORM)
                .add(OLItem.FLUTTER_ECHO);
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(OLItem.EMERALD_MOURNING)
                .add(OLItem.EMERALD_MOURNING_AWAKE);
        getOrCreateTagBuilder(OLTag.Items.SHIELD)
                .add(OLItem.SWALLOWS_STORM)
                .add(OLItem.SWALLOWS_STORM_AWAKE);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(OLItem.FLUTTER_ECHO)
                .add(OLItem.FLUTTER_ECHO_AWAKE);
        getOrCreateTagBuilder(OLTag.Items.SPARKLE)
                .add(Items.EXPERIENCE_BOTTLE)
                .add(Items.EMERALD_BLOCK)
                .add(Items.GLISTERING_MELON_SLICE)
                .add(Items.EMERALD)
                .add(Items.GOLDEN_CARROT)
                .add(Items.DIAMOND)
                .add(Items.DIAMOND_AXE)
                .add(Items.DIAMOND_HOE)
                .add(Items.DIAMOND_PICKAXE)
                .add(Items.DIAMOND_SHOVEL)
                .add(Items.DIAMOND_SWORD)
                .add(Items.DIAMOND_HELMET)
                .add(Items.DIAMOND_CHESTPLATE)
                .add(Items.DIAMOND_LEGGINGS)
                .add(Items.DIAMOND_BOOTS)
                .add(Items.GOLD_INGOT)
                .add(Items.GOLDEN_AXE)
                .add(Items.GOLDEN_HOE)
                .add(Items.GOLDEN_PICKAXE)
                .add(Items.GOLDEN_SHOVEL)
                .add(Items.GOLDEN_SWORD)
                .add(Items.GOLDEN_HELMET)
                .add(Items.GOLDEN_CHESTPLATE)
                .add(Items.GOLDEN_LEGGINGS)
                .add(Items.GOLDEN_BOOTS)
                .add(Items.AMETHYST_SHARD)
                .add(Items.DIAMOND_BLOCK)
                .add(Items.GOLDEN_APPLE)
                .add(Items.GOLD_BLOCK)
                .add(Items.TOTEM_OF_UNDYING)
                .add(Items.ENCHANTED_GOLDEN_APPLE)
                .addOptional(MythicMetals.MIDAS_GOLD_INGOT)
                .addOptional(MythicMetals.MIDAS_GOLD_BLOCK);
    }
}
