package com.github.faxundo.old_legends.entity.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.entity.EchoBlockEntity;
import com.github.faxundo.old_legends.entity.OLEntities;
import com.github.faxundo.old_legends.item.OLItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EchoPickAxeProjectileEntity extends ThrownItemEntity {

    private int count = 0;
    private int countLimit = 0;
    private int timeLived = 0;
    private ItemStack stack;

    public EchoPickAxeProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EchoPickAxeProjectileEntity(LivingEntity livingEntity, World world, ItemStack stack) {
        super(OLEntities.ECHO_PICKAXE_PROJECTILE, livingEntity, world);
        this.stack = stack;
    }

    @Override
    protected Item getDefaultItem() {
        return OLItem.FLUTTER_ECHO_AWAKE;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        timeLived++;
        if (timeLived >= 200) {
            this.discard();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient) {
            LivingEntity entity = (LivingEntity) entityHitResult.getEntity();
            PlayerEntity player = (PlayerEntity) this.getOwner();
            entity.damage(player.getDamageSources().playerAttack(player), 14);
            if (!entity.equals(this.getOwner())) {
                countController();
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        BlockPos pos = blockHitResult.getBlockPos();
        World world = this.getWorld();
        if (!world.isClient) {
            BlockState state = this.getWorld().getBlockState(pos);
            if (!state.getBlock().equals(Blocks.BEDROCK)) {
                if (this.getOwner() instanceof PlayerEntity player) {

                    world.breakBlock(pos, false);
                    world.setBlockState(pos,OLBlock.ECHO_BLOCK.getDefaultState());

                    if (world.getBlockEntity(pos) instanceof EchoBlockEntity echoBlockEntity) {
                        echoBlockEntity.setBlockState(state);
                        echoBlockEntity.setStack(stack);
                        echoBlockEntity.setPlayer(player);
                    }
                }
            }
            countController();
        }
    }


    public void countController() {
        countLimit = OldLegends.CONFIG.flutterEcho.countLimit;
        count++;
        if (count >= countLimit) {
            this.discard();
        }
    }
}
