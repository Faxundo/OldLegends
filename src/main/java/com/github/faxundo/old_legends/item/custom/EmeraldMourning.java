package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericSword;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;

public class EmeraldMourning extends OLGenericSword {

    private final int damageIllager = OldLegends.CONFIG.emeraldMourning.emeraldMourningPercentageIllager;
    private final int damageIllagerAwake = OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakePercentageIllager;

    public EmeraldMourning(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        setId("emerald_mourning");
        setAwake(false);
        setAmountPassives(2);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            if (target instanceof IllagerEntity) {
                if (isAwake()) {
                    target.damage(attacker.getWorld().getDamageSources().generic(), (this.getAttackDamage() * damageIllagerAwake) / 100);
                } else {
                    target.damage(attacker.getWorld().getDamageSources().generic(), (this.getAttackDamage() * damageIllager) / 100);
                }
                if (attacker.isPlayer()) {
                    if (target.isDead()) {
                        OLHelper.spawnParticle(target.getWorld(), ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getY(), target.getZ(),
                                0.5, 0, 0.5);
                        if (isAwake()) {
                            for (int i = 1; i <= OLHelper.getRandomNumber(1, 3); i++) {
                                OLHelper.spawnParticle(target.getWorld(), ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getY(), target.getZ(),
                                        0.5, 0, 0.5);
                                target.dropStack(new ItemStack(Items.EMERALD));
                            }
                        } else {
                            target.dropStack(new ItemStack(Items.EMERALD));
                        }
                    }
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
