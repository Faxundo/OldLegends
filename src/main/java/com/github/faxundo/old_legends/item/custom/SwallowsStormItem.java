package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericShield;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.OLHelper;
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

        this.durabilityConsumed = CONFIG.swallowsStorm.swallowsStormPercentageConsumeDurability;
        this.stormHeal = CONFIG.swallowsStorm.swallowsStormHeal;
        this.stormHealAwake = CONFIG.swallowsStorm.swallowsStormAwakeHeal;
        this.stormHealLost = CONFIG.swallowsStorm.swallowsStormHealLost;
        setMaxCharges(CONFIG.swallowsStorm.swallowsStormMaxCharges);

        ItemStack item = user.getStackInHand(hand);

        if (user.isUsingItem() && item.isOf(OLItem.SWALLOWS_STORM)) {
            return TypedActionResult.success(item, false);
        }

        if (item.hasNbt()) {

            if (!item.getNbt().contains(OldLegends.MOD_ID)) {
                item.getNbt().putInt(OldLegends.MOD_ID, 0);
            }

            NbtCompound nbtData = item.getNbt();
            int charges = nbtData.getInt(OldLegends.MOD_ID);

            if (user.getHealth() <= stormHealLost) {

                user.playSound(OLSound.SWALLOWS_STORM_HEAL, SoundCategory.PLAYERS, 5.0f, 0f);
                OLHelper.spawnParticle(user.getWorld(), ParticleTypes.ELECTRIC_SPARK, user.getX(), user.getY(), user.getZ(),
                        0.5, 0, 0.5);

                item.damage((item.getMaxDamage() * durabilityConsumed) / 100,
                        user, (e) -> e.sendEquipmentBreakStatus(OLHelper.getItemSlot(user, item.getItem())));

                if (isAwake()) {
                    if (charges == this.getMaxCharges() / 2 || charges == this.getMaxCharges()) {
                        user.heal(stormHealAwake);
                        int updatedCharges = (charges == this.getMaxCharges() / 2) ? 0 : charges - (this.getMaxCharges() / 2);
                        nbtData.putInt(OldLegends.MOD_ID, updatedCharges);
                    }
                } else if (charges == this.getMaxCharges()) {
                    user.heal(stormHeal);
                    nbtData.putInt(OldLegends.MOD_ID, 0);
                }

            }
        }
        return super.use(world, user, hand);
    }


}

