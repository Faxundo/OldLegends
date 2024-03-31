package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericShield;
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

    private int durabilityConsumed = OldLegends.CONFIG.swallowsStorm.swallowsStormPercentageConsumeDurability;
    private int stormHeal = OldLegends.CONFIG.swallowsStorm.swallowsStormHeal;
    private int stormHealAwake = OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeHeal;
    private int stormHealLost = OldLegends.CONFIG.swallowsStorm.swallowsStormHealLost;

    public SwallowsStormItem(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings, coolDownTicks, enchantability, repairItemTag);
        setId("swallows_storm");
        setAwake(false);
        setUseCharges(true);
        setMaxCharges(CONFIG.swallowsStorm.swallowsStormMaxCharges);
        setAmountPassives(3);
    }

    public int getDurabilityConsumed() {
        return durabilityConsumed;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item = user.getStackInHand(hand);
        if (item.hasNbt()) {
            if (!item.getNbt().contains(OldLegends.MOD_ID)) {
                item.getNbt().putInt(OldLegends.MOD_ID, 0);
            }
            ItemStack shield = user.getStackInHand(hand);
            NbtCompound nbtData = shield.getNbt();
            int charges = nbtData.getInt(OldLegends.MOD_ID);
            if (user.getHealth() <= stormHealLost) {

                user.playSound(OLSound.SWALLOWS_STORM_HEAL, SoundCategory.PLAYERS, 5.0f, 0f);
                OLHelper.spawnParticle(user.getWorld(), ParticleTypes.ELECTRIC_SPARK, user.getX(), user.getY(), user.getZ(),
                        0.5, 0, 0.5);
                shield.damage((shield.getMaxDamage() * durabilityConsumed) / 100,
                        user, (e) -> {
                            e.sendEquipmentBreakStatus(OLHelper.getItemSlot(user, shield.getItem()));
                        });


                if (isAwake()) {
                    if (charges == this.getMaxCharges() / 2 || charges == this.getMaxCharges()) {
                        user.heal(stormHealAwake);
                        int updatedCharges = (charges == this.getMaxCharges() / 2) ? 0 : charges - (this.getMaxCharges() / 2);
                        System.out.println(updatedCharges);
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

