package com.github.faxundo.old_legends.recipe;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.recipe.custom.RuneTableRecipe;
import com.github.faxundo.old_legends.recipe.custom.RuneTableSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class OLRecipe {

    public static final RecipeType<RuneTableRecipe> RUNE_TABLE_TYPE = new RecipeType<>() {};
    public static final RecipeSerializer<RuneTableRecipe> RUNE_TABLE_SERIALIZER = new RuneTableSerializer() {};

//    public static void registerRecipes() {
//        Registry.register(Registries.RECIPE_SERIALIZER, OldLegends.identifier("rune_table"),RUNE_TABLE_SERIALIZER);
//        Registry.register(Registries.RECIPE_TYPE, OldLegends.identifier("rune_table"), RUNE_TABLE_TYPE);
//    }
}
