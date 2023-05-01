package satisfyu.beachparty.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import satisfyu.beachparty.entity.chair.ChairUtil;
import satisfyu.beachparty.util.BeachpartyUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DeckChairBlock extends BeachChairBlock {
    private static final Supplier<VoxelShape> bottomShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0, 1, 0.375, 1));
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0, 1, 0.375, 1));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0.36875, 0.0625, 1, 0.49375, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0, 0.1875, 0.25, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.875, 0.1875, 0.25, 1));

        return shape;
    };

    public static final Map<Direction, VoxelShape> BOTTOM_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, bottomShapeSupplier.get()));
        }
    });

    private static final Supplier<VoxelShape> topShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0, 0.3125, 0.375, 1));
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0, 0.3125, 0.375, 1));
        shape = Shapes.or(shape, Shapes.box(0, 0.36875, 0.0625, 0.25, 0.49375, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0, 0.3125, 0.25, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.875, 0.3125, 0.25, 1));

        return shape;
    };

    public static final Map<Direction, VoxelShape> TOP_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, topShapeSupplier.get()));
        }
    });

    public DeckChairBlock(Properties settings) {
        super(settings);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return state.getValue(PART) == BedPart.HEAD ? TOP_SHAPE.get(direction) : BOTTOM_SHAPE.get(direction);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return ChairUtil.onUse(world, player, hand, hit, -1.0);
    }

}
