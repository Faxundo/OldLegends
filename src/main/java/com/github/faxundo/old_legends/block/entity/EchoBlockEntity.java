package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.block.OLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class EchoBlockEntity extends BlockEntity {

    private int count = 0;
    private BlockState blockState;
    private ItemStack stack;
    private PlayerEntity player;

    public EchoBlockEntity(BlockPos pos, BlockState state) {
        super(OLBlockEntity.ECHO_BLOCK_ENTITY, pos, state);
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }
        count++;
        if (count >= 45) {
            world.playSound(null, pos, SoundEvents.BLOCK_SCULK_VEIN_BREAK, SoundCategory.BLOCKS, 0.5f, -0.1f);
            world.removeBlock(pos, false);
            if (blockState != null) {
                LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld) world);
                ItemScatterer.spawn(world, pos, dropToInventory(getDroppedStacks(blockState, pos, player, stack, builder)));
            }
        }
    }

    public List<ItemStack> getDroppedStacks(BlockState state, BlockPos pos, PlayerEntity player, ItemStack stack, LootContextParameterSet.Builder builder) {
        RegistryKey<LootTable> identifier = state.getBlock().getLootTableKey();
        if (identifier.equals(LootTables.EMPTY)) {
            return Collections.emptyList();
        } else {

            builder.add(LootContextParameters.BLOCK_STATE, state)
                    .add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                    .add(LootContextParameters.TOOL, stack)
                    .add(LootContextParameters.THIS_ENTITY, player);
            LootContextParameterSet lootContextParameterSet = builder.build(LootContextTypes.BLOCK);
            ServerWorld serverWorld = lootContextParameterSet.getWorld();
            LootTable lootTable = serverWorld.getServer().getReloadableRegistries().getLootTable(identifier);

            return lootTable.generateLoot(lootContextParameterSet);

        }
    }

    public Inventory dropToInventory(List<ItemStack> listStack) {
        return new SimpleInventory(listStack.toArray(new ItemStack[0]));
    }
}
