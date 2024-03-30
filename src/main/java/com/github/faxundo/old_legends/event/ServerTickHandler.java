package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import com.github.faxundo.old_legends.util.OLHelpers;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ServerTickHandler implements ServerTickCallback {

    private final int stormChance = OldLegends.CONFIG.swallowsStorm.swallowsStormChance;

    @Override
    public void tick(MinecraftServer server) {
        World world = server.getOverworld();
        if (world.isThundering()) {
            for (PlayerEntity player : world.getPlayers()) {
                if (player.getMainHandStack().getItem() instanceof SwallowsStormItem swallowsStormItem) {
                    if (player.isBlocking()
                            && OLHelpers.getRandomNumber(1, 100) < stormChance
                            && player.getMainHandStack().getNbt().getInt(OldLegends.MOD_ID) < swallowsStormItem.getMaxCharges()
                            && player.getWorld().isSkyVisible(player.getBlockPos())
                            && !player.getAbilities().creativeMode) {
                        double x = player.getX();
                        double y = player.getY();
                        double z = player.getZ();
                        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                        lightning.setFireTicks(0);
                        lightning.setOnFire(false);
                        lightning.setOnFireFor(0);
                        lightning.setPosition(x, y, z);
                        world.spawnEntity(lightning);
                    }
                }
            }
        }
    }
}
