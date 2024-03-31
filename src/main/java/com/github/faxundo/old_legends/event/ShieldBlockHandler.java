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

    private int maxCharges = OldLegends.CONFIG.swallowsStorm.swallowsStormMaxCharges;
    private int maxChargesAwake = OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeMaxCharges;
    private int electrifiedDamage = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedDamage;
    private double electrifiedStrength = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedStrength;
    private double electrifiedAwakeStrength = OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeElectrifiedStrength;

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
            int damage = OLHelper.getCharges(shield) / electrifiedDamage;

            if (charges == maxCharges || charges == maxChargesAwake) {
                DustParticleEffect dustParticle = new DustParticleEffect(new Vector3f(73.0f, 59.0f, 90.0f), 1.0f);
                OLHelper.spawnParticle(defender.getWorld(), dustParticle, defender.getX()+1, defender.getY(), defender.getZ()+1,
                        0.5, 0, 0.5);
            }

            if (source.getName().equals("arrow")) {
                return ActionResult.PASS;
            }

            if (source.getAttacker() instanceof LivingEntity attacker) {
                if (swallowsStormItem.isAwake()) {
                    damage = OLHelper.getCharges(shield) / (electrifiedDamage / 2);
                    attacker.damage(attacker.getDamageSources().lightningBolt(), damage);
                    attacker.takeKnockback(electrifiedAwakeStrength, -attacker.getX(), -attacker.getZ());
                } else {
                    attacker.damage(attacker.getDamageSources().lightningBolt(), damage);
                    attacker.takeKnockback(electrifiedStrength, -attacker.getX(), -attacker.getZ());
                }
            }
            //Recharge with Lightning Bolt
            if (source.getName().equals("lightningBolt")) {
                OLHelper.spawnParticle(defender.getWorld(), ParticleTypes.ELECTRIC_SPARK, defender.getX(), defender.getY(), defender.getZ(),
                        0.5, 0, 0.5);
                if (swallowsStormItem.isAwake()) {
                    nbtData.putInt(OldLegends.MOD_ID, maxChargesAwake);
                } else {
                    nbtData.putInt(OldLegends.MOD_ID, maxCharges);
                }

            //Obtain Charges
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
