package com.github.faxundo.old_legends.item.custom.awake;

import com.github.faxundo.old_legends.item.custom.SwallowsStormItem;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.joml.Vector3f;

import static com.github.faxundo.old_legends.OldLegends.CONFIG;

public class SwallowsStormAwakeItem extends SwallowsStormItem implements Ability {

    private int cooldown;
    private int explosionDamage;
    private int explosionRange;
    private int explosionKnockback;
    private int maxCharges;

    public SwallowsStormAwakeItem(Settings settings, int coolDownTicks, int enchantability, TagKey<Item> repairItemTag) {
        super(settings, coolDownTicks, enchantability, repairItemTag);
        setId("swallows_storm");
        setAwake(true);
        setUseCharges(true);
        setAmountPassives(3);
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

        abilityStack.set(OLDataComponent.CHARGES,0);

        player.getItemCooldownManager().set(this, cooldown);

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
