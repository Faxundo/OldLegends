package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.block.ImplementedInventory;
import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.github.faxundo.old_legends.screen.custom.RuneTableScreenHandler;
import com.github.faxundo.old_legends.screen.data.RuneTableData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuneTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
    private boolean northPillar;
    private boolean southPillar;
    private boolean eastPillar;
    private boolean westPillar;
    private int BOOK_SLOT = 0;
    private int OUTPUT_SLOT = 10;

    public RuneTableBlockEntity(BlockPos pos, BlockState state) {
        super(OLBlockEntity.RUNE_TABLE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
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
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putBoolean("northPillar", northPillar);
        nbt.putBoolean("southPillar", southPillar);
        nbt.putBoolean("eastPillar", eastPillar);
        nbt.putBoolean("westPillar", westPillar);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        northPillar = nbt.getBoolean("northPillar");
        southPillar = nbt.getBoolean("southPillar");
        eastPillar = nbt.getBoolean("eastPillar");
        westPillar = nbt.getBoolean("westPillar");
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        this.northPillar = hasAndSpawnPillar(world, pos, 0, 0, -3);
        this.southPillar = hasAndSpawnPillar(world, pos, 0, 0, 3);
        this.eastPillar = hasAndSpawnPillar(world, pos, 3, 0, 0);
        this.westPillar = hasAndSpawnPillar(world, pos, -3, 0, 0);

        if (inventory.get(1).getItem().equals(Items.BOOK)) {
            craftEnchantment();
            markDirty(world, pos, state);
            return;
        }
        if (inventory.get(1).getItem().equals(OLItem.BLANK_RUNE)) {
            craftRune();
            markDirty(world, pos, state);
        }
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

    private boolean isOutputSlotEmptyOrReceivable(ItemStack output) {
        ItemStack stack = this.getStack(OUTPUT_SLOT);
        return stack.isEmpty() || (stack.getItem() == output.getItem() && stack.getCount() < stack.getMaxCount());
    }

    public void craftRune() {
        if (inventory.get(BOOK_SLOT).getItem() instanceof BookOfTheLegends bookOfTheLegends) {
            String pageDeath = "item.old_legends.death_rune_page";
            String pageSky = "item.old_legends.sky_rune_page";
            String pageTime = "item.old_legends.time_rune_page";
            boolean craftOk = false;

            ItemStack output = ItemStack.EMPTY;
            List<Integer> deleteList = new ArrayList<>();
            List<Integer> hasLapis = new ArrayList<>();

            deleteList.add(1);

            for (int i = 2; i < inventory.size(); i++) {
                Item itemSlot = inventory.get(i).getItem();
                if (itemSlot.equals(Items.LAPIS_LAZULI)) {
                    hasLapis.add(i);
                }
            }
            // Time Rune
            if (hasLapis.containsAll(Arrays.asList(3, 5, 7, 9)) && bookOfTheLegends.hasPage(inventory.get(BOOK_SLOT), pageTime)) {
                output = OLItem.TIME_RUNE.getDefaultStack();
                deleteList.addAll(Arrays.asList(3, 5, 7, 9));
                //Death Rune
            } else if (hasLapis.containsAll(Arrays.asList(3, 7)) && bookOfTheLegends.hasPage(inventory.get(BOOK_SLOT), pageDeath)) {
                output = OLItem.DEATH_RUNE.getDefaultStack();
                deleteList.addAll(Arrays.asList(3, 7));
                // Sky Rune
            } else if (hasLapis.containsAll(Arrays.asList(6, 8)) && bookOfTheLegends.hasPage(inventory.get(BOOK_SLOT), pageSky)) {
                output = OLItem.SKY_RUNE.getDefaultStack();
                deleteList.addAll(Arrays.asList(6, 8));
            }


            ItemStack outputSlotStack = inventory.get(OUTPUT_SLOT);
            if (outputSlotStack.isEmpty()) {
                this.setStack(OUTPUT_SLOT, output);
            } else if (outputSlotStack.getItem() == output.getItem() && outputSlotStack.getCount() < outputSlotStack.getMaxCount()) {
                outputSlotStack.increment(1);
            } else {
                return;
            }

            craftOk = true;

            if (!(output.equals(ItemStack.EMPTY)) && craftOk) {
                for (Integer i : deleteList) {
                    inventory.get(i).decrement(1);
                }
            }
        }
    }

    public void craftEnchantment() {
        if (inventory.get(BOOK_SLOT).getItem() instanceof BookOfTheLegends bookOfTheLegends) {

            String page = "item.old_legends.vengeance_page";
            int level = 0;
            List<Integer> deleteList = new ArrayList<>();
            deleteList.add(1);

            for (int i = 2; i < inventory.size(); i++) {
                Item itemSlot = inventory.get(i).getItem();
                if (level == 3) break;
                if (itemSlot.equals(OLItem.DEATH_RUNE)) {
                    deleteList.add(i);
                    level++;
                }
            }

            if (bookOfTheLegends.hasPage(inventory.get(BOOK_SLOT), page)) {
                ItemStack output = new ItemStack(Items.ENCHANTED_BOOK);

                if (!(level >= 1)) return;
                ItemStack outputSlotStack = inventory.get(OUTPUT_SLOT);
//                if (isOutputSlotEmptyOrReceivable(outputSlotStack)) {
//
//                    Enchantment enchantment = OLEnchantment.VENGEANCE;
//                    Map<Enchantment, Integer> enchantments = new HashMap<>();
//                    enchantments.put(enchantment, level);
//
//                    EnchantmentHelper.set(enchantments, output);
//
//
//                    for (Integer index : deleteList) {
//                        setStack(index, ItemStack.EMPTY);
//                    }
//
//
//                    this.setStack(OUTPUT_SLOT, output);
//
//                }
            }
        }
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return new RuneTableData(this.pos);
    }
}
