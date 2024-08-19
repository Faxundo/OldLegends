package com.github.faxundo.old_legends.datagen;

import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.item.OLItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class OLRecipeProvider extends FabricRecipeProvider {


    public OLRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, OLBlock.RELIQUARY_BLOCK, 1)
                .pattern("LOL")
                .pattern("ODO")
                .pattern("LOL")
                .input('O', Items.GOLD_INGOT)
                .input('L', Items.IRON_INGOT)
                .input('D', OLItem.RELIQUARY_BLUEPRINT)
                .showNotification(true)
                .criterion(hasItem(OLItem.RELIQUARY_BLUEPRINT), conditionsFromItem(OLItem.RELIQUARY_BLUEPRINT))
                .offerTo(exporter, new Identifier(getRecipeName(OLBlock.RELIQUARY_BLOCK)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, OLItem.BLANK_RUNE, 3)
                .pattern(" L ")
                .pattern("L L")
                .pattern(" L ")
                .input('L', Items.POLISHED_DEEPSLATE)
                .showNotification(true)
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                .offerTo(exporter, new Identifier(getRecipeName(OLItem.BLANK_RUNE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, OLBlock.RUNE_TABLE, 1)
                .pattern("DOD")
                .pattern("LEL")
                .pattern("LOL")
                .input('O', Items.GOLD_INGOT)
                .input('L', Items.COBBLED_DEEPSLATE)
                .input('D', Items.AMETHYST_SHARD)
                .input('E', OLItem.PALE_GEM)
                .showNotification(true)
                .criterion(hasItem(OLItem.PALE_GEM), conditionsFromItem(OLItem.PALE_GEM))
                .offerTo(exporter, new Identifier(getRecipeName(OLBlock.RUNE_TABLE)));


        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofStacks(OLItem.AWAKENING_UPGRADE.getDefaultStack()),
                        Ingredient.ofStacks(OLItem.EMERALD_MOURNING.getDefaultStack()),
                        Ingredient.ofStacks(OLItem.END_EXTRACT.getDefaultStack()),
                        RecipeCategory.COMBAT, OLItem.EMERALD_MOURNING_AWAKE)
                .criterion(hasItem(OLItem.EMERALD_MOURNING), conditionsFromItem(OLItem.EMERALD_MOURNING))
                .offerTo(exporter, new Identifier(getRecipeName(OLItem.EMERALD_MOURNING_AWAKE)));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofStacks(OLItem.AWAKENING_UPGRADE.getDefaultStack()),
                        Ingredient.ofStacks(OLItem.SWALLOWS_STORM.getDefaultStack()),
                        Ingredient.ofStacks(OLItem.END_EXTRACT.getDefaultStack()),
                        RecipeCategory.COMBAT, OLItem.SWALLOWS_STORM_AWAKE)
                .criterion(hasItem(OLItem.SWALLOWS_STORM), conditionsFromItem(OLItem.SWALLOWS_STORM))
                .offerTo(exporter, new Identifier(getRecipeName(OLItem.SWALLOWS_STORM_AWAKE)));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofStacks(OLItem.AWAKENING_UPGRADE.getDefaultStack()),
                        Ingredient.ofStacks(OLItem.FLUTTER_ECHO.getDefaultStack()),
                        Ingredient.ofStacks(OLItem.END_EXTRACT.getDefaultStack()),
                        RecipeCategory.COMBAT, OLItem.FLUTTER_ECHO_AWAKE)
                .criterion(hasItem(OLItem.FLUTTER_ECHO), conditionsFromItem(OLItem.FLUTTER_ECHO))
                .offerTo(exporter, new Identifier(getRecipeName(OLItem.FLUTTER_ECHO_AWAKE)));
    }
}
