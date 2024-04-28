package com.github.faxundo.old_legends.recipe;

import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RuneTableRecipe implements Recipe<SimpleInventory> {

    private final ItemStack output;
    private final List<IngredientSlot> recipeItems;
    final CraftingRecipeCategory category;

    public RuneTableRecipe(CraftingRecipeCategory category, List<IngredientSlot> ingredients, ItemStack stack) {
        this.category = category;
        this.output = stack;
        this.recipeItems = ingredients;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(11, Ingredient.EMPTY);
        for (IngredientSlot slot : recipeItems) {
            list.set(slot.getSlot(), slot.getIngredient());
        }
        return list;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) {
            return false;
        }

        for (IngredientSlot slot : recipeItems) {
            if (slot.getSlot() == 0 && inventory.getStack(slot.getSlot()).getItem() instanceof BookOfTheLegends) {
                continue;
            } else if (slot.getSlot() == 1) {
                if (!slot.getIngredient().test(inventory.getStack(slot.getSlot()))) {
                    return false;
                }
            } else if (slot.getSlot() == 10) {
                if (!slot.getIngredient().test(inventory.getStack(slot.getSlot()))) {
                    return false;
                }
            } else {
                if (!slot.getIngredient().test(inventory.getStack(slot.getSlot()))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {

        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<RuneTableRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "rune_table";
    }

    public static class Serializer implements RecipeSerializer<RuneTableRecipe> {

        public static final Serializer INSTANCE = new Serializer();

        public static final Codec<RuneTableRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(recipe -> recipe.category),
                Codec.list(IngredientSlot.CODEC).fieldOf("recipeItems").forGetter(recipe -> recipe.recipeItems),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
        ).apply(instance, RuneTableRecipe::new));

        protected Serializer() {
        }

        @Override
        public Codec<RuneTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public RuneTableRecipe read(PacketByteBuf buf) {
            CraftingRecipeCategory craftingRecipeCategory = buf.readEnumConstant(CraftingRecipeCategory.class);

            List<IngredientSlot> recipeItems = new ArrayList<>();
            int ingredientCount = buf.readVarInt();
            for (int i = 0; i < ingredientCount; i++) {
                int slot = buf.readVarInt();
                Ingredient ingredient = Ingredient.fromPacket(buf);
                recipeItems.add(new IngredientSlot(slot, ingredient));
            }

            ItemStack output = buf.readItemStack();
            return new RuneTableRecipe(craftingRecipeCategory, recipeItems, output);
        }

        @Override
        public void write(PacketByteBuf buf, RuneTableRecipe recipe) {
            buf.writeEnumConstant(recipe.category);

            buf.writeVarInt(recipe.recipeItems.size());
            for (IngredientSlot slot : recipe.recipeItems) {
                buf.writeVarInt(slot.getSlot());
                slot.getIngredient().write(buf);
            }

            buf.writeItemStack(recipe.output);
        }
    }

    public static class IngredientSlot {
        public static final Codec<IngredientSlot> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("slot").forGetter(IngredientSlot::getSlot),
                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(IngredientSlot::getIngredient)
        ).apply(instance, IngredientSlot::new));

        private final int slot;
        private final Ingredient ingredient;

        public IngredientSlot(int slot, Ingredient ingredient) {
            this.slot = slot;
            this.ingredient = ingredient;
        }

        public int getSlot() {
            return slot;
        }

        public Ingredient getIngredient() {
            return ingredient;
        }
    }
}
