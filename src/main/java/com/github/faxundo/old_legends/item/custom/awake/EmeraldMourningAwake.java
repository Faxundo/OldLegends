package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.entity.MourningMob;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class EmeraldMourningAwake extends EmeraldMourning {

    private int cooldown;
    private int durabilityConsumed;


    public EmeraldMourningAwake(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        setAwake(true);
    }

    public void useAbility(PlayerEntity player) {
        World world = player.getWorld();
        if (!world.isClient) {

            if (player.getItemCooldownManager().isCoolingDown(this)) {
                return;
            }

            ItemStack abilityStack = OLHelper.getAbilityItemStack(player, this.getDefaultStack());
            cooldown = OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakeCooldown;
            durabilityConsumed = OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakePercentageConsumeDurability;


            abilityStack.damage((this.getMaxDamage() * durabilityConsumed) / 100,
                    player, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                    });

            player.getItemCooldownManager().set(this, cooldown);
            player.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, SoundCategory.PLAYERS, 5.0f, 0f);
            player.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, SoundCategory.PLAYERS, 5.0f, 0f);
            for (int i = 1; i <= 3; i++) {
                MourningMob mourningMob = new MourningMob(EntityType.ZOMBIE_VILLAGER, world);
                mourningMob.setPosition(
                        player.getX() + OLHelper.getRandomNumber(0, 4),
                        player.getY() + 1,
                        player.getZ() - OLHelper.getRandomNumber(0, 4));
                if (mourningMob.getOwnerUuid().isEmpty()) {
                    mourningMob.setOwner(player);
                }
                for (int j = 1; j < 4; j++) {
                    OLHelper.spawnParticle(mourningMob.getWorld(), ParticleTypes.LARGE_SMOKE, mourningMob.getBlockX(), mourningMob.getBlockY() - 0.2, mourningMob.getBlockZ(),
                            0, 0.1, 0);
                    OLHelper.spawnParticle(mourningMob.getWorld(), ParticleTypes.SOUL, mourningMob.getBlockX(), mourningMob.getBlockY() - 0.3, mourningMob.getBlockZ(),
                            0.1, 0.1, 0.1);
                }
                world.spawnEntity(mourningMob);
            }
        }
    }
}
