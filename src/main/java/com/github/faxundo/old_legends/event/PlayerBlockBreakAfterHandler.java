package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.item.custom.FlutterEcho;
import com.github.faxundo.old_legends.util.OLHelper;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlayerBlockBreakAfterHandler implements PlayerBlockBreakEvents.After {

    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (!player.isCreative()
                && OLHelper.isOreBlock(state)
                && player.getMainHandStack().getItem() instanceof FlutterEcho flutterEcho) {

            flutterEcho.setBlockMinedState(state);
            flutterEcho.setBlockMinedPos(pos);

        }
    }

}
