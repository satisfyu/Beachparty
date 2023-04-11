package net.satisfyu.beachparty.block;



import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.beachparty.util.BeachpartyLineConnectingType;



public class TableBlock extends BeachpartyLineConnectingBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED;
    public static final VoxelShape TOP_SHAPE;
    public static final VoxelShape[] LEG_SHAPES;

    public TableBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState().with(WATERLOGGED, false)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        BeachpartyLineConnectingType type = state.get(TYPE);

        if (type == BeachpartyLineConnectingType.MIDDLE) {
            return TOP_SHAPE;
        }

        if((direction == Direction.NORTH && type == BeachpartyLineConnectingType.LEFT) || (direction == Direction.SOUTH && type == BeachpartyLineConnectingType.RIGHT)){
            return VoxelShapes.union(TOP_SHAPE, LEG_SHAPES[0], LEG_SHAPES[3]);
        }
        else if((direction == Direction.NORTH && type == BeachpartyLineConnectingType.RIGHT) || (direction == Direction.SOUTH && type == BeachpartyLineConnectingType.LEFT)){
            return VoxelShapes.union(TOP_SHAPE, LEG_SHAPES[1], LEG_SHAPES[2]);
        }
        else if((direction == Direction.EAST && type == BeachpartyLineConnectingType.LEFT) || (direction == Direction.WEST && type == BeachpartyLineConnectingType.RIGHT)){
            return VoxelShapes.union(TOP_SHAPE, LEG_SHAPES[0], LEG_SHAPES[1]);
        }
        else if((direction == Direction.EAST && type == BeachpartyLineConnectingType.LEFT.RIGHT) || (direction == Direction.WEST && type == BeachpartyLineConnectingType.LEFT)){
            return VoxelShapes.union(TOP_SHAPE, LEG_SHAPES[2], LEG_SHAPES[3]);
        }
        return VoxelShapes.union(TOP_SHAPE, LEG_SHAPES);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {

        World world = context.getWorld();
        BlockPos clickedPos = context.getBlockPos();
        return super.getPlacementState(context).with(WATERLOGGED, world.getFluidState(clickedPos).getFluid() == Fluids.WATER);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        TOP_SHAPE = Block.createCuboidShape(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
        LEG_SHAPES = new VoxelShape[]{
                Block.createCuboidShape(0, 0, 0, 2, 13, 2), //north
                Block.createCuboidShape(14, 0, 0, 16, 13, 2), //south
                Block.createCuboidShape(14, 0, 14, 16, 13, 16), //west
                Block.createCuboidShape(0, 0, 14, 2, 13, 16) //east

        };
    }


}
