package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.util.OLTag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EchoOreBlockEntity extends BlockEntity {

    private int count = 0;
    private BlockState ore = Blocks.AIR.getDefaultState();
    private ItemStack itemStack;
    private PlayerEntity player;

    public EchoOreBlockEntity(BlockPos pos, BlockState state) {
        super(OLBlockEntity.ECHO_ORE_ENTITY, pos, state);
    }

    public void setStack(ItemStack stack) {
        this.itemStack = stack;
    }

    public ItemStack getStack() {
        return itemStack;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setOre(BlockState ore) {
        this.ore = ore;
    }

    public BlockState getOre() {
        return ore;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }
        count++;
        if (count >= 70) {
            world.playSound(null, pos, SoundEvents.BLOCK_SCULK_VEIN_BREAK, SoundCategory.BLOCKS, 0.5f, -0.1f);
            world.removeBlock(pos, false);
            if (ore != null) {
                LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld) world);
                ItemScatterer.spawn(world, pos, dropEchoOre(ore, pos, player, itemStack, builder));
            }
        }
    }

    public Inventory dropEchoOre(BlockState state, BlockPos pos, PlayerEntity player, ItemStack stack, LootContextParameterSet.Builder builder) {
        List<ItemStack> list = new ArrayList<>();

        TagKey<Block> ORES = OLTag.Blocks.ORES;

        RegistryKey<LootTable> identifier = state.getBlock().getLootTableKey();
        if (!identifier.equals(LootTables.EMPTY) && state.isIn(ORES)) {
            builder.add(LootContextParameters.BLOCK_STATE, state)
                    .add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                    .add(LootContextParameters.TOOL, stack)
                    .add(LootContextParameters.THIS_ENTITY, player);
            LootContextParameterSet lootContextParameterSet = builder.build(LootContextTypes.BLOCK);
            ServerWorld serverWorld = lootContextParameterSet.getWorld();
            LootTable lootTable = serverWorld.getServer().getReloadableRegistries().getLootTable(identifier);
            list.addAll(lootTable.generateLoot(lootContextParameterSet));
        } else {
            //Debug
            list.add(Items.GOLDEN_BOOTS.getDefaultStack());
        }
        return new SimpleInventory(list.toArray(new ItemStack[0]));
    }
}
