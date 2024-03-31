package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.util.OLHelper;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ServerTickHandler implements ServerTickCallback {

    private int stormChance = OldLegends.CONFIG.swallowsStorm.swallowsStormChance;
    private final StatusEffectInstance effectResistance = new StatusEffectInstance(StatusEffects.RESISTANCE, 40, 4);

    @Override
    public void tick(MinecraftServer server) {
        World world = server.getOverworld();
        if (world.isThundering()) {

            for (PlayerEntity player : world.getPlayers()) {

                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightning.setFireTicks(0);
                lightning.setOnFire(false);
                lightning.setOnFireFor(0);
                lightning.setPosition(x, y, z);

                if (OLHelper.hasItemInHand(player, OLItem.SWALLOWS_STORM)) {
                    ItemStack itemStack = OLHelper.getItemInHand(player, OLItem.SWALLOWS_STORM);
                    if (!itemStack.hasNbt()) {
                        return;
                    }
                    NbtCompound nbtData = itemStack.getNbt();
                    if (!nbtData.contains(OldLegends.MOD_ID)) {
                        return;
                    }


                    if (player.isBlocking()
                            && OLHelper.getRandomNumber(1, 100) < stormChance
                            && nbtData.getInt(OldLegends.MOD_ID) < OldLegends.CONFIG.swallowsStorm.swallowsStormMaxCharges
                            && player.getWorld().isSkyVisible(player.getBlockPos())
                            && !player.getAbilities().creativeMode) {

                        player.addStatusEffect(effectResistance);
                        world.spawnEntity(lightning);
                    }
                } else if (OLHelper.hasItemInHand(player, OLItem.SWALLOWS_STORM_AWAKE)) {
                    ItemStack itemStack = OLHelper.getItemInHand(player, OLItem.SWALLOWS_STORM_AWAKE);
                    if (!itemStack.hasNbt()) {
                        return;
                    }
                    NbtCompound nbtData = itemStack.getNbt();
                    if (!nbtData.contains(OldLegends.MOD_ID)) {
                        return;
                    }

                    if (player.isBlocking()
                            && OLHelper.getRandomNumber(1, 100) < stormChance
                            && nbtData.getInt(OldLegends.MOD_ID) < OldLegends.CONFIG.swallowsStorm.swallowsStormAwakeMaxCharges
                            && player.getWorld().isSkyVisible(player.getBlockPos())
                            && !player.getAbilities().creativeMode) {

                        player.addStatusEffect(effectResistance);
                        world.spawnEntity(lightning);
                    }
                }
            }
        }
    }
}
