package com.github.faxundo.old_legends.enchantment;

import com.github.faxundo.old_legends.OldLegends;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.github.faxundo.old_legends.OldLegends.LOGGER;
import static com.github.faxundo.old_legends.OldLegends.MOD_NAME;

public class OLEnchantment {

    public static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, OldLegends.identifier(name), enchantment);
    }
    public static void registerModEnchantments() {
        LOGGER.info(">>> Registering " + MOD_NAME + " enchantments.");
    }
}
