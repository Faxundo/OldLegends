package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.generic.OLGenericShield;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static com.github.faxundo.old_legends.OldLegends.CONFIG;

public class SwallowsStorm extends OLGenericShield {

    public static final String CHARGES = "tooltip.old_legends.swallows_storm.charges";
    public static final String ABILITY_NAME_1 = "tooltip.old_legends.swallows_storm_name_1";
    public static final String ABILITY_1 = "tooltip.old_legends.swallows_storm_1";
    public static final String ABILITY_NAME_2 = "tooltip.old_legends.swallows_storm_name_2";
    public static final String ABILITY_2 = "tooltip.old_legends.swallows_storm_2";
    public static final String ABILITY_NAME_3 = "tooltip.old_legends.swallows_storm_name_3";
    public static final String ABILITY_3 = "tooltip.old_legends.swallows_storm_3";

    private int durabilityConsumed;
    private int stormHeal;
    private int stormHealAwake;
    private int stormHealLost;
    private int maxCharges;
    private int maxChargesAwake;

    public SwallowsStorm(Settings settings) {
        super(settings.component(OLDataComponent.CHARGES,0));
        setId("swallows_storm");
        setAwake(false);
        setUseCharges(true);
        setAmountPassives(3);
    }

    public int getDurabilityConsumed() {
        return durabilityConsumed;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        this.durabilityConsumed = CONFIG.swallowsStorm.consumeDurability;
        this.stormHeal = CONFIG.swallowsStorm.heal;
        this.stormHealAwake = CONFIG.swallowsStorm.stormHealAwake;
        this.stormHealLost = CONFIG.swallowsStorm.healLost;
        this.maxCharges = CONFIG.swallowsStorm.maxCharges;
        this.maxChargesAwake = CONFIG.swallowsStorm.maxChargesAwake;
        if (isAwake()) {
            setMaxCharges(maxChargesAwake);
        } else {
            setMaxCharges(maxCharges);
        }

        ItemStack itemStack = user.getStackInHand(hand);

        if (user.isUsingItem() && itemStack.isOf(OLItem.SWALLOWS_STORM)) {
            return TypedActionResult.success(itemStack, false);
        }

        int charges = itemStack.get(OLDataComponent.CHARGES);

        if (user.getHealth() <= stormHealLost) {

            if (isAwake()) {
                if (charges == maxChargesAwake / 2 || charges == maxChargesAwake) {
                    user.heal(stormHealAwake);
                    int updatedCharges = (charges == getMaxCharges() / 2) ? 0 : charges - (getMaxCharges() / 2);
                    itemStack.set(OLDataComponent.CHARGES, updatedCharges);
                    healEffects(user, itemStack);
                }
            } else if (charges == maxCharges) {
                user.heal(stormHeal);
                itemStack.set(OLDataComponent.CHARGES, 0);
                healEffects(user, itemStack);
            }

        }
        return super.use(world, user, hand);
    }

    public void healEffects(PlayerEntity player, ItemStack item) {
        player.playSound(OLSound.SWALLOWS_STORM_HEAL, 5.0f, 0f);
        OLHelperParticle.spawnParticle(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, player.getX() + 0.5, player.getY() + 1, player.getZ() + 0.5,
                0.5, 0, 0.5);
        item.damage((item.getMaxDamage() * durabilityConsumed) / 100, player, OLHelper.getItemSlot(player, item.getItem()));
    }

}

