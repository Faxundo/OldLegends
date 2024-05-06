package com.github.faxundo.old_legends.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public class RuneTableSerializer implements RecipeSerializer<RuneTableRecipe> {

    public static final Codec<RuneTableRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("key").forGetter(RuneTableRecipe::getKey),
            validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9).fieldOf("ingredients").forGetter(RuneTableRecipe::getIngredientList),
            Codec.INT.fieldOf("experience").forGetter(RuneTableRecipe::getExperience),
            ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(RuneTableRecipe::getOutput)
    ).apply(instance, RuneTableRecipe::new));

    private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
        return Codecs.validate(Codecs.validate(
                delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
        ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
    }

    @Override
    public Codec<RuneTableRecipe> codec() {
        return CODEC;
    }

    @Override
    public RuneTableRecipe read(PacketByteBuf buf) {
        String key = buf.readString();
        int experience = buf.readInt();
        int ingredientSize = buf.readVarInt();
        DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredientSize, Ingredient.EMPTY);
        inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));
        ItemStack output = buf.readItemStack();

        return new RuneTableRecipe(key, inputs, experience, output);
    }

    @Override
    public void write(PacketByteBuf buf, RuneTableRecipe recipe) {
        buf.writeString(recipe.getKey());
        buf.writeInt(recipe.getExperience());
        buf.writeVarInt(recipe.getIngredients().size());
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.write(buf);
        }
        buf.writeItemStack(recipe.getResult(null));
    }
}

