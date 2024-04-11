package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.entity.custom.EchoPickAxeProjectileEntity;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.custom.FlutterEcho;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class FlutterEchoAwake extends FlutterEcho implements Ability {

    private int cooldown;
    private int durabilityConsumed;

    public FlutterEchoAwake(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
        setAwake(true);
    }

    public void useAbility(PlayerEntity player) {
        cooldown = OldLegends.CONFIG.flutterEcho.cooldown;
        durabilityConsumed = OldLegends.CONFIG.flutterEcho.consumeDurability;
        World world = player.getWorld();

        ItemStack itemStack = player.getStackInHand(OLHelper.getHandWithItem(player, OLItem.FLUTTER_ECHO_AWAKE));

        if (player.getItemCooldownManager().isCoolingDown(this)) {
            return;
        }

        if (!world.isClient) {
            ItemStack abilityStack = OLHelper.getAbilityItemStack(player, this.getDefaultStack());

            EchoPickAxeProjectileEntity echoPickaxeProjectile = new EchoPickAxeProjectileEntity(player, world, abilityStack);
            echoPickaxeProjectile.setItem(itemStack);
            echoPickaxeProjectile.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);

            player.playSound(SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.PLAYERS, 1.5f, -0.3f);

            abilityStack.damage((this.getMaxDamage() * durabilityConsumed) / 100,
                    player, (e) -> {
                        e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
                    });

            world.spawnEntity(echoPickaxeProjectile);
            player.getItemCooldownManager().set(this, cooldown);
        }
    }
}
