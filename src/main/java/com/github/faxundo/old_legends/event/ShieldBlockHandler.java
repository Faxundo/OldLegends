package com.github.faxundo.old_legends.event;

import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class ShieldBlockHandler implements ShieldBlockCallback {

    private final int maxCharges = OldLegends.CONFIG.swallowsStorm.swallowsStormMaxCharges;
    private final int electrifiedDamage = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedDamage;
    private final double electrifiedStrength = OldLegends.CONFIG.swallowsStorm.swallowsStormElectrifiedStrength;
    private final int stormHeal = OldLegends.CONFIG.swallowsStorm.swallowsStormHeal;

    @Override
    public ActionResult block(LivingEntity defender, DamageSource source, float amount, Hand hand, ItemStack shield) {
        if (!shield.hasNbt()) {
            return ActionResult.PASS;
        }

        NbtCompound nbtData = shield.getNbt();

        if (!nbtData.contains("old_legends")
                && shield.getDamage() < shield.getMaxDamage()) {
            return ActionResult.PASS;
        }

        int charges = nbtData.getInt("old_legends");

        if (shield.getItem() instanceof SwallowsStormItem swallowsStormItem) {
            int damage = swallowsStormItem.getCharges(shield) / electrifiedDamage;

            if (source.getAttacker() instanceof LivingEntity attacker) {
                attacker.damage(attacker.getDamageSources().lightningBolt(), damage + 1);
                attacker.takeKnockback(electrifiedStrength, attacker.getX(), attacker.getZ());
            }
            if (source.getName().equals("lightningBolt")) {
                nbtData.putInt("old_legends", maxCharges);
                defender.heal(stormHeal);
            } else {
                if (source.getAttacker() instanceof Entity) {
                    if (charges < maxCharges) {
                        nbtData.putInt("old_legends", charges + 1);
                    }
                }
            }
        }
        return ActionResult.PASS;
    }
}
