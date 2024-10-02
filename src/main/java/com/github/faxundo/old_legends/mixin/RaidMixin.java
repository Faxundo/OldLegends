package com.github.faxundo.old_legends.mixin;

import com.github.faxundo.old_legends.effect.OLEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Raid.class)
public class RaidMixin {

    @Inject(method = "addHero", at=@At("HEAD"))
    public void addHero(Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            if (player.hasStatusEffect(Registries.STATUS_EFFECT.getEntry(OLEffect.BANE_OF_THE_VILLAGE))) {
                player.removeStatusEffect(Registries.STATUS_EFFECT.getEntry(OLEffect.BANE_OF_THE_VILLAGE));
            }
        }
    }
}
