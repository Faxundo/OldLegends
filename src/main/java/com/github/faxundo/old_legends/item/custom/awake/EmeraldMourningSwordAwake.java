package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericSword;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;

public class EmeraldMourningSwordAwake extends OLGenericSword {
    public EmeraldMourningSwordAwake(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        setId("emerald_mourning_awake");
        setAwake(true);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            if (target instanceof IllagerEntity) {
                target.damage(attacker.getWorld().getDamageSources().generic(), (this.getAttackDamage() * OldLegends.CONFIG.emeraldMourningAwakePercentage) / 100);
                if (target.isDead() && attacker.isPlayer()) {
                    double randomNumber = Math.floor(Math.random() * (3 - 1 + 1) + 1);
                    for (int i = 1; i <= randomNumber; i++) {
                        target.dropStack(new ItemStack(Items.EMERALD));
                    }
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getPlayer().getWorld().isClient()) {
        }
        return ActionResult.SUCCESS;
    }
}
