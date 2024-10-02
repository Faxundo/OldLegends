package com.github.faxundo.old_legends.mixin;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.effect.OLEffect;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.villager.OLVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerData;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {

    private int range;
    private int maxVillagers;

    @Inject(method = "setVillagerData", at = @At("HEAD"), cancellable = true)
    public void setVillagerData(VillagerData villagerData, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        World world = livingEntity.getWorld();
        if (!world.isClient) {
            if (villagerData.getProfession().equals(OLVillager.SAGE) && villagerData.getLevel() <= 1) {
                if (!cancelProfession(livingEntity, world)) {
                    livingEntity.playSound(SoundEvents.ENTITY_VILLAGER_NO);
                    ci.cancel();
                }
            }
        }
    }

    @Unique
    public boolean cancelProfession(LivingEntity livingEntity, World world) {
        range = OldLegends.CONFIG.runeTable.range;
        maxVillagers = OldLegends.CONFIG.runeTable.maxVillagers;

        BlockPos blockPos = livingEntity.getBlockPos();
        Box box = new Box(blockPos.getX() - range, blockPos.getY() - range, blockPos.getZ() - range, blockPos.getX() + range, blockPos.getY() + range, blockPos.getZ() + range);

        List<VillagerEntity> villagers = world.getEntitiesByType(EntityType.VILLAGER, box, villagerEntity -> true);

        int villages = Math.round((float) (villagers.size() / maxVillagers));

        return OLHelper.amountOfWise(world, box) <= villages;
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource damageSource, CallbackInfo ci) {
        Entity entity = damageSource.getAttacker();
        if (entity != null) {
            if (entity instanceof PlayerEntity playerEntity) {
                if (!playerEntity.isCreative()) {

                    // Reduce hero of the village
                    StatusEffectInstance heroOfTheVillage = playerEntity.getStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE);
                    if (heroOfTheVillage != null) {
                        int amplifierHero = heroOfTheVillage.getAmplifier();
                        int durationHero = heroOfTheVillage.getDuration();

                        playerEntity.removeStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE);
                        if (amplifierHero != 0) {
                            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, durationHero, amplifierHero - 1));
                        }
                        return;
                    }

                    // Give Bane of the village
                    StatusEffectInstance currentEffect = playerEntity.getStatusEffect(Registries.STATUS_EFFECT.getEntry(OLEffect.BANE_OF_THE_VILLAGE));
                    int amplifier = currentEffect != null ? currentEffect.getAmplifier() + 1 : 0;

                    amplifier = Math.min(amplifier, 4);

                    StatusEffectInstance baneOfTheVillageInstance = new StatusEffectInstance(
                            Registries.STATUS_EFFECT.getEntry(OLEffect.BANE_OF_THE_VILLAGE), 48000, amplifier);

                    playerEntity.addStatusEffect(baneOfTheVillageInstance);
                }
            }
        }
    }

    // Copy of the original method with little changes
    @Inject(method = "prepareOffersFor", at = @At("TAIL"))
    private void prepareOffersFor(PlayerEntity player, CallbackInfo ci) {
        if (player.hasStatusEffect(Registries.STATUS_EFFECT.getEntry(OLEffect.BANE_OF_THE_VILLAGE))) {
            StatusEffectInstance statusEffectInstance = player.getStatusEffect(Registries.STATUS_EFFECT.getEntry(OLEffect.BANE_OF_THE_VILLAGE));

            if (statusEffectInstance != null) {
                int amplifier = statusEffectInstance.getAmplifier();

                VillagerEntity villagerEntity = (VillagerEntity) (Object) this;
                for (TradeOffer tradeOffer : villagerEntity.getOffers()) {
                    double increase = 0.3 + 0.0625 * (double) amplifier;
                    int amount = (int) Math.floor(increase * (double) tradeOffer.getOriginalFirstBuyItem().getCount());
                    tradeOffer.increaseSpecialPrice(Math.max(amount, 1));
                }
            }
        }
    }
}
