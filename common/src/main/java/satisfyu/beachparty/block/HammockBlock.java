package net.satisfyu.beachparty.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.satisfyu.beachparty.block.enums.HammockPart;
import net.satisfyu.beachparty.util.BeachpartyUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HammockBlock extends HorizontalFacingBlock {
    public static final EnumProperty<HammockPart> PART;
    public static final BooleanProperty OCCUPIED;


    private static final Supplier<VoxelShape> leftShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(-0.1875, 0.62, 0.125, 0.07499999999999996, 0.6825, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 0, 0.4375, 0.8125, 1, 0.5625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.6875, 1, 0.4375, 0.8125, 1.3125, 0.5625));

        return shape;
    };

    public static final Map<Direction, VoxelShape> LEFT_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.NORTH, direction, leftShapeSupplier.get()));
        }
    });

    private static final Supplier<VoxelShape> middleShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.62, 0.125, 0.8125, 0.6825, 1));

        return shape;
    };

    public static final Map<Direction, VoxelShape> MIDDLE_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.NORTH, direction, middleShapeSupplier.get()));
        }
    });

    private static final Supplier<VoxelShape> rightShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.925, 0.62, 0.125, 1.1875, 0.6825, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.4375, 0.3125, 1, 0.5625));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 1, 0.4375, 0.3125, 1.3125, 0.5625));

        return shape;
    };

    public static final Map<Direction, VoxelShape> RIGHT_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.NORTH, direction, rightShapeSupplier.get()));
        }
    });

    public HammockBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(PART, HammockPart.MIDDLE).with(OCCUPIED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return state.get(PART) == HammockPart.LEFT ? LEFT_SHAPE.get(direction) : state.get(PART) == HammockPart.RIGHT ? RIGHT_SHAPE.get(direction) : MIDDLE_SHAPE.get(direction);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getPlayerFacing().getOpposite();
        BlockPos middle = ctx.getBlockPos();
        BlockPos left = middle.offset(direction.rotateCounterclockwise(Direction.Axis.Y));
        BlockPos right = middle.offset(direction.rotateClockwise(Direction.Axis.Y));
        World world = ctx.getWorld();
        if (ctx.getPlayer() != null) {
            ctx.getPlayer().sendMessage(Text.translatable("block.beachparty.notimplemented").formatted(Formatting.AQUA), true);
        }
        return world.getBlockState(left).canReplace(ctx) && world.getWorldBorder().contains(left) && world.getBlockState(right).canReplace(ctx) && world.getWorldBorder().contains(right)  ? this.getDefaultState().with(FACING, direction) : null;
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            BlockPos left = pos.offset(state.get(FACING).rotateClockwise(Direction.Axis.Y));
            BlockPos right = pos.offset(state.get(FACING).rotateCounterclockwise(Direction.Axis.Y));
            world.setBlockState(left, state.with(PART, HammockPart.LEFT), 3);
            world.setBlockState(right, state.with(PART, HammockPart.RIGHT), 3);
            world.updateNeighbors(pos, Blocks.AIR);
            state.updateNeighbors(world, pos, 3);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        HammockPart part = state.get(PART);
        Direction direction = state.get(FACING);
        switch (part) {
            case MIDDLE -> {
                BlockPos left = pos.offset(direction.rotateClockwise(Direction.Axis.Y));
                BlockPos right = pos.offset(direction.rotateCounterclockwise(Direction.Axis.Y));
                if (world.getBlockState(left).getBlock() == this) world.setBlockState(left, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                if (world.getBlockState(right).getBlock() == this) world.setBlockState(right, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
            }
            case LEFT -> {
                BlockPos middle = pos.offset(direction.rotateCounterclockwise(Direction.Axis.Y));
                BlockPos right = pos.offset(direction.rotateCounterclockwise(Direction.Axis.Y)).offset(direction.rotateCounterclockwise(Direction.Axis.Y));
                if (world.getBlockState(middle).getBlock() == this) world.setBlockState(middle, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                if (world.getBlockState(right).getBlock() == this) world.setBlockState(right, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
            }
            case RIGHT -> {
                BlockPos middle = pos.offset(direction.rotateClockwise(Direction.Axis.Y));
                BlockPos left = pos.offset(direction.rotateClockwise(Direction.Axis.Y)).offset(direction.rotateClockwise(Direction.Axis.Y));
                if (world.getBlockState(middle).getBlock() == this) world.setBlockState(middle, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                if (world.getBlockState(left).getBlock() == this) world.setBlockState(left, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        /*
        if (world.isClient || state.get(PART) != HammockPart.MIDDLE) {
            return ActionResult.CONSUME;
        }
        if (!isHammockWhole(state, world, pos)) {
            return ActionResult.CONSUME;
        }
        if (!isBedWorking(world)) {
            return ActionResult.SUCCESS;
        }

        if (state.get(OCCUPIED)) {
            player.sendMessage(Text.translatable("block.minecraft.bed.occupied"), true);
        } else {
            player.trySleep(pos).ifLeft(reason -> {
                if (reason.getMessage() != null) {
                    player.sendMessage(reason.getMessage(), true);
                }
            });
        }
        return ActionResult.SUCCESS;
         */
        return super.onUse(state, world, pos, player, hand, hit);
    }

    private boolean isHammockWhole(BlockState state, World world, BlockPos pos) {
        HammockPart part = state.get(PART);
        Direction direction = state.get(FACING);
        switch (part) {
            case MIDDLE -> {
                BlockPos left = pos.offset(direction.rotateClockwise(Direction.Axis.Y));
                BlockPos right = pos.offset(direction.rotateCounterclockwise(Direction.Axis.Y));
                return  (world.getBlockState(left).getBlock() == this) && (world.getBlockState(right).getBlock() == this);
            }
            case LEFT -> {
                BlockPos middle = pos.offset(direction.rotateCounterclockwise(Direction.Axis.Y));
                BlockPos right = pos.offset(direction.rotateCounterclockwise(Direction.Axis.Y)).offset(direction.rotateCounterclockwise(Direction.Axis.Y));
                return  (world.getBlockState(middle).getBlock() == this) && (world.getBlockState(right).getBlock() == this);
            }
            case RIGHT -> {
                BlockPos middle = pos.offset(direction.rotateClockwise(Direction.Axis.Y));
                BlockPos left = pos.offset(direction.rotateClockwise(Direction.Axis.Y)).offset(direction.rotateClockwise(Direction.Axis.Y));
                return  (world.getBlockState(middle).getBlock() == this) && (world.getBlockState(left).getBlock() == this);}
        }
        return false;
    }

    public static boolean isBedWorking(World world) {
        return world.getDimension().bedWorks();
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, Direction.UP);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OCCUPIED);
    }

    static {
        PART = EnumProperty.of("part", HammockPart.class);
        OCCUPIED = Properties.OCCUPIED;
    }

}