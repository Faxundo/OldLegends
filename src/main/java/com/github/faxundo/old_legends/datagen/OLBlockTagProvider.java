package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.integration.MythicMetals;
import com.github.faxundo.old_legends.util.OLTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class OLBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public OLBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(OLTag.Blocks.ORES)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.COAL_ORES)
                .addOptional(MythicMetals.ORES);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(OLBlock.RELIQUARY_BLOCK)
                .add(OLBlock.RUNE_TABLE);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(OLBlock.RELIQUARY_BLOCK)
                .add(OLBlock.RUNE_TABLE);
    }
}
