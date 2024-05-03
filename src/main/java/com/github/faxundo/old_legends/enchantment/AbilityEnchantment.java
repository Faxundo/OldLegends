package com.github.faxundo.old_legends.enchantment;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface AbilityEnchantment {

    void useAbility(PlayerEntity player, ItemStack stack);

}
