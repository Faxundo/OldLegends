package com.github.faxundo.old_legends.entity;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.sound.OLSound;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MourningMob extends ZombieVillagerEntity {

    int TIME_LIVED = 0;
    int LIFE = OldLegends.CONFIG.emeraldMourning.emeraldMourningAwakeMobTime;
    protected static final TrackedData<String> OWNER_UUID;

    public MourningMob(EntityType<? extends ZombieVillagerEntity> entityType, World world) {
        super(entityType, world);
        experiencePoints = 0;
    }

    public static DefaultAttributeContainer.Builder createMourningMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(3, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.goalSelector.add(4, new WanderAroundGoal(this, 0.6));
        this.targetSelector.add(1, new ActiveTargetGoal(this, IllagerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, 5, false, false, (player) -> {
            PlayerEntity playerEntity = (PlayerEntity) player;
            String uUUID = playerEntity.getUuid().toString();
            return !uUUID.equals(getOwnerUuid());
        }));
        this.targetSelector.add(3, new ActiveTargetGoal(this, MobEntity.class, 5, false, false, (entity) -> {
            if (!(entity instanceof MourningMob)) {
                return entity instanceof Monster;
            }
            return false;
        }));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(OWNER_UUID, "");
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Owner", this.getOwnerUuid());

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(OWNER_UUID, nbt.getString("Owner"));
    }

    @Nullable
    public String getOwnerUuid() {
        return this.dataTracker.get(OWNER_UUID);
    }

    public void setOwnerUuid(@Nullable String uuid) {
        this.dataTracker.set(OWNER_UUID, uuid);
    }

    public void setOwner(PlayerEntity player) {
        String ownerUUID = player.getUuid().toString();
        this.setOwnerUuid(ownerUUID);
    }

    @Override
    protected void mobTick() {
        TIME_LIVED++;
        if (TIME_LIVED == LIFE) {
            this.kill();
        }
        super.mobTick();
    }

    @Override
    protected boolean shouldDropLoot() {
        return false;
    }

    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    protected boolean burnsInDaylight() {
        return false;
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_VILLAGER_NO;
    }

    @Nullable
    @Override
    public SoundEvent getDeathSound() {
        return OLSound.MOURNING_MOB_DEATH;
    }

    static {
        OWNER_UUID = DataTracker.registerData(MourningMob.class, TrackedDataHandlerRegistry.STRING);

    }
}
