package net.satisfyu.beachparty.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class SandwaveBlock extends SandBlock {
    public SandwaveBlock(int color, Settings settings) {
        super(color, settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.000001 && validLocation(world, pos) && world.getBlockState(pos.up()).isAir()) {
            world.setBlockState(pos.up(), ObjectRegistry.MESSAGE_IN_A_BOTTLE.getDefaultState());
        }
        super.randomTick(state, world, pos, random);
    }

    private boolean validLocation(ServerWorld world, BlockPos pos) {
        for (BlockPos blockPos : BlockPos.iterate(pos.add(-2, 0, -2), pos.add(2, 0, 2))) {
            if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }
}
