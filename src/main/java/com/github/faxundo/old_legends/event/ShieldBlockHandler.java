package com.github.faxundo.old_legends.event;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.joml.Vector3f;

public class ShieldBlockHandler implements ShieldBlockCallback {

    @Override
    public ActionResult block(LivingEntity defender, DamageSource source, float amount, Hand hand, ItemStack shield) {

        if (!shield.hasNbt()) {
            return ActionResult.PASS;
        }

        NbtCompound nbtData = shield.getOrCreateNbt();

        if (!nbtData.contains(OldLegends.MOD_ID)) {
            return ActionResult.PASS;
        }

        int maxCharges = OldLegends.CONFIG.swallowsStorm.swallowsStormMaxCharges;
        int maxChargesAwake = OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeMaxCharges;
        int electrifiedDamage = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedDamage;
        double electrifiedStrength = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedStrength;
        double electrifiedAwakeStrength = OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeElectrifiedStrength;

        int charges = nbtData.getInt(OldLegends.MOD_ID);

        if (shield.getItem() instanceof SwallowsStormItem swallowsStormItem) {

            if (charges == maxCharges || charges == maxChargesAwake) {
                DustParticleEffect dustParticle = new DustParticleEffect(new Vector3f(73.0f, 59.0f, 90.0f), 1.0f);
                OLHelper.spawnParticle(defender.getWorld(), dustParticle, defender.getX() + 1, defender.getY(), defender.getZ() + 1,
                        0.5, 0, 0.5);
            }

            if (source.getName().equals("arrow")) {
                return ActionResult.PASS;
            }

            if (source.getAttacker() instanceof LivingEntity attacker) {
                int damage = swallowsStormItem.isAwake() ? (charges / (electrifiedDamage / 2) + 1) : (charges / electrifiedDamage) + 1;
                double strength = swallowsStormItem.isAwake() ? electrifiedAwakeStrength : electrifiedStrength;
                attacker.takeKnockback(strength, -attacker.getX(), -attacker.getZ());
                attacker.damage(attacker.getDamageSources().lightningBolt(), damage);
            }

            if (source.getName().equals("lightningBolt")) {
                OLHelper.spawnParticle(defender.getWorld(), ParticleTypes.ELECTRIC_SPARK, defender.getX(), defender.getY(), defender.getZ(),
                        0.5, 0, 0.5);
                nbtData.putInt(OldLegends.MOD_ID, swallowsStormItem.isAwake() ? maxChargesAwake : maxCharges);

            } else {
                if (source.getAttacker() instanceof Entity) {
                    if (charges < maxChargesAwake && swallowsStormItem.isAwake()) {
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

