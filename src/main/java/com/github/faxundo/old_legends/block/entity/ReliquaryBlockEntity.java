package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.ImplementedInventory;
import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.generic.OLGenericRune;
import com.github.faxundo.old_legends.particle.OLParticle;
import com.github.faxundo.old_legends.screen.custom.ReliquaryScreenHandler;
import com.github.faxundo.old_legends.screen.data.ReliquaryData;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReliquaryBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int points;
    private int lock;
    private boolean bl = false;

    public ReliquaryBlockEntity(BlockPos pos, BlockState state) {
        super(OLBlockEntity.RELIQUARY_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ReliquaryBlockEntity.this.points;
                    case 1 -> ReliquaryBlockEntity.this.lock;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ReliquaryBlockEntity.this.points = value;
                    case 1 -> ReliquaryBlockEntity.this.lock = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void onOpen(PlayerEntity player) {
        player.playSound(SoundEvents.BLOCK_CHEST_OPEN, 0.6f, 0.0f);
        player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, 1.4f, 0.0f);
        if (lock == 1)
            player.sendMessage(Text.translatable("block.old_legends.reliquary.alert").setStyle(OLHelper.getStyle("error")));
    }

    @Override
    public void onClose(PlayerEntity player) {
        player.playSound(SoundEvents.BLOCK_CHEST_CLOSE, 0.8f, 0.0f);
        player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, 1.2f, 0.0f);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.old_legends.reliquary");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt,inventory,registryLookup);
        nbt.putInt("reliquary.points", points);
        nbt.putInt("reliquary.lock", lock);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        points = nbt.getInt("reliquary.points");
        lock = nbt.getInt("reliquary.lock");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ReliquaryScreenHandler(syncId, playerInventory, this, this.propertyDelegate,
                ScreenHandlerContext.create(this.getWorld(), this.getPos()), lock);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

        if (world.isClient) {
            return;
        }
        if (!world.getRegistryKey().getValue().toShortTranslationKey().equals("overworld")) {
            return;
        }

        if (lock == 0) {
            return;
        }

        if (!bl) {
            spawnLockParticle();
            bl = true;
        }

        if (world.getTimeOfDay() != OldLegends.CONFIG.reliquary.grinningHoarderTime) {
            return;
        }
        world.playSound(null, pos, OLSound.GRINNING_HOARDER_LAUGH, SoundCategory.BLOCKS, 1.0F, 0.0F);

        inventory.clear();
        world.removeBlock(pos, false);
        ItemScatterer.spawn(world, pos, dropReliquary(points));
    }

    public Inventory dropReliquary(int value) {

        List<ItemStack> list = new ArrayList<>();

        while (value > 0) {

            if (value >= 20) {
                list.add(new ItemStack(OLItem.PALE_GEM));
                value -= 20;
            } else if (value >= 10) {

                List<Item> runes = new ArrayList<>();
                List<Item> items = Registries.ITEM.stream().filter(item -> item.getTranslationKey().contains("rune")).toList();
                for (Item rune : items) {
                    if (rune instanceof OLGenericRune && !rune.getTranslationKey().contains("blank")) {
                        runes.add(rune);
                    }
                }
                double randomNumber = OLHelper.getRandomNumber(0, runes.size() - 1);
                list.add(runes.get((int) randomNumber).getDefaultStack());

                value -= 10;
            } else if (value >= 7) {
                list.add(new ItemStack(Items.LAPIS_LAZULI));
                value -= 7;
            } else if (value >= 5) {
                list.add(new ItemStack(Items.REDSTONE));
                value -= 5;
            } else {
                list.add(new ItemStack(Items.COAL));
                value--;
            }
        }
        return new SimpleInventory(list.toArray(new ItemStack[0]));
    }


    private void spawnLockParticle() {
        if (this.world != null) {
            OLHelperParticle.spawnParticle(this.world, OLParticle.LOCK, this.pos.getX() + 0.5, this.pos.getY() + 0.8, this.pos.getZ() + 0.5, 0, 0, 0);
        }
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity player) {
        return new ReliquaryData(this.getPos());
    }
}
