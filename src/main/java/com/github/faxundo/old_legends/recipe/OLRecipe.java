package com.github.faxundo.old_legends.recipe;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class OLRecipe {

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, OldLegends.identifier(RuneTableRecipe.Type.ID),RuneTableRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, OldLegends.identifier(RuneTableRecipe.Type.ID),RuneTableRecipe.Type.INSTANCE);
    }
}
