package com.github.faxundo.old_legends.enchantment.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.enchantment.OLGenericEnchantment;
import com.github.faxundo.old_legends.entity.custom.Vengeful;
import com.github.faxundo.old_legends.item.custom.awake.EmeraldMourningAwake;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class VengeanceEnchantment extends OLGenericEnchantment {

    public VengeanceEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void useAbility(PlayerEntity player, ItemStack stack) {
        World world = player.getWorld();
        if (world.isClient) {
            return;
        }

        if (player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
            return;
        }

        ItemStack abilityStack = OLHelper.getAbilityItemStack(player, stack);
        int amount = EnchantmentHelper.getLevel(this, stack);

        for (int i = 1; i <= amount; i++) {
            int randomNumber = (int) OLHelper.getRandomNumber(1, 100);
            Vengeful vengeful = new Vengeful(EntityType.ZOMBIE_VILLAGER, world);
            if (randomNumber <= 60) {
                vengeful.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.IRON_SWORD));
                vengeful.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            } else if (randomNumber > 60 && randomNumber <= 90) {
                vengeful.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BOW));
                vengeful.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.ARROW));
                vengeful.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            } else if (randomNumber > 90 && randomNumber <= 100) {
                vengeful.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.NETHERITE_AXE));
            }
            vengeful.setOwner(player);
            vengeful.setPosition(
                    player.getX() + OLHelper.getRandomNumber(0, 4),
                    player.getY() + 1,
                    player.getZ() - OLHelper.getRandomNumber(0, 4));

            player.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, SoundCategory.PLAYERS, 5.0f, 0f);
            OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.LARGE_SMOKE, vengeful.getBlockX(), vengeful.getBlockY() - 0.2, vengeful.getBlockZ(),
                    0, 0.1, 0);
            OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.SOUL, vengeful.getBlockX(), vengeful.getBlockY() - 0.3, vengeful.getBlockZ(),
                    0.1, 0.1, 0.1);

            world.spawnEntity(vengeful);
        }

        if (!(stack.getItem() instanceof EmeraldMourningAwake)) {
            player.getItemCooldownManager().set(stack.getItem(), OldLegends.CONFIG.enchantment.vengeanceCooldown);
            abilityStack.damage((abilityStack.getMaxDamage() * OldLegends.CONFIG.enchantment.vengeanceDurability) / 100,
                    player, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                    });
        }
    }
}
