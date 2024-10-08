package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.generic.OLGenericRune;
import com.github.faxundo.old_legends.item.generic.OLGenericSword;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;

public class EmeraldMourning extends OLGenericSword {

    public static final String ABILITY_NAME_1 = "tooltip.old_legends.emerald_mourning_name_1";
    public static final String ABILITY_1 = "tooltip.old_legends.emerald_mourning_1";
    public static final String ABILITY_NAME_2 = "tooltip.old_legends.emerald_mourning_name_2";
    public static final String ABILITY_2 = "tooltip.old_legends.emerald_mourning_2";

    private int damageIllager;
    private int damageIllagerAwake;

    public EmeraldMourning(ToolMaterial toolMaterial, Item.Settings settings) {
        super(toolMaterial, settings.attributeModifiers(EmeraldMourning.createAttributeModifiers(ToolMaterials.NETHERITE,3,-2.4f)));
        setId("emerald_mourning");
        setAwake(false);
        setAmountPassives(2);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.getItem() instanceof OLGenericRune && ingredient.getTranslationKey().contains("death_rune");
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        damageIllager = OldLegends.CONFIG.emeraldMourning.percentageIllager;
        damageIllagerAwake = OldLegends.CONFIG.emeraldMourning.percentageIllagerAwake;

        if (!attacker.getWorld().isClient()) {
            if (target instanceof IllagerEntity) {
                if (isAwake()) {
                    target.damage(attacker.getWorld().getDamageSources().generic(), (getMaterial().getAttackDamage() * damageIllagerAwake) / 100);
                } else {
                    target.damage(attacker.getWorld().getDamageSources().generic(), (getMaterial().getAttackDamage() * damageIllager) / 100);
                }
                if (attacker.isPlayer()) {
                    if (target.isDead()) {
                        OLHelperParticle.spawnParticle(target.getWorld(), ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getY(), target.getZ(),
                                0.5, 0, 0.5);
                        if (isAwake()) {
                            for (int i = 1; i <= OLHelper.getRandomNumber(1, 3); i++) {
                                OLHelperParticle.spawnParticle(target.getWorld(), ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getY(), target.getZ(),
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
