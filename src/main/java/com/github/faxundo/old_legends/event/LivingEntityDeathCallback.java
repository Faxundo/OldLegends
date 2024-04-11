package com.github.faxundo.old_legends.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface LivingEntityDeathCallback {

    Event<LivingEntityDeathCallback> EVENT = EventFactory.createArrayBacked(LivingEntityDeathCallback.class,
            (listeners) -> (player, livingEntity) -> {
                for (LivingEntityDeathCallback listener : listeners) {
                    ActionResult result = listener.interact(player, livingEntity);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult interact(PlayerEntity player, LivingEntity livingEntity);

}
