package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.item.OLItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class OLModelProvider extends FabricModelProvider {

    public OLModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(OLBlock.ECHO_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(OLBlock.ECHO_BLOCK);
        blockStateModelGenerator.registerSimpleState(OLBlock.RUNE_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(OLItem.ICON, Models.GENERATED);
        itemModelGenerator.register(OLItem.BOOK_OF_THE_LEGENDS, Models.GENERATED);
        itemModelGenerator.register(OLItem.PALE_GEM, Models.GENERATED);
        itemModelGenerator.register(OLItem.END_EXTRACT, Models.GENERATED);
        itemModelGenerator.register(OLItem.AWAKENING_UPGRADE, Models.GENERATED);

        itemModelGenerator.register(OLItem.BLANK_BLUEPRINT, Models.GENERATED);
        itemModelGenerator.register(OLItem.RELIQUARY_BLUEPRINT, Models.GENERATED);
        itemModelGenerator.register(OLItem.RELIQUARY_PAGE, Models.GENERATED);

        itemModelGenerator.register(OLItem.BLANK_RUNE, Models.GENERATED);
        itemModelGenerator.register(OLItem.SKY_RUNE, Models.GENERATED);
        itemModelGenerator.register(OLItem.DEATH_RUNE, Models.GENERATED);
        itemModelGenerator.register(OLItem.TIME_RUNE, Models.GENERATED);
        itemModelGenerator.register(OLItem.DEATH_RUNE_PAGE, Models.GENERATED);
        itemModelGenerator.register(OLItem.SKY_RUNE_PAGE, Models.GENERATED);
        itemModelGenerator.register(OLItem.TIME_RUNE_PAGE, Models.GENERATED);

        itemModelGenerator.register(OLItem.EMERALD_MOURNING, Models.HANDHELD);
        itemModelGenerator.register(OLItem.EMERALD_MOURNING_AWAKE, Models.HANDHELD);
        itemModelGenerator.register(OLItem.EMERALD_MOURNING_PAGE, Models.GENERATED);
        itemModelGenerator.register(OLItem.SWALLOWS_STORM_PAGE, Models.GENERATED);
        itemModelGenerator.register(OLItem.FLUTTER_ECHO, Models.HANDHELD);
        itemModelGenerator.register(OLItem.FLUTTER_ECHO_AWAKE, Models.HANDHELD);
        itemModelGenerator.register(OLItem.FLUTTER_ECHO_PAGE, Models.GENERATED);

        itemModelGenerator.register(OLItem.VENGEANCE_PAGE, Models.GENERATED);
    }

}
