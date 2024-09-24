package com.github.faxundo.old_legends.event.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface ShieldBlockCallback {

        Event<ShieldBlockCallback> EVENT = EventFactory.createArrayBacked(ShieldBlockCallback.class,
                (listeners) -> (defender, source, shield) -> {
                    for (ShieldBlockCallback listener : listeners) {
                        ActionResult result = listener.block(defender, source, shield);

                        if (result != ActionResult.PASS) {
                            return result;
                        }
                    }

                    return ActionResult.PASS;
                });

        ActionResult block(LivingEntity defender, DamageSource source, ItemStack shield);
}
