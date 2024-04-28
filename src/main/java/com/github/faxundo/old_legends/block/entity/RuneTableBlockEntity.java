package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.block.ImplementedInventory;
import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.screen.custom.RuneTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RuneTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
    private boolean northPillar;
    private boolean southPillar;
    private boolean eastPillar;
    private boolean westPillar;

    public RuneTableBlockEntity(BlockPos pos, BlockState state) {
        super(OLBlockEntity.RUNE_TABLE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.old_legends.rune_table");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new RuneTableScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        northPillar = nbt.getBoolean("northPillar");
        southPillar = nbt.getBoolean("southPillar");
        eastPillar = nbt.getBoolean("eastPillar");
        westPillar = nbt.getBoolean("westPillar");
    }


    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putBoolean("northPillar", northPillar);
        nbt.putBoolean("southPillar", southPillar);
        nbt.putBoolean("eastPillar", eastPillar);
        nbt.putBoolean("westPillar", westPillar);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        this.northPillar = hasAndSpawnPillar(world, pos, 0, 0, -3);
        this.southPillar = hasAndSpawnPillar(world, pos, 0, 0, 3);
        this.eastPillar = hasAndSpawnPillar(world, pos, 3, 0, 0);
        this.westPillar = hasAndSpawnPillar(world, pos, -3, 0, 0);
    }

    public boolean hasAndSpawnPillar(World world, BlockPos pos, int offsetX, int offsetY, int offsetZ) {
        BlockPos pillarPos = pos.add(offsetX, offsetY, offsetZ);
        return hasPillar(world, pillarPos);
    }

    public boolean hasPillar(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock().equals(Blocks.AMETHYST_BLOCK)
                && world.getBlockState(pos.up()).getBlock().equals(Blocks.AMETHYST_BLOCK)
                && world.getBlockState(pos.up(2)).getBlock().equals(Blocks.AMETHYST_CLUSTER);
    }
}
