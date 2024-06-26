package com.github.faxundo.old_legends.block.entity;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.ImplementedInventory;
import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.particle.OLParticle;
import com.github.faxundo.old_legends.screen.ReliquaryScreenHandler;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
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
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("reliquary.points", points);
        nbt.putInt("reliquary.lock", lock);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
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
        if (!world.getDimensionKey().getValue().toShortTranslationKey().equals("overworld")) {
            return;
        }
//        BlockPos downPos = pos.down();
//        Block blockDown = world.getBlockState(downPos).getBlock();
//        if (blockDown.equals(Blocks.GOLD_BLOCK)) {
//        }
        if (lock == 0) {
            return;
        }

        if (world.getTimeOfDay() >= OldLegends.CONFIG.reliquary.grinningHoarderTime - 3600 && !bl) {
            OLHelperParticle.spawnParticle(world, OLParticle.LOCK, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, 0 , 0, 0);
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
            double randomNumber = OLHelper.getRandomNumber(1, 3);
            if (value >= 20) {
                list.add(new ItemStack(OLItem.PALE_GEM));
                value -= 20;
            } else if (value >= 10) {
                if (randomNumber == 1) {
                    list.add(new ItemStack(OLItem.DEATH_RUNE));
                } else if (randomNumber == 2) {
                    list.add(new ItemStack(OLItem.SKY_RUNE));
                } else {
                    list.add(new ItemStack(OLItem.TIME_RUNE));
                }
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
}
