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
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class EmeraldMourningSwordAwake extends OLGenericSword {
    public EmeraldMourningSwordAwake(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        setId("emerald_mourning_awake");
        setAwake(true);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            if (target instanceof IllagerEntity) {
                target.damage(attacker.getWorld().getDamageSources().generic(), (this.getAttackDamage() * OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakePercentage) / 100);
                if (target.isDead() && attacker.isPlayer()) {
                    for (int i = 1; i <= OLHelpers.getRandomNumber(1, 3); i++) {
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
            playerEntity.getItemCooldownManager().set(this, 100);
            context.getStack().damage((this.getMaxDamage() * OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakePercentageConsumeDurability) / 100,
                    playerEntity, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                    });
            for (int i = 1; i <= 3; i++) {
                MourningMob mourningMob = new MourningMob(EntityType.ZOMBIE_VILLAGER, playerWorld);
                mourningMob.setPosition(
                        playerEntity.getX() + OLHelpers.getRandomNumber(1, 4),
                        playerEntity.getY(),
                        playerEntity.getZ() + OLHelpers.getRandomNumber(1, 4));
                if (mourningMob.getOwnerUuid().isEmpty()) {
                    mourningMob.setOwner(playerEntity);
                }
                playerWorld.spawnEntity(mourningMob);
            }
        }
        return ActionResult.SUCCESS;
    }
}
