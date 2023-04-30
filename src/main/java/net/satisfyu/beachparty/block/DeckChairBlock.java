package net.satisfyu.beachparty.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.beachparty.entity.chair.ChairUtil;
import net.satisfyu.beachparty.util.BeachpartyUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DeckChairBlock extends BeachChairBlock {
    private static final Supplier<VoxelShape> bottomShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0, 1, 0.375, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0, 1, 0.375, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.36875, 0.0625, 1, 0.49375, 0.9375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0, 0.1875, 0.25, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0, 0.875, 0.1875, 0.25, 1));

        return shape;
    };

    public static final Map<Direction, VoxelShape> BOTTOM_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, bottomShapeSupplier.get()));
        }
    });

    private static final Supplier<VoxelShape> topShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0, 0.3125, 0.375, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.25, 0, 0.3125, 0.375, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0.36875, 0.0625, 0.25, 0.49375, 0.9375));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0, 0.3125, 0.25, 0.125));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.875, 0.3125, 0.25, 1));

        return shape;
    };

    public static final Map<Direction, VoxelShape> TOP_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, topShapeSupplier.get()));
        }
    });

    public DeckChairBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return state.get(PART) == BedPart.HEAD ? TOP_SHAPE.get(direction) : BOTTOM_SHAPE.get(direction);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ChairUtil.onUse(world, player, hand, hit, -1.0);
    }

}
