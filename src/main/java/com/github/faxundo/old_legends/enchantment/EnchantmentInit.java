package com.github.faxundo.old_legends.enchantment;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.enchantment.effects.SummonAlliesEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EnchantmentInit {
    public static final RegistryKey<Enchantment> VENGEANCE_KEY =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, OldLegends.identifier("vengeance"));

    public static final MapCodec<SummonAlliesEnchantmentEffect> SUMMON_ALLIES = register("summon_allies", SummonAlliesEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect>MapCodec<T> register (String name, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, OldLegends.identifier(name), codec);
    }
}
