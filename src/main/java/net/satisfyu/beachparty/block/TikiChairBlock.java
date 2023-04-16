package net.satisfyu.beachparty.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.*;
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

public class TikiChairBlock extends Block {
     public TikiChairBlock(Settings settings) {
          super(settings);
     }


         public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

         private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
              VoxelShape shape = VoxelShapes.empty();
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.1875, 0.375, 0.375, 0.25, 0.625));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0, 0.625, 0.6875, 0.5625, 0.6875));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0, 0.3125, 0.6875, 0.5625, 0.375));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.1875, 0.375, 0.6875, 0.25, 0.625));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.3125, 0.3125, 0.625, 0.375, 0.375));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.3125, 0.625, 0.625, 0.375, 0.6875));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.625, 0.375, 0.5625, 0.6875));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.375, 0.5625, 0.375));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.5625, 0.3125, 0.75, 0.6875, 0.6875));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.5625, 0.25, 0.6875, 0.6875, 0.3125));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.5625, 0.6875, 0.6875, 0.6875, 0.75));
             shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.6875, 0.3125, 0.6875, 0.75, 0.6875));


              return shape;
         };

         public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
              for (Direction direction : Direction.Type.HORIZONTAL.stream().toList()) {
                   map.put(direction, BeachpartyUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
              }
         });

         @Override
         public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
              return SHAPE.get(state.get(FACING));
         }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand
            hand, BlockHitResult hit) {
        return ChairUtil.onUse(world, player, hand, hit, 0.2);
    }


         @Override
         public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
              super.onStateReplaced(state, world, pos, newState, moved);
              ChairUtil.onStateReplaced(world, pos);
         }

         @Override
         protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
              builder.add(FACING);
         }

         @Override
         public BlockState getPlacementState(ItemPlacementContext ctx) {
              return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
         }


         @Override
         public BlockState rotate(BlockState state, BlockRotation rotation) {
              return state.with(FACING, rotation.rotate(state.get(FACING)));
         }

         @Override
         public BlockState mirror(BlockState state, BlockMirror mirror) {
              return state.rotate(mirror.getRotation(state.get(FACING)));
         }
    }

