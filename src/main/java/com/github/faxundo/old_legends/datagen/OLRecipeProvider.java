package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.item.OLItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class OLRecipeProvider extends FabricRecipeProvider {


    public OLRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, OLItem.AWAKENING_UPGRADE, 1)
                .pattern(" A ")
                .pattern("AFA")
                .pattern(" A ")
                .input('F', OLItem.PALE_GEM)
                .input('A', Items.NETHERITE_INGOT)
                .showNotification(true)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(hasItem(OLItem.PALE_GEM), conditionsFromItem(OLItem.PALE_GEM))
                .offerTo(exporter, new Identifier(getRecipeName(OLItem.AWAKENING_UPGRADE)));

    }
}
