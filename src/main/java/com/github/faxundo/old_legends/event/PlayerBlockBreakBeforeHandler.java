package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.block.entity.ReliquaryBlockEntity;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlayerBlockBreakBeforeHandler implements PlayerBlockBreakEvents.Before {
    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (blockEntity != null) {
            if (blockEntity instanceof ReliquaryBlockEntity reliquaryBlockEntity) {
                if (!player.isCreative()) {
                    return !reliquaryBlockEntity.isLocked();
                }
            }
        }
        return true;
    }
}
