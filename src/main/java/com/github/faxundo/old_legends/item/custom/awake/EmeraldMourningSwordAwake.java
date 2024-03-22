package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.entity.MourningMob;
import com.github.faxundo.old_legends.item.OLGenericSword;
import com.github.faxundo.old_legends.util.OLHelpers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class EmeraldMourningSwordAwake extends OLGenericSword {
    public EmeraldMourningSwordAwake(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        setId("emerald_mourning");
        setAwake(true);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            if (target instanceof IllagerEntity) {
                target.damage(attacker.getWorld().getDamageSources().generic(), (this.getAttackDamage() * OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakePercentageIllager) / 100);
                if (target.isDead() && attacker.isPlayer()) {
                    for (int i = 1; i <= OLHelpers.getRandomNumber(1, 3); i++) {
                        OLHelpers.spawnParticle(target.getWorld(), ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getY(), target.getZ(),
                                0.5, 0, 0.5);
                        target.dropStack(new ItemStack(Items.EMERALD));
                    }
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World playerWorld = context.getWorld();
        PlayerEntity playerEntity = context.getPlayer();
        if (!playerWorld.isClient) {
            playerEntity.getItemCooldownManager().set(this, OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakeCooldown);
            context.getStack().damage((this.getMaxDamage() * OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakePercentageConsumeDurability) / 100,
                    playerEntity, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                    });
            playerEntity.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, SoundCategory.PLAYERS, 5.0f, 0f);
            for (int i = 1; i <= 3; i++) {
                MourningMob mourningMob = new MourningMob(EntityType.ZOMBIE_VILLAGER, playerWorld);
                mourningMob.setPosition(
                        playerEntity.getX() + OLHelpers.getRandomNumber(0, 4),
                        playerEntity.getY() + 1,
                        playerEntity.getZ() - OLHelpers.getRandomNumber(0, 4));
                if (mourningMob.getOwnerUuid().isEmpty()) {
                    mourningMob.setOwner(playerEntity);
                }
                for (int j = 1; j < 4; j++) {
                    OLHelpers.spawnParticle(mourningMob.getWorld(), ParticleTypes.LARGE_SMOKE, mourningMob.getBlockX(), mourningMob.getBlockY()-0.2, mourningMob.getBlockZ(),
                            0, 0.1, 0);
                    OLHelpers.spawnParticle(mourningMob.getWorld(), ParticleTypes.SOUL, mourningMob.getBlockX(), mourningMob.getBlockY()-0.3, mourningMob.getBlockZ(),
                            0.1, 0.1, 0.1);
                }
                playerWorld.spawnEntity(mourningMob);
            }
        }
        return ActionResult.SUCCESS;
    }
}
