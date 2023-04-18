package net.satisfyu.beachparty.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import net.satisfyu.beachparty.util.BeachpartyTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "scheduledTick", at = @At("TAIL"))
    private void injected(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (state.getBlock() == Blocks.HAY_BLOCK) {
            if (world.getBiome(pos).isIn(BeachpartyTags.WARM_BIOME)) {
                world.setBlockState(pos, ObjectRegistry.DRIED_WHEAT_BLOCK.getDefaultState());
            }
        }
    }
}
