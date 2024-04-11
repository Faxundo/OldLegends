package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericShield;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static com.github.faxundo.old_legends.OldLegends.CONFIG;

public class SwallowsStormItem extends OLGenericShield {

    private int durabilityConsumed;
    private int stormHeal;
    private int stormHealAwake;
    private int stormHealLost;
    private int maxCharges;
    private int maxChargesAwake;

    public SwallowsStormItem(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings, coolDownTicks, enchantability, repairItemTag);
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
        this.stormHeal = CONFIG.swallowsStorm.stormHeal;
        this.stormHealAwake = CONFIG.swallowsStorm.stormHealAwake;
        this.stormHealLost = CONFIG.swallowsStorm.stormHealLost;
        this.maxCharges = CONFIG.swallowsStorm.maxCharges;
        this.maxChargesAwake = CONFIG.swallowsStorm.maxChargesAwake;
        if (isAwake()) {
            setMaxCharges(maxChargesAwake);
        } else {
            setMaxCharges(maxCharges);
        }

        ItemStack item = user.getStackInHand(hand);

        if (user.isUsingItem() && item.isOf(OLItem.SWALLOWS_STORM)) {
            return TypedActionResult.success(item, false);
        }

        NbtCompound nbtData = item.getOrCreateNbt();

        if (!nbtData.contains(OldLegends.MOD_ID)) {
            nbtData.putInt(OldLegends.MOD_ID, 0);
        }


        int charges = nbtData.getInt(OldLegends.MOD_ID);

        if (user.getHealth() <= stormHealLost) {

            if (isAwake()) {
                if (charges == maxChargesAwake / 2 || charges == maxChargesAwake) {
                    user.heal(stormHealAwake);
                    int updatedCharges = (charges == getMaxCharges() / 2) ? 0 : charges - (getMaxCharges() / 2);
                    nbtData.putInt(OldLegends.MOD_ID, updatedCharges);
                    healEffects(user, item);
                }
            } else if (charges == maxCharges) {
                user.heal(stormHeal);
                nbtData.putInt(OldLegends.MOD_ID, 0);
                healEffects(user, item);
            }

        }
        return super.use(world, user, hand);
    }

    public void healEffects(PlayerEntity player, ItemStack item) {
        player.playSound(OLSound.SWALLOWS_STORM_HEAL, SoundCategory.PLAYERS, 5.0f, 0f);
        OLHelperParticle.spawnParticle(player.getWorld(), ParticleTypes.ELECTRIC_SPARK, player.getX() + 0.5, player.getY() + 1, player.getZ() + 0.5,
                0.5, 0, 0.5);
        item.damage((item.getMaxDamage() * durabilityConsumed) / 100,
                player, (e) -> e.sendEquipmentBreakStatus(OLHelper.getItemSlot(player, item.getItem())));
    }

}

