package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EchoOreBlockEntity extends BlockEntity {

    private int count = 0;
    private BlockState ore = Blocks.AIR.getDefaultState();
    private boolean hasFortune = false;
    private int fortuneLevel = 0;

    public EchoOreBlockEntity(BlockPos pos, BlockState state) {
        super(OLBlockEntity.ECHO_ORE_ENTITY, pos, state);
    }

    public void setOre(BlockState ore) {
        this.ore = ore;
    }

    public BlockState getOre() {
        return ore;
    }

    public void setHasFortune(boolean hasFortune) {
        this.hasFortune = hasFortune;
    }

    public boolean isHasFortune() {
        return hasFortune;
    }

    public void setFortuneLevel(int fortuneLevel) {
        this.fortuneLevel = fortuneLevel;
    }

    public int getFortuneLevel() {
        return fortuneLevel;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }
        count++;
        if (count >= 70) {
            world.playSound(null, pos, SoundEvents.BLOCK_SCULK_VEIN_BREAK, SoundCategory.BLOCKS, 0.5f, -0.1f);
            world.removeBlock(pos, false);
            ItemScatterer.spawn(world, pos, dropEchoOre(getOre()));
        }
    }

    public Inventory dropEchoOre(BlockState state) {
        List<ItemStack> list = new ArrayList<>();
        ItemStack itemStack = ItemStack.EMPTY;
        int amount = 0;
        if (state.getBlock().equals(Blocks.COAL_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_COAL_ORE)) {
            itemStack = Items.COAL.getDefaultStack();
            if (hasFortune) {
                itemStack.setCount(fortuneLevel + 1);
            }
        } else if (state.getBlock().equals(Blocks.IRON_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_IRON_ORE)) {
            itemStack = Items.RAW_IRON.getDefaultStack();
            if (hasFortune) {
                itemStack.setCount(fortuneLevel + 1);
            }
        } else if (state.getBlock().equals(Blocks.EMERALD_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_EMERALD_ORE)) {
            itemStack = Items.EMERALD.getDefaultStack();
            if (hasFortune) {
                itemStack.setCount(fortuneLevel + 1);
            }
        } else if (state.getBlock().equals(Blocks.DIAMOND_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_DIAMOND_ORE)) {
            itemStack = Items.DIAMOND.getDefaultStack();
            if (hasFortune) {
                itemStack.setCount(fortuneLevel + 1);
            }
        } else if (state.getBlock().equals(Blocks.COPPER_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_COPPER_ORE)) {
            itemStack = Items.RAW_COPPER.getDefaultStack();
            amount = (int) OLHelper.getRandomNumber(2, 5);
            if (hasFortune) {
                itemStack.setCount((fortuneLevel * amount) + (1 + amount));
            } else {
                itemStack.setCount(1 + amount);
            }
        } else if (state.getBlock().equals(Blocks.REDSTONE_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_REDSTONE_ORE)) {
            itemStack = Items.REDSTONE.getDefaultStack();
            amount = (int) OLHelper.getRandomNumber(4, 5);
            if (hasFortune) {
                itemStack.setCount(fortuneLevel + amount);
            }
        } else if (state.getBlock().equals(Blocks.LAPIS_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_LAPIS_ORE)) {
            itemStack = Items.LAPIS_LAZULI.getDefaultStack();
            amount = (int) OLHelper.getRandomNumber(4, 9);
            if (hasFortune) {
                itemStack.setCount((fortuneLevel * amount) + (1 + amount));
            } else {
                itemStack.setCount(1 + amount);
            }
        } else if (state.getBlock().equals(Blocks.GOLD_ORE) || state.getBlock().equals(Blocks.DEEPSLATE_GOLD_ORE)) {
            itemStack = Items.RAW_GOLD.getDefaultStack();
            if (hasFortune) {
                itemStack.setCount(fortuneLevel + 1);
            }
        } else if (state.getBlock().equals(Blocks.NETHER_QUARTZ_ORE)) {
            itemStack = Items.QUARTZ.getDefaultStack();
            if (hasFortune) {
                itemStack.setCount(fortuneLevel + 1);
            }
        } else if (state.getBlock().equals(Blocks.NETHER_GOLD_ORE)) {
            itemStack = Items.GOLD_NUGGET.getDefaultStack();
            amount = (int) OLHelper.getRandomNumber(2, 6);
            if (hasFortune) {
                itemStack.setCount((fortuneLevel * amount) + (1 + amount));
            } else {
                itemStack.setCount(1 + amount);
            }
        }
        list.add(itemStack);
        return new SimpleInventory(list.toArray(new ItemStack[0]));
    }
}
