package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.entity.custom.EchoPickAxeProjectileEntity;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.item.custom.FlutterEcho;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class FlutterEchoAwake extends FlutterEcho implements Ability {


    public static final String ABILITY_AWAKE_1 = "tooltip.old_legends.flutter_echo_awake_1";
    public static final String ABILITY_AWAKE_2 = "tooltip.old_legends.flutter_echo_awake_2";
    public static final String ABILITY_NAME_AWAKE = "tooltip.old_legends.flutter_echo_name_active";
    public static final String ABILITY_AWAKE = "tooltip.old_legends.flutter_echo_awake_active";

    private int cooldown;
    private int durabilityConsumed;
    private int extraCount;

    public FlutterEchoAwake(ToolMaterial material, TagKey<Block> effectiveBlocks, Item.Settings settings) {
        super(material, effectiveBlocks, settings.attributeModifiers(EmeraldMourning.createAttributeModifiers(ToolMaterials.NETHERITE, 2, -2.f)));
        setAwake(true);
    }

    public void useAbility(PlayerEntity player) {
        cooldown = OldLegends.CONFIG.flutterEcho.cooldown;
        durabilityConsumed = OldLegends.CONFIG.flutterEcho.consumeDurability;
        World world = player.getWorld();

        if (player.getItemCooldownManager().isCoolingDown(this)) return;

        if (!world.isClient) {
            ItemStack abilityStack = OLHelper.getAbilityItemStack(player, this.getDefaultStack());

            player.playSound(SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, 1.5f, -0.3f);

            abilityStack.damage((this.getDefaultStack().getMaxDamage() * durabilityConsumed) / 100, player, EquipmentSlot.MAINHAND);

            if (!player.isCreative()) {
                player.getItemCooldownManager().set(this, cooldown);
            }

            //Here is created the projectile
            EchoPickAxeProjectileEntity echoPickaxeProjectile = new EchoPickAxeProjectileEntity(player, player.getWorld(), abilityStack);
            echoPickaxeProjectile.setItem(abilityStack);
            echoPickaxeProjectile.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);

            //Spawn the projectile
            player.getWorld().spawnEntity(echoPickaxeProjectile);
        }
    }
}
