package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericSword;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;

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
                target.damage(attacker.getWorld().getDamageSources().generic(), (this.getAttackDamage() * OldLegends.CONFIG.emeraldMourningPercentage)/100);
                if (target.isDead() && attacker.isPlayer()) {
                    target.dropStack(new ItemStack(Items.EMERALD));
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
