package com.github.faxundo.old_legends.mixin;

import com.github.faxundo.old_legends.block.entity.ReliquaryBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {

    @Inject(method = "insertAndExtract", at = @At("HEAD"), cancellable = true)
    private static void onInsertAndExtract(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity, BooleanSupplier booleanSupplier, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockEntity(pos.up()) instanceof ReliquaryBlockEntity
                || world.getBlockEntity(pos.down()) instanceof ReliquaryBlockEntity
                || world.getBlockEntity(pos.east()) instanceof ReliquaryBlockEntity
                || world.getBlockEntity(pos.west()) instanceof ReliquaryBlockEntity
                || world.getBlockEntity(pos.north()) instanceof ReliquaryBlockEntity
                || world.getBlockEntity(pos.south()) instanceof ReliquaryBlockEntity) {
            cir.setReturnValue(false);
        }
    }



}
