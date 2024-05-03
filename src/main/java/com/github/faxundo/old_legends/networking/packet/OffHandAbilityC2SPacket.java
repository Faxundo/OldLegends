package com.github.faxundo.old_legends.networking.packet;

import com.github.faxundo.old_legends.enchantment.OLGenericEnchantment;
import com.github.faxundo.old_legends.item.custom.awake.Ability;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Map;

public class OffHandAbilityC2SPacket {

    public static void receive (MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        ItemStack stack = player.getOffHandStack();
        Item item = stack.getItem();
        if (item instanceof Ability abilityUser) {
            abilityUser.useAbility(player);
        }
        Map<Enchantment, Integer> listEnchantments = EnchantmentHelper.get(stack);
        for (Enchantment enchantment : listEnchantments.keySet()) {
            if (enchantment instanceof OLGenericEnchantment olGenericEnchantment) {
                olGenericEnchantment.useAbility(player, stack);
                break;
            }
        }
    }
}
