package net.satisfyu.beachparty.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class SandCastleBlock extends Block {
    public static final DirectionProperty FACING;
    public static final BooleanProperty TOP_TOWER;
    public static final BooleanProperty RIGHT_TOWER;
    public static final BooleanProperty BOTTOM_TOWER;
    public static final BooleanProperty LEFT_TOWER;
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);

    public SandCastleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TOP_TOWER, false).with(RIGHT_TOWER, false).with(BOTTOM_TOWER, false).with(LEFT_TOWER, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack handStack = player.getStackInHand(hand);
        if (handStack.getItem() == ObjectRegistry.SAND_BUCKET && !hasAllTowers(state)) {
            BooleanProperty tower = getTowerHitPos(state, hit);
            if (!state.get(tower)) {
                world.setBlockState(pos, state.with(tower, true));
                exchangeStack(handStack, player, new ItemStack(ObjectRegistry.EMPTY_SAND_BUCKET));
                return ActionResult.success(world.isClient);
            }
        }
        else if (handStack.getItem() == ObjectRegistry.EMPTY_SAND_BUCKET) {
            if (hasNoTowers(state)) {
                world.breakBlock(pos, false);
                exchangeStack(handStack, player, new ItemStack(ObjectRegistry.SAND_BUCKET));
                return ActionResult.success(world.isClient);
            } else if (!hasNoTowers(state)) {
                BooleanProperty tower = getTowerHitPos(state, hit);
                if (state.get(tower)) {
                    world.setBlockState(pos, state.with(tower, false));
                    exchangeStack(handStack, player, new ItemStack(ObjectRegistry.SAND_BUCKET));
                    return ActionResult.success(world.isClient);
                }

            }
        }
        return ActionResult.PASS;
    }

    private void exchangeStack(ItemStack handStack, PlayerEntity player, ItemStack possibleReturnStack) {
        ItemStack returnStack = !player.getAbilities().creativeMode ? possibleReturnStack : handStack;
        if (player.isCreative()) {
            if (!player.getInventory().contains(returnStack)) {
                player.getInventory().insertStack(returnStack);
            }
            return;
        }
        int slot = player.getInventory().getSlotWithStack(handStack);
        handStack.decrement(1);
        if (!player.getInventory().insertStack(slot, returnStack)) {
            player.dropItem(returnStack, false);
        }
    }

    private BooleanProperty getTowerHitPos(BlockState state, BlockHitResult hitResult) {
        double x = hitResult.getPos().getX();
        double z = hitResult.getPos().getZ();

        double relX = x - hitResult.getBlockPos().getX();
        double relZ = z - hitResult.getBlockPos().getZ();

        switch (state.get(FACING)) {
            case EAST -> {
                if (relX < 0.5 && relZ >= 0.5) {
                    return TOP_TOWER;
                } else if (relX < 0.5 && relZ < 0.5) {
                    return RIGHT_TOWER;
                } else if (relX >= 0.5 && relZ >= 0.5) {
                    return LEFT_TOWER;
                } else {
                    return BOTTOM_TOWER;
                }
            }
            case SOUTH -> {
                if (relX < 0.5 && relZ < 0.5) {
                    return TOP_TOWER;
                } else if (relX >= 0.5 && relZ < 0.5) {
                    return RIGHT_TOWER;
                } else if (relX < 0.5 && relZ >= 0.5) {
                    return LEFT_TOWER;
                } else {
                    return BOTTOM_TOWER;
                }
            }
            case WEST -> {
                if (relX >= 0.5 && relZ < 0.5) {
                    return TOP_TOWER;
                } else if (relX >= 0.5 && relZ >= 0.5) {
                    return RIGHT_TOWER;
                } else if (relX < 0.5 && relZ < 0.5) {
                    return LEFT_TOWER;
                } else {
                    return BOTTOM_TOWER;
                }
            }
            default -> {
                if (relX >= 0.5 && relZ >= 0.5) {
                    return TOP_TOWER;
                } else if (relX < 0.5 && relZ >= 0.5) {
                    return RIGHT_TOWER;
                } else if (relX >= 0.5 && relZ < 0.5) {
                    return LEFT_TOWER;
                } else {
                    return BOTTOM_TOWER;
                }
            }
        }
    }

    private boolean hasAllTowers(BlockState state) {
        return state.get(TOP_TOWER) && state.get(RIGHT_TOWER) && state.get(BOTTOM_TOWER) && state.get(LEFT_TOWER);
    }

    private boolean hasNoTowers(BlockState state) {
        return !state.get(TOP_TOWER) && !state.get(RIGHT_TOWER) && !state.get(BOTTOM_TOWER) && !state.get(LEFT_TOWER);
    }

    public static ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        return !player.getAbilities().creativeMode ? new ItemStack(ObjectRegistry.EMPTY_SAND_BUCKET) : stack;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = Direction.UP;
        BlockPos blockPos = pos.down();
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {

            world.breakBlock(pos, true);
        }
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TOP_TOWER, RIGHT_TOWER, BOTTOM_TOWER, LEFT_TOWER);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        TOP_TOWER = BooleanProperty.of("top");
        RIGHT_TOWER = BooleanProperty.of("right");
        BOTTOM_TOWER = BooleanProperty.of("bottom");
        LEFT_TOWER = BooleanProperty.of("left");
    }

}
