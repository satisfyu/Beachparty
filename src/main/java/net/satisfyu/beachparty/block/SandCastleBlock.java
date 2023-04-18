package net.satisfyu.beachparty.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class SandCastleBlock extends Block {
    private static final VoxelShape BASE_SHAPE = VoxelShapes.union(Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 1.0, 15.0), Block.createCuboidShape(2.0, 1.0, 2.0, 14.0, 6.0, 14.0));
    public static final BooleanProperty TALL_TOWER;
    public static final VoxelShape TALL_TOWER_SHAPE = Block.createCuboidShape(11.0, 0.0, 11.0, 15.0, 15.0, 15.0);
    public static final BooleanProperty RIGHT_TOWER;
    public static final VoxelShape RIGHT_TOWER_SHAPE = Block.createCuboidShape(1.0, 1.0, 11.0, 5.0, 12.0, 15.0);
    public static final BooleanProperty TOP_TOWER;
    public static final VoxelShape TOP_TOWER_SHAPE = Block.createCuboidShape(5.0, 6.0, 5.0, 11.0, 9.0, 11.0);
    public static final BooleanProperty LEFT_TOWER;
    public static final VoxelShape LEFT_TOWER_SHAPE = Block.createCuboidShape(11.0, 1.0, 1.0, 15.0, 12.0, 5.0);


    public SandCastleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TALL_TOWER, false).with(RIGHT_TOWER, false).with(TOP_TOWER, false).with(LEFT_TOWER, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.union(BASE_SHAPE);
        if (state.get(TALL_TOWER)) {
            shape = VoxelShapes.union(shape, TALL_TOWER_SHAPE);
        }
        if (state.get(RIGHT_TOWER)) {
            shape = VoxelShapes.union(shape, RIGHT_TOWER_SHAPE);
        }
        if (state.get(LEFT_TOWER)) {
            shape = VoxelShapes.union(shape, LEFT_TOWER_SHAPE);
        }
        if (state.get(TOP_TOWER)) {
            shape = VoxelShapes.union(shape, TOP_TOWER_SHAPE);
        }
        return shape;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack handStack = player.getStackInHand(hand);
        if (handStack.getItem() == ObjectRegistry.SAND_BUCKET && !hasAllTowers(state)) {
            BooleanProperty tower = getTowerHitPos(hit);
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
                BooleanProperty tower = getTowerHitPos(hit);
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
        PlayerInventory inventory = player.getInventory();
        int slot = inventory.getSlotWithStack(handStack);
        handStack.decrement(1);
        if(player.getInventory().getStack(slot).isEmpty()) {
            if (!inventory.insertStack(slot, returnStack)) {
                player.dropItem(returnStack, false);
            }
        } else {
            if (!inventory.insertStack(returnStack)) {
                player.dropItem(returnStack, false);
            }
        }

    }

    private BooleanProperty getTowerHitPos(BlockHitResult hitResult) {
        double x = hitResult.getPos().getX();
        double z = hitResult.getPos().getZ();

        double relX = x - hitResult.getBlockPos().getX();
        double relZ = z - hitResult.getBlockPos().getZ();

        if (relX >= 0.5 && relZ >= 0.5) {
            return TALL_TOWER;
        } else if (relX < 0.5 && relZ >= 0.5) {
            return RIGHT_TOWER;
        } else if (relX >= 0.5 && relZ < 0.5) {
            return LEFT_TOWER;
        } else {
            return TOP_TOWER;
        }
    }

    private boolean hasAllTowers(BlockState state) {
        return state.get(TALL_TOWER) && state.get(RIGHT_TOWER) && state.get(TOP_TOWER) && state.get(LEFT_TOWER);
    }

    private boolean hasNoTowers(BlockState state) {
        return !state.get(TALL_TOWER) && !state.get(RIGHT_TOWER) && !state.get(TOP_TOWER) && !state.get(LEFT_TOWER);
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
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ObjectRegistry.SAND_BUCKET);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TALL_TOWER, RIGHT_TOWER, TOP_TOWER, LEFT_TOWER);
    }

    static {
        TALL_TOWER = BooleanProperty.of("tall");
        RIGHT_TOWER = BooleanProperty.of("right");
        TOP_TOWER = BooleanProperty.of("top");
        LEFT_TOWER = BooleanProperty.of("left");
    }
}