package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericSword;
import com.github.faxundo.old_legends.util.OLHelpers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;

public class EmeraldMourningSword extends OLGenericSword {

    public EmeraldMourningSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        setId("emerald_mourning");
        setAwake(false);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            if (target instanceof IllagerEntity) {
                target.damage(attacker.getWorld().getDamageSources().generic(), (this.getAttackDamage() * OldLegends.CONFIG.emeraldMourning.emeraldMourningPercentageIllager)/100);
                if (attacker.isPlayer()) {
                    if (target.isDead()) {
                        OLHelpers.spawnParticle(target.getWorld(), ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getY(), target.getZ(),
                                0.5, 0, 0.5);
                        target.dropStack(new ItemStack(Items.EMERALD));
                    }
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
