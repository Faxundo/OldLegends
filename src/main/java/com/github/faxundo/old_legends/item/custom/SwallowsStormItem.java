package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLGenericShield;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static com.github.faxundo.old_legends.OldLegends.CONFIG;

public class SwallowsStormItem extends OLGenericShield {

    private final int durabilityConsumed = OldLegends.CONFIG.swallowsStorm.swallowsStormPercentageConsumeDurability;
    private final int stormHeal = OldLegends.CONFIG.swallowsStorm.swallowsStormHeal;
    private final int stormHealLost = OldLegends.CONFIG.swallowsStorm.swallowsStormHealLost;

    public SwallowsStormItem(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings, coolDownTicks, enchantability, repairItemTag);
        setId("swallows_storm");
        setAwake(false);
        setUseCharges(true);
        setMaxCharges(CONFIG.swallowsStorm.swallowsStormMaxCharges);
        setAmountPassives(3);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item = user.getStackInHand(hand);
        if (item.hasNbt()) {
            if (!item.getNbt().contains("old_legends")) {
                item.getNbt().putInt("old_legends", 0);
            }
            ItemStack shield = user.getStackInHand(hand);
            NbtCompound nbtData = shield.getNbt();
            int charges = nbtData.getInt("old_legends");
            if (charges == this.getMaxCharges() && user.getHealth() <= stormHealLost) {
                shield.damage((shield.getMaxDamage() * durabilityConsumed) / 100,
                        user, (e) -> {
                            e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                        });
                user.heal(stormHeal);
                nbtData.putInt("old_legends", 0);
            }
        }
        return super.use(world, user, hand);
    }

    public int getCharges(ItemStack itemStack) {
        if (itemStack.hasNbt()) {
            if (itemStack.getNbt().contains("old_legends")) {
                return itemStack.getNbt().getInt("old_legends");
            }
        }
        return 0;
    }
}

