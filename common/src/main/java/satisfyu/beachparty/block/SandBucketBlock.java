package satisfyu.beachparty.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import satisfyu.beachparty.registry.ObjectRegistry;
import satisfyu.beachparty.util.BeachpartyUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SandBucketBlock extends FacingBlock {
    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> box(4, 0, 6, 10, 6, 12);

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL.stream().toList()) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    public SandBucketBlock(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (state.getBlock() == ObjectRegistry.EMPTY_SAND_BUCKET_BLOCK && itemStack.getItem() == Items.SAND) {
            itemStack.shrink(1);
            world.setBlockAndUpdate(pos, ObjectRegistry.SAND_BUCKET_BLOCK.defaultBlockState().setValue(FACING, state.getValue(FACING)));
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, hand, hit);
    }
}
