package com.github.faxundo.old_legends.event;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import com.github.faxundo.old_legends.util.OLHelpers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class ShieldBlockHandler implements ShieldBlockCallback {

    private final int maxCharges = OldLegends.CONFIG.swallowsStorm.swallowsStormMaxCharges;
    private final int maxChargesAwake = OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeMaxCharges;
    private final int electrifiedDamage = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedDamage;
    private final double electrifiedStrength = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedStrength;
    private final double electrifiedAwakeStrength = OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeElectrifiedStrength;

    @Override
    public ActionResult block(LivingEntity defender, DamageSource source, float amount, Hand hand, ItemStack shield) {
        if (!shield.hasNbt()) {
            return ActionResult.PASS;
        }

        NbtCompound nbtData = shield.getNbt();

        if (!nbtData.contains(OldLegends.MOD_ID)
                && shield.getDamage() < shield.getMaxDamage()) {
            return ActionResult.PASS;
        }

        int charges = nbtData.getInt(OldLegends.MOD_ID);

        if (shield.getItem() instanceof SwallowsStormItem swallowsStormItem) {
            int damage = swallowsStormItem.getCharges(shield) / electrifiedDamage;

            if (charges == 30) {
                OLHelpers.spawnParticle(defender.getWorld(), ParticleTypes.ENCHANT, defender.getX(), defender.getY(), defender.getZ(),
                        0.5, 0, 0.5);
            }
            if (source.getAttacker() instanceof LivingEntity attacker) {
                attacker.damage(attacker.getDamageSources().lightningBolt(), damage + 1);
                if (swallowsStormItem.getAwake()) {
                    attacker.takeKnockback(electrifiedAwakeStrength, attacker.getX(), attacker.getZ());
                } else {
                    attacker.takeKnockback(electrifiedStrength, attacker.getX(), attacker.getZ());
                }
            }
            if (source.getName().equals("lightningBolt")) {
                defender.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 5.0f, 0f);
                OLHelpers.spawnParticle(defender.getWorld(), ParticleTypes.ELECTRIC_SPARK, defender.getX(), defender.getY(), defender.getZ(),
                        0.5, 0, 0.5);
                if (swallowsStormItem.getAwake()) {
                    nbtData.putInt(OldLegends.MOD_ID, maxChargesAwake);
                } else {
                    nbtData.putInt(OldLegends.MOD_ID, maxCharges);
                }
            } else {
                if (source.getAttacker() instanceof Entity) {
                    if (charges < maxChargesAwake && swallowsStormItem.getAwake()) {
                        nbtData.putInt(OldLegends.MOD_ID, charges + 1);
                    } else if (charges < maxCharges) {
                        nbtData.putInt(OldLegends.MOD_ID, charges + 1);

                    }
                }
            }
        }
        return ActionResult.PASS;
    }
}
