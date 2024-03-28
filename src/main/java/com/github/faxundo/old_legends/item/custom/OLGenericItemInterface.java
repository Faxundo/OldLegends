package com.github.faxundo.old_legends.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface OLGenericItemInterface {

    Text getName (ItemStack itemStack);
    void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context);
}
