package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.item.custom.SwallowsStorm;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.joml.Vector3f;

import static com.github.faxundo.old_legends.OldLegends.CONFIG;

public class SwallowsStormAwake extends SwallowsStorm implements Ability {

    public static final String ABILITY_AWAKE_1 = "tooltip.old_legends.swallows_storm_awake_1";
    public static final String ABILITY_AWAKE_2 = "tooltip.old_legends.swallows_storm_awake_2";
    public static final String ABILITY_AWAKE_3 = "tooltip.old_legends.swallows_storm_awake_3";
    public static final String ABILITY_NAME_AWAKE = "tooltip.old_legends.swallows_storm_name_active";
    public static final String ABILITY_AWAKE = "tooltip.old_legends.swallows_storm_awake_active";

    private int cooldown;
    private int explosionDamage;
    private int explosionRange;
    private int explosionKnockback;
    private int maxCharges;

    public SwallowsStormAwake(Settings settings) {
        super(settings);
        setAwake(true);
    }

    public void useAbility(PlayerEntity player) {

        this.cooldown = CONFIG.swallowsStorm.explosiveCooldown;
        this.explosionDamage = CONFIG.swallowsStorm.explosiveDamage;
        this.explosionRange = CONFIG.swallowsStorm.explosiveRange;
        this.explosionKnockback = CONFIG.swallowsStorm.explosiveKnockback;
        this.maxCharges = CONFIG.swallowsStorm.maxChargesAwake;

        World world = player.getWorld();

        if (world.isClient) return;

        ItemStack abilityStack = OLHelper.getAbilityItemStack(player, this.getDefaultStack());

        if (player.getItemCooldownManager().isCoolingDown(this)) return;

        if (!abilityStack.contains(OLDataComponent.CHARGES)) return;

        if (abilityStack.get(OLDataComponent.CHARGES) != maxCharges) return;

        player.playSound(OLSound.SWALLOWS_STORM_EXPLOSION, 5.0f, 0f);
        DustParticleEffect dustParticle = new DustParticleEffect(new Vector3f(0.73f, 0.59f, 0.90f), 1.0f);
        OLHelperParticle.spawnCircularParticlesAroundPlayer(player, player.getWorld(), dustParticle, 100, explosionRange);

        abilityStack.set(OLDataComponent.CHARGES, 0);

        if (!player.isCreative()) {
            player.getItemCooldownManager().set(this, cooldown);
        }

        abilityStack.damage((this.getDefaultStack().getMaxDamage() * getDurabilityConsumed()) / 100, player, EquipmentSlot.MAINHAND);

        Box box = player.getBoundingBox().expand(explosionRange);
        world.getEntitiesByClass(LivingEntity.class, box, (entity_box) -> true).forEach((entity_box) -> {
            if (entity_box != player) {
                entity_box.takeKnockback(explosionKnockback, entity_box.getX(), entity_box.getZ());
                entity_box.damage(player.getDamageSources().playerAttack(player), explosionDamage);

            }
        });

    }
}
