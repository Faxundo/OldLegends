package com.github.faxundo.old_legends.recipe.custom;

import com.github.faxundo.old_legends.recipe.OLRecipe;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

// Doesn't works

public class RuneTableRecipe implements Recipe<SimpleInventory> {
    public static final int INPUT_SLOTS = 10;
    private final String key;
    private final int experience;
    private final List<Ingredient> ingredientList;
    private final ItemStack output;

    public RuneTableRecipe(String key, List<Ingredient> ingredientList, int experience, ItemStack output) {
        this.key = key;
        this.ingredientList = ingredientList;
        this.experience = experience;
        this.output = output;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.ingredientList.size());
        list.addAll(ingredientList);
        return list;
    }

    public String getKey() {
        return key;
    }

    public int getExperience() {
        return experience;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (inventory.size() != INPUT_SLOTS + 1) return false;
        if (world.isClient) return false;

        List<Boolean> ingredientsFine = new ArrayList<>();

        for (int slot = 1; slot < INPUT_SLOTS; slot++) {
            ItemStack stackInSlot = inventory.getStack(slot);

            for (Ingredient ingredient : ingredientList) {
                ingredientsFine.add(ingredient.test(stackInSlot));
                break;
            }
        }

        for (Boolean value : ingredientsFine) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return OLRecipe.RUNE_TABLE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return OLRecipe.RUNE_TABLE_TYPE;
    }
}
