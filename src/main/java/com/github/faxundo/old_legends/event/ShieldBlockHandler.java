package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.event.callback.ShieldBlockCallback;
import com.github.faxundo.old_legends.item.custom.SwallowsStorm;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import org.joml.Vector3f;

public class ShieldBlockHandler implements ShieldBlockCallback {

    @Override
    public ActionResult block(LivingEntity defender, DamageSource source, ItemStack shield) {
        if (!shield.contains(OLDataComponent.CHARGES)) return ActionResult.PASS;

        int maxCharges = OldLegends.CONFIG.swallowsStorm.maxCharges;
        int maxChargesAwake = OldLegends.CONFIG.swallowsStorm.maxChargesAwake;
        int electrifiedDamage = OldLegends.CONFIG.swallowsStorm.electrifiedDamage;
        double electrifiedStrength = OldLegends.CONFIG.swallowsStorm.electrifiedStrength;
        double electrifiedAwakeStrength = OldLegends.CONFIG.swallowsStorm.electrifiedStrengthAwake;

        int charges = shield.get(OLDataComponent.CHARGES);

        if (shield.getItem() instanceof SwallowsStorm swallowsStormItem) {

            if (charges == maxCharges || charges == maxChargesAwake) {
                DustParticleEffect dustParticle = new DustParticleEffect(new Vector3f(73.0f, 59.0f, 90.0f), 1.0f);
                OLHelperParticle.spawnParticle(defender.getWorld(), dustParticle, defender.getX() + 1, defender.getY(), defender.getZ() + 1,
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
                OLHelperParticle.spawnParticle(defender.getWorld(), ParticleTypes.ELECTRIC_SPARK, defender.getX(), defender.getY(), defender.getZ(),
                        0.5, 0, 0.5);
                shield.set(OLDataComponent.CHARGES, swallowsStormItem.isAwake() ? maxChargesAwake : maxCharges);

            } else {
                if (source.getAttacker() instanceof Entity) {
                    if (charges < maxChargesAwake && swallowsStormItem.isAwake()) {
                        shield.set(OLDataComponent.CHARGES, charges + 2);
                    } else if (charges < maxCharges) {
                        shield.set(OLDataComponent.CHARGES, charges + 1);

                    }
                }
            }
        }
        return ActionResult.PASS;
    }
}

