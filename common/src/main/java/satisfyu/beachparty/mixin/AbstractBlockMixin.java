package satisfyu.beachparty.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import satisfyu.beachparty.registry.ObjectRegistry;
import satisfyu.beachparty.util.BeachpartyTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class AbstractBlockMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void injected(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (state.getBlock() == Blocks.HAY_BLOCK) {
            if (world.getBiome(pos).is(BeachpartyTags.WARM_BIOME)) {
                world.setBlockAndUpdate(pos, ObjectRegistry.DRIED_WHEAT_BLOCK.get().defaultBlockState());

            }
        }
    }
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        Direction direction = Direction.getRandom(randomSource);
        if (direction != Direction.UP) {
            BlockPos blockPos2 = blockPos.relative(direction);
            BlockState blockState2 = level.getBlockState(blockPos2);
            if (!blockState.canOcclude() || !blockState2.isFaceSturdy(level, blockPos2, direction.getOpposite())) {
                double d = (double)blockPos.getX();
                double e = (double)blockPos.getY();
                double f = (double)blockPos.getZ();
                if (direction == Direction.DOWN) {
                    e -= 0.05;
                    d += randomSource.nextDouble();
                    f += randomSource.nextDouble();
                } else {
                    e += randomSource.nextDouble() * 0.8;
                    if (direction.getAxis() == Direction.Axis.X) {
                        f += randomSource.nextDouble();
                        if (direction == Direction.EAST) {
                            ++d;
                        } else {
                            d += 0.05;
                        }
                    } else {
                        d += randomSource.nextDouble();
                        if (direction == Direction.SOUTH) {
                            ++f;
                        } else {
                            f += 0.05;
                        }
                    }
                }

                level.addParticle(ParticleTypes.DRIPPING_WATER, d, e, f, 0.0, 0.0, 0.0);
            }
        }
    }
}
