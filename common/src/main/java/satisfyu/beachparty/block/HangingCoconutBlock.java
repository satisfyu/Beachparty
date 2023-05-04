package satisfyu.beachparty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingCoconutBlock extends Block {
    public HangingCoconutBlock(Properties properties) {
        super(properties);
    }


    private static final VoxelShape SHAPE = Block.box(4.0, 7.0, 4.0, 12.0, 15.0, 12.0);

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void neighborChanged(BlockState state, net.minecraft.world.level.Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.getBlockState(pos.above()).getBlock() instanceof AirBlock) {
            worldIn.destroyBlock(pos, true);
        }
    }
}
