package net.satisfyu.beachparty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class SandwaveBlock extends SandBlock {
    public SandwaveBlock(int color, Properties settings) {
        super(color, settings);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.000001 && validLocation(world, pos) && world.getBlockState(pos.above()).isAir()) {
            world.setBlockAndUpdate(pos.above(), ObjectRegistry.MESSAGE_IN_A_BOTTLE.defaultBlockState());
        }
        super.randomTick(state, world, pos, random);
    }

    private boolean validLocation(ServerLevel world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-2, 0, -2), pos.offset(2, 0, 2))) {
            if (world.getFluidState(blockPos).is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }
}
