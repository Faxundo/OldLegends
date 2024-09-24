package com.github.faxundo.old_legends.mixin;

import com.github.faxundo.old_legends.event.callback.ShieldBlockCallback;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();

    @Inject(method = "damage", at = @At("HEAD"))
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        //Shield Block
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack activeItem = player.getActiveItem();
        if (activeItem.getItem() instanceof ShieldItem) {
            //Event
            ShieldBlockCallback.EVENT.invoker().block(player, source, activeItem);

            Hand hand = OLHelper.getHandWithItem(player, activeItem.getItem());

            //Durability loss
            if (hand == Hand.MAIN_HAND) {
                activeItem.damage((int) amount, player, EquipmentSlot.MAINHAND);
            } else if (hand == Hand.OFF_HAND) {
                activeItem.damage((int) amount, player, EquipmentSlot.OFFHAND);
            }

            //Sound
            player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.getWorld().random.nextFloat() * 0.4F);
        }
    }

    @Inject(method = "disableShield", at = @At("HEAD"))
    public void disableShield(CallbackInfo ci) {
        this.getItemCooldownManager().set(OLItem.SWALLOWS_STORM, 90);
        this.getItemCooldownManager().set(OLItem.SWALLOWS_STORM_AWAKE, 80);
    }
}
