package com.github.faxundo.old_legends.block.custom;

import com.github.faxundo.old_legends.block.OLBlockEntity;
import com.github.faxundo.old_legends.block.entity.ReliquaryBlockEntity;
import com.github.faxundo.old_legends.util.OLHelper;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReliquaryBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final String DESCRIPTION = "block.old_legends.reliquary.description";
    public static final String LOCK = "block.old_legends.reliquary.lock";
    public static final String LOCK_DESCRIPTION = "block.old_legends.reliquary.lock_description";
    public static final String ALERT = "block.old_legends.reliquary.alert";
    public static final MapCodec<ReliquaryBlock> CODEC = createCodec(ReliquaryBlock::new);
    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 0, 2, 15, 5, 14),
            Block.createCuboidShape(1, 5, 2, 15, 8, 14),
            Block.createCuboidShape(7, 3, 1, 9, 6, 2));

    public ReliquaryBlock(Settings settings) {
        super(settings.nonOpaque().strength(20f).requiresTool().resistance(1200f));
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<ReliquaryBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ReliquaryBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(Properties.HORIZONTAL_FACING);
        return switch (facing) {
            case NORTH -> VoxelShapes.union(
                    Block.createCuboidShape(1, 0, 2, 15, 5, 14),
                    Block.createCuboidShape(1, 5, 2, 15, 8, 14),
                    Block.createCuboidShape(7, 3, 1, 9, 6, 2));
            case SOUTH -> VoxelShapes.union(
                    Block.createCuboidShape(1, 0, 2, 15, 5, 14),
                    Block.createCuboidShape(1, 5, 2, 15, 8, 14),
                    Block.createCuboidShape(7, 3, 14, 9, 6, 15));
            case EAST -> VoxelShapes.union(
                    Block.createCuboidShape(2, 0, 1, 14, 5, 15),
                    Block.createCuboidShape(2, 5, 1, 14, 8, 15),
                    Block.createCuboidShape(14, 3, 7, 15, 6, 9));
            case WEST -> VoxelShapes.union(
                    Block.createCuboidShape(2, 0, 1, 14, 5, 15),
                    Block.createCuboidShape(2, 5, 1, 14, 8, 15),
                    Block.createCuboidShape(1, 3, 7, 2, 6, 9));
            default -> SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(Properties.HORIZONTAL_FACING, rotation.rotate(state.get(Properties.HORIZONTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(Properties.HORIZONTAL_FACING)));
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ReliquaryBlockEntity) {
                ItemScatterer.spawn(world, pos, (ReliquaryBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((ReliquaryBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);

            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, OLBlockEntity.RELIQUARY_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        OLHelper.appendTooltipBlockHelper(stack, context, tooltip, options, DESCRIPTION);
    }
}
