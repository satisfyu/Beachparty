package net.satisfyu.beachparty.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import net.satisfyu.beachparty.util.BeachpartyUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SandBucketBlock extends FacingBlock {
    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> Block.createCuboidShape(4, 0, 6, 10, 6, 12);

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL.stream().toList()) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    public SandBucketBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.get(state.get(FACING));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (state.getBlock() == ObjectRegistry.EMPTY_SAND_BUCKET_BLOCK && itemStack.getItem() == Items.SAND) {
            itemStack.decrement(1);
            world.setBlockState(pos, ObjectRegistry.SAND_BUCKET_BLOCK.getDefaultState().with(FACING, state.get(FACING)));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
