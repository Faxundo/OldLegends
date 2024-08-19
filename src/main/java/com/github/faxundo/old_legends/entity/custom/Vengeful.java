package com.github.faxundo.old_legends.entity.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.sound.OLSound;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
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
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class Vengeful extends ZombieVillagerEntity implements RangedAttackMob {

    int TIME_LIVED = 0;
    protected static final TrackedData<String> OWNER_UUID;

    private final BowAttackGoal<Vengeful> bowAttackGoal = new BowAttackGoal<>(this, 1.0, 20, 15.0F);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.2, false) {
        public void stop() {
            super.stop();
            Vengeful.this.setAttacking(false);
        }

        public void start() {
            super.start();
            Vengeful.this.setAttacking(true);
        }
    };

    public Vengeful(EntityType<? extends ZombieVillagerEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 0;
        this.updateAttackType();
    }

    public static DefaultAttributeContainer.Builder createMourningMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 15.0F, 1.0F));
        this.goalSelector.add(3, new LookAtEntityGoal(this, MobEntity.class, 15.0F));
        this.goalSelector.add(4, new WanderAroundGoal(this, 0.8));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, IllagerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, 5, false, false, (player) -> {
            PlayerEntity playerEntity = (PlayerEntity) player;
            String uUUID = playerEntity.getUuid().toString();
            return !uUUID.equals(getOwnerUuid());
        }));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, MobEntity.class, 5, false, false, (entity) -> {
            if (!(entity instanceof Vengeful)) {
                return entity instanceof Monster;
            }
            return false;
        }));
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(OWNER_UUID, "");
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Owner", this.getOwnerUuid());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(OWNER_UUID, nbt.getString("Owner"));
        this.updateAttackType();
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
    public void shootAt(LivingEntity target, float pullProgress) {
        ItemStack itemStack = this.getProjectileType(this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW)));
        PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack, pullProgress);
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(persistentProjectileEntity);
    }

    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
        return ProjectileUtil.createArrowProjectile(this, arrow, damageModifier, this.getMainHandStack());
    }

    public void updateAttackType() {
        if (this.getWorld() != null && !this.getWorld().isClient) {
            this.goalSelector.remove(this.meleeAttackGoal);
            this.goalSelector.remove(this.bowAttackGoal);
            ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
            if (itemStack.isOf(Items.BOW)) {
                int i = 20;
                if (this.getWorld().getDifficulty() != Difficulty.HARD) {
                    i = 40;
                }

                this.bowAttackGoal.setAttackInterval(i);
                this.goalSelector.add(1, this.bowAttackGoal);
            } else {
                this.goalSelector.add(1, this.meleeAttackGoal);
            }

        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @org.jetbrains.annotations.Nullable EntityData entityData) {
        entityData = super.initialize(world, difficulty, spawnReason, entityData);
        this.updateAttackType();
        return entityData;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        super.equipStack(slot, stack);
        if (!this.getWorld().isClient) {
            this.updateAttackType();
        }
    }

    @Override
    protected void mobTick() {
        int life = OldLegends.CONFIG.emeraldMourning.mobTime;
        TIME_LIVED++;
        if (TIME_LIVED == life) {
            this.kill();
        }
        super.mobTick();
    }

//    @Override
//    public EntityGroup getGroup() {
//        return EntityGroup.UNDEAD;
//    }

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
        OWNER_UUID = DataTracker.registerData(Vengeful.class, TrackedDataHandlerRegistry.STRING);
    }
}
