package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.custom.awake.SwallowsStormAwakeItem;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityHandler implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (player.getMainHandStack().getItem() instanceof SwallowsStormAwakeItem swallowsStormAwakeItem) {

            ItemStack itemStack = player.getMainHandStack();

            if (!itemStack.hasNbt()) {
                return ActionResult.PASS;
            }

            NbtCompound nbtData = itemStack.getNbt();

            if (!nbtData.contains(OldLegends.MOD_ID)) {
                return ActionResult.PASS;
            }

            int charges = nbtData.getInt(OldLegends.MOD_ID);

            if (charges == swallowsStormAwakeItem.getMaxCharges()) {

                nbtData.putInt(OldLegends.MOD_ID, 0);
                Box box = player.getBoundingBox().expand(50);
                world.getEntitiesByClass(LivingEntity.class, box, (entity_box) -> true).forEach((entity_box) -> {
                    if (entity_box != player) {
                        entity_box.damage(player.getDamageSources().playerAttack(player), 5);

                    }
                });
            }
        }
        return ActionResult.PASS;
    }
}
