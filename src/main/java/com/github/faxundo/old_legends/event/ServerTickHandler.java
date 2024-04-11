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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerTickHandler implements ServerTickCallback {

    private final Map<UUID, Integer> cooldownMap = new HashMap<>();

    @Override
    public void tick(MinecraftServer server) {
        World world = server.getOverworld();
        if (world.isThundering()) {

            int stormChance = OldLegends.CONFIG.swallowsStorm.stormChance;
            int maxCharges = OldLegends.CONFIG.swallowsStorm.maxCharges;
            int maxChargesAwake = OldLegends.CONFIG.swallowsStorm.maxChargesAwake;
            int cooldownTicks = OldLegends.CONFIG.swallowsStorm.stormCooldown;


            for (PlayerEntity player : world.getPlayers()) {
                if (player.isBlocking() && canSpawnLightning(player, stormChance, maxCharges, maxChargesAwake)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 40, 4));
                    spawnLightning(player);
                    cooldownMap.put(player.getUuid(), cooldownTicks);
                }
            }
        }

        for (UUID playerId : cooldownMap.keySet()) {
            int cooldown = cooldownMap.get(playerId);
            if (cooldown <= 0) {
                cooldownMap.remove(playerId);
            } else {
                cooldownMap.put(playerId, cooldown - 1);
            }
        }
    }

    private boolean canSpawnLightning(PlayerEntity player, int stormChance, int maxCharges, int maxChargesAwake) {
        ItemStack itemStack = ItemStack.EMPTY;
        int maxChargesItem = 0;

        if (OLHelper.hasItemInHand(player, OLItem.SWALLOWS_STORM)) {
            itemStack = OLHelper.getItemInHand(player, OLItem.SWALLOWS_STORM);
            maxChargesItem = maxCharges;
        } else if (OLHelper.hasItemInHand(player, OLItem.SWALLOWS_STORM_AWAKE)) {
            itemStack = OLHelper.getItemInHand(player, OLItem.SWALLOWS_STORM_AWAKE);
            maxChargesItem = maxChargesAwake;
        }

        if (!itemStack.hasNbt()) {
            return false;
        }
        NbtCompound nbtData = itemStack.getNbt();
        if (!nbtData.contains(OldLegends.MOD_ID)) {
            return false;
        }

        if (cooldownMap.containsKey(player.getUuid())) {
            return false;
        }

        return OLHelper.getRandomNumber(1, 100) < stormChance
                && nbtData.getInt(OldLegends.MOD_ID) < maxChargesItem
                && player.getWorld().isSkyVisible(player.getBlockPos())
                && !player.getAbilities().creativeMode;
    }

    public void spawnLightning(PlayerEntity player) {
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, player.getWorld());
        lightning.setFireTicks(0);
        lightning.setOnFire(false);
        lightning.setOnFireFor(0);
        lightning.setPosition(player.getX(), player.getY(), player.getZ());
        player.getWorld().spawnEntity(lightning);
    }
}
