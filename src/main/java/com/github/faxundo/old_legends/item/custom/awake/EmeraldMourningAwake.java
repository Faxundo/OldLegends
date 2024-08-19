package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.enchantment.EnchantmentInit;
import com.github.faxundo.old_legends.entity.custom.Vengeful;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EmeraldMourningAwake extends EmeraldMourning implements Ability {

    private int cooldown;
    private int durabilityConsumed;


    public EmeraldMourningAwake(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(EmeraldMourning.createAttributeModifiers(ToolMaterials.NETHERITE, 4, -2.4f)));
        setAwake(true);
    }

    public void useAbility(PlayerEntity player) {
        World world = player.getWorld();
        if (!world.isClient) {

            if (player.getItemCooldownManager().isCoolingDown(this)) {
                return;
            }

            ItemStack abilityStack = OLHelper.getAbilityItemStack(player, this.getDefaultStack());
            this.cooldown = OldLegends.CONFIG.emeraldMourning.cooldown;
            this.durabilityConsumed = OldLegends.CONFIG.emeraldMourning.consumeDurability;

            abilityStack.damage((this.getDefaultStack().getMaxDamage() * durabilityConsumed) / 100, player, EquipmentSlot.MAINHAND);

            player.getItemCooldownManager().set(this, cooldown);

            player.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, 5.0f, 0f);

            for (int i = 1; i <= 4; i++) {
                Vengeful vengeful = new Vengeful(EntityType.ZOMBIE_VILLAGER, world);
                vengeful.setPosition(
                        player.getX() + OLHelper.getRandomNumber(0, 4),
                        player.getY() + 1,
                        player.getZ() - OLHelper.getRandomNumber(0, 4));
                if (vengeful.getOwnerUuid().isEmpty()) {
                    vengeful.setOwner(player);
                }

                vengeanceEnchantment(player, abilityStack, vengeful);

                for (int j = 1; j < 4; j++) {
                    OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.LARGE_SMOKE, vengeful.getBlockX(), vengeful.getBlockY() - 0.2, vengeful.getBlockZ(),
                            0, 0.1, 0);
                    OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.SOUL, vengeful.getBlockX(), vengeful.getBlockY() - 0.3, vengeful.getBlockZ(),
                            0.1, 0.1, 0.1);
                }

                world.spawnEntity(vengeful);
            }
        }
    }

    public void vengeanceEnchantment(PlayerEntity player, ItemStack stack, Vengeful vengeful) {
        ItemEnchantmentsComponent listEnchantments = EnchantmentHelper.getEnchantments(stack);
        for (RegistryEntry<Enchantment> enchantment : listEnchantments.getEnchantments()) {
            if (enchantment.equals(EnchantmentInit.VENGEANCE_KEY)) {

                DynamicRegistryManager registryManager = player.getWorld().getRegistryManager();

                RegistryKey<Enchantment> vengeanceKey = RegistryKey.of(RegistryKeys.ENCHANTMENT, OldLegends.identifier("Vengeance"));

                RegistryEntry.Reference<Enchantment> vengeanceReference = registryManager.get(RegistryKeys.ENCHANTMENT).entryOf(vengeanceKey);

                int level = EnchantmentHelper.getLevel(vengeanceReference, stack);
                switch (level) {
                    case 1:
                        vengeful.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
                        vengeful.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
                        vengeful.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
                    case 2:
                        vengeful.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
                        vengeful.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
                        vengeful.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                    case 3:
                        vengeful.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.DIAMOND_SWORD));
                        vengeful.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
                        vengeful.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                        vengeful.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
                        vengeful.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
                }
            }
        }
    }
}
