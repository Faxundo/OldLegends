package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.entity.custom.Vengeful;
import com.github.faxundo.old_legends.item.custom.EmeraldMourning;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class EmeraldMourningAwake extends EmeraldMourning implements Ability {

    public static final String ABILITY_AWAKE_1 = "tooltip.old_legends.emerald_mourning_awake_1";
    public static final String ABILITY_AWAKE_2 = "tooltip.old_legends.emerald_mourning_awake_2";
    public static final String ABILITY_NAME_AWAKE = "tooltip.old_legends.emerald_mourning_name_active";
    public static final String ABILITY_AWAKE = "tooltip.old_legends.emerald_mourning_awake_active";

    private int cooldown;
    private int durabilityConsumed;

    public EmeraldMourningAwake(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(EmeraldMourning.createAttributeModifiers(ToolMaterials.NETHERITE, 4, -2.4f)));
        setAwake(true);
    }

    public void useAbility(PlayerEntity player) {
        World world = player.getWorld();
        if (!world.isClient) {

            if (player.getItemCooldownManager().isCoolingDown(this)) return;

            ItemStack abilityStack = OLHelper.getAbilityItemStack(player, this.getDefaultStack());
            this.cooldown = OldLegends.CONFIG.emeraldMourning.cooldown;
            this.durabilityConsumed = OldLegends.CONFIG.emeraldMourning.consumeDurability;

            abilityStack.damage((this.getDefaultStack().getMaxDamage() * durabilityConsumed) / 100, player, EquipmentSlot.MAINHAND);

            player.playSound(SoundEvents.ENTITY_WITHER_SKELETON_STEP, 5.0f, 0f);

            if (!player.isCreative()) {
                player.getItemCooldownManager().set(this, cooldown);
            }

            for (int i = 1; i <= 4; i++) {
                Vengeful vengeful = new Vengeful(EntityType.ZOMBIE_VILLAGER, world);
                vengeful.setPosition(
                        player.getX() + OLHelper.getRandomNumber(0, 4),
                        player.getY() + 1,
                        player.getZ() - OLHelper.getRandomNumber(0, 4));

                if (vengeful.getOwnerUuid().isEmpty()) {
                    vengeful.setOwnerUuid(player.getUuidAsString());
                }

                for (int j = 1; j < 4; j++) {
                    OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.LARGE_SMOKE, vengeful.getBlockX(), vengeful.getBlockY() - 0.2, vengeful.getBlockZ(),
                            0, 0.1, 0);
                    OLHelperParticle.spawnParticle(vengeful.getWorld(), ParticleTypes.SOUL, vengeful.getBlockX(), vengeful.getBlockY() - 0.3, vengeful.getBlockZ(),
                            0.1, 0.1, 0.1);
                }

                world.spawnEntity(vengeful);
            }
        }
    }



//        DynamicRegistryManager registryManager = player.getWorld().getRegistryManager();
//        RegistryKey<Enchantment> vengeanceKey = RegistryKey.of(RegistryKeys.ENCHANTMENT, OldLegends.identifier("vengeance"));
//        RegistryEntry.Reference<Enchantment> vengeanceReference = registryManager.get(RegistryKeys.ENCHANTMENT).entryOf(vengeanceKey);
//        int level = EnchantmentHelper.getLevel(vengeanceReference, chestplate);

}
