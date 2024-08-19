package com.github.faxundo.old_legends.enchantment.effects;

import com.github.faxundo.old_legends.entity.custom.Vengeful;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public record SummonAlliesEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {

    public static final MapCodec<SummonAlliesEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(SummonAlliesEnchantmentEffect::amount)
            ).apply(instance, SummonAlliesEnchantmentEffect::new));

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (world.isClient) return;


//        if (player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
//            return;
//        }
//
//        ItemStack abilityStack = OLHelper.getAbilityItemStack(player, stack);
//        int amount = EnchantmentHelper.getLevel(this, stack);

        for (int i = 1; i <= 3; i++) {
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

//            vengeful.setOwner(player);
//            vengeful.setPosition(
//                    player.getX() + OLHelper.getRandomNumber(0, 4),
//                    player.getY() + 1,
//                    player.getZ() - OLHelper.getRandomNumber(0, 4));
//
//            player.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, 5.0f, 0f);

            OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.LARGE_SMOKE, vengeful.getBlockX(), vengeful.getBlockY() - 0.2, vengeful.getBlockZ(),
                    0, 0.1, 0);
            OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.SOUL, vengeful.getBlockX(), vengeful.getBlockY() - 0.3, vengeful.getBlockZ(),
                    0.1, 0.1, 0.1);

            world.spawnEntity(vengeful);
        }

//        if (!(stack.getItem() instanceof EmeraldMourningAwake)) {
//            player.getItemCooldownManager().set(stack.getItem(), OldLegends.CONFIG.enchantment.vengeanceCooldown);
//            abilityStack.damage((abilityStack.getMaxDamage() * OldLegends.CONFIG.enchantment.vengeanceDurability) / 100,
//                    player, (e) -> {
//                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
//                    });
//        }

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
