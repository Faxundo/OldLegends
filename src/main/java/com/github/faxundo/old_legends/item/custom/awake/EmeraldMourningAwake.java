package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.enchantment.custom.VengeanceEnchantment;
import com.github.faxundo.old_legends.entity.custom.Vengeful;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class EmeraldMourningAwake extends EmeraldMourning implements Ability{

    private int cooldown;
    private int durabilityConsumed;


    public EmeraldMourningAwake(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        setAwake(true);
    }

    public void useAbility(PlayerEntity player) {
        World world = player.getWorld();
        if (!world.isClient) {

            if (player.getItemCooldownManager().isCoolingDown(this)) {
                return;
            }

            ItemStack abilityStack = OLHelper.getAbilityItemStack(player, this.getDefaultStack());
            cooldown = OldLegends.CONFIG.emeraldMourning.cooldown;
            durabilityConsumed = OldLegends.CONFIG.emeraldMourning.consumeDurability;

            abilityStack.damage((this.getMaxDamage() * durabilityConsumed) / 100,
                    player, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                    });

            player.getItemCooldownManager().set(this, cooldown);

            player.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, SoundCategory.PLAYERS, 5.0f, 0f);

            for (int i = 1; i <= 4; i++) {
                Vengeful vengeful = new Vengeful(EntityType.ZOMBIE_VILLAGER, world);
                vengeful.setPosition(
                        player.getX() + OLHelper.getRandomNumber(0, 4),
                        player.getY() + 1,
                        player.getZ() - OLHelper.getRandomNumber(0, 4));
                if (vengeful.getOwnerUuid().isEmpty()) {
                    vengeful.setOwner(player);
                }

                vengeanceEnchantment(abilityStack, vengeful);

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

    public void vengeanceEnchantment (ItemStack stack, Vengeful vengeful) {
        Map<Enchantment, Integer> listEnchantments = EnchantmentHelper.get(stack);
        for (Enchantment enchantment : listEnchantments.keySet()) {
            if (enchantment instanceof VengeanceEnchantment vengeanceEnchantment) {
                int level = EnchantmentHelper.getLevel(vengeanceEnchantment,stack);
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
