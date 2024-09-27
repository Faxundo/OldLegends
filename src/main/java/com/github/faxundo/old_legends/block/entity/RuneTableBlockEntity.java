package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.block.ImplementedInventory;
import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.custom.BookOfTheLegends;
import com.github.faxundo.old_legends.screen.custom.RuneTableScreenHandler;
import com.github.faxundo.old_legends.screen.data.RuneTableData;
import com.github.faxundo.old_legends.util.OLTag;
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

import java.util.*;

public class RuneTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
    private boolean northPillar;
    private boolean southPillar;
    private boolean eastPillar;
    private boolean westPillar;
    public static final int BOOK_SLOT = 0;
    public static final int OUTPUT_SLOT = 10;
    public static final int MARKER_SLOT = 1;

    private ItemStack itemOutput = ItemStack.EMPTY;
    private boolean craftOk = false;
    private Map<Integer, Integer> deleteList = new HashMap<>();
    private List<Integer> lapisList = new ArrayList<>();

    private final List<Integer> timeRune = Arrays.asList(3, 5, 7, 9);
    private final List<Integer> deathRune = Arrays.asList(3, 7);
    private final List<Integer> skyRune = Arrays.asList(6, 8);

    public RuneTableBlockEntity(BlockPos pos, BlockState state) {
        super(OLBlockEntity.RUNE_TABLE_BLOCK_ENTITY, pos, state);
    }

    public ItemStack getItemOutput() {
        return itemOutput;
    }

    public void setItemOutput(ItemStack itemOutput) {
        this.itemOutput = itemOutput;
    }

    public boolean getCraftOk() {
        return craftOk;
    }

    public void setCraftOk(boolean craftOk) {
        this.craftOk = craftOk;
    }

    public Map<Integer, Integer> getDeleteList() {
        return deleteList;
    }

    public void clearDeleteList() {
        deleteList.clear();
    }

    public void clearLapisList() {
        lapisList.clear();
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

        //Clear meta-data
        if (!(inventory.get(BOOK_SLOT).getItem().equals(OLItem.BOOK_OF_THE_LEGENDS)
                || inventory.get(MARKER_SLOT).isIn(OLTag.Items.CAN_AWAKE)
                || inventory.get(MARKER_SLOT).getItem().equals(OLItem.BLANK_RUNE)) && !inventory.get(OUTPUT_SLOT).isEmpty() || verifyIngredients()) {
            clear();
        }

        //Craft methods
        if (inventory.get(MARKER_SLOT).isIn(OLTag.Items.CAN_AWAKE)) {
            markDirty(world, pos, state);
            return;
        }
        if (inventory.get(MARKER_SLOT).getItem().equals(OLItem.BLANK_RUNE)) {
            craftRune();
            markDirty(world, pos, state);
            return;
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

    public void craftRune() {

        if (inventory.get(BOOK_SLOT).getItem() instanceof BookOfTheLegends bookOfTheLegends) {

            deleteList.put(1, 1);

            //Check what slots have lapis
            for (int i = 2; i < inventory.size(); i++) {
                Item itemSlot = inventory.get(i).getItem();
                if (itemSlot.equals(Items.LAPIS_LAZULI)) {
                    lapisList.add(i);
                }
            }

            // Time Rune
            if (lapisList.containsAll(timeRune) && bookOfTheLegends.hasPage(inventory.get(BOOK_SLOT), new ItemStack(OLItem.TIME_RUNE_PAGE))
                    && slotsIsEmpty(Arrays.asList(2, 4, 6, 8))) {
                itemOutput = OLItem.TIME_RUNE.getDefaultStack();
                timeRune.forEach(slot -> deleteList.put(slot, 1));
                //Death Rune
            } else if (lapisList.containsAll(deathRune) && bookOfTheLegends.hasPage(inventory.get(BOOK_SLOT), new ItemStack(OLItem.DEATH_RUNE_PAGE))
                    && slotsIsEmpty(Arrays.asList(2, 4, 5, 6, 8, 9))) {
                itemOutput = OLItem.DEATH_RUNE.getDefaultStack();
                deathRune.forEach(slot -> deleteList.put(slot, 1));
                // Sky Rune
            } else if (lapisList.containsAll(skyRune) && bookOfTheLegends.hasPage(inventory.get(BOOK_SLOT), new ItemStack(OLItem.SKY_RUNE_PAGE))
                    && slotsIsEmpty(Arrays.asList(2, 4, 5, 7, 9))) {
                itemOutput = OLItem.SKY_RUNE.getDefaultStack();
                skyRune.forEach(slot -> deleteList.put(slot, 1));
            }

            //Mark ready to craft
            if (isOutputSlotEmptyOrReceivable(itemOutput)) {
                inventory.set(OUTPUT_SLOT, itemOutput);
                craftOk = true;
            }
        }
    }

    public void clear() {
        itemOutput = ItemStack.EMPTY;
        deleteList.clear();
        lapisList.clear();
        setItemOutput(itemOutput);
        inventory.set(OUTPUT_SLOT, itemOutput);
        craftOk = false;
    }

    public boolean verifyIngredients() {
        ItemStack outputStackCopy = itemOutput.copy();
        if (outputStackCopy.isEmpty()) return false;
        if (outputStackCopy.equals(OLItem.DEATH_RUNE.getDefaultStack())) {
            return checkIngredients(deathRune);
        } else if (outputStackCopy.equals(OLItem.TIME_RUNE.getDefaultStack())) {
            return checkIngredients(timeRune);
        } else if (outputStackCopy.equals(OLItem.SKY_RUNE.getDefaultStack())) {
            return checkIngredients(skyRune);
        }
        return true;
    }

    private boolean checkIngredients(List<Integer> runeSlots) {
        for (Integer runeSlot : runeSlots) {
            ItemStack stackInSlot = inventory.get(runeSlot);
            if (stackInSlot.isEmpty() || !stackInSlot.getItem().equals(Items.LAPIS_LAZULI)) {
                return false;
            }
        }
        return true;
    }

    public boolean slotsIsEmpty(List<Integer> slots) {
        for (Integer slot : slots) {
            if (!inventory.get(slot).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean isOutputSlotEmptyOrReceivable(ItemStack output) {
        ItemStack stack = this.getStack(OUTPUT_SLOT);
        return stack.isEmpty() || (stack.getItem() == output.getItem() && stack.getCount() < stack.getMaxCount());
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return new RuneTableData(this.pos);
    }
}
