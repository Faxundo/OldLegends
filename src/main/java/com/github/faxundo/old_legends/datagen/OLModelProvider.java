package com.github.faxundo.old_legends.datagen;

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

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(OLItem.ICON, Models.GENERATED);
        itemModelGenerator.register(OLItem.BOOK_OF_THE_LEGENDS, Models.GENERATED);
        itemModelGenerator.register(OLItem.PALE_GEM, Models.GENERATED);
        itemModelGenerator.register(OLItem.END_EXTRACT, Models.GENERATED);
        itemModelGenerator.register(OLItem.AWAKENING_UPGRADE, Models.GENERATED);
        itemModelGenerator.register(OLItem.SKY_RUNE, Models.GENERATED);
        itemModelGenerator.register(OLItem.DEATH_RUNE, Models.GENERATED);
        itemModelGenerator.register(OLItem.EMERALD_MOURNING, Models.HANDHELD);
        itemModelGenerator.register(OLItem.EMERALD_MOURNING_AWAKE, Models.HANDHELD);
    }
}
