package net.satisfyu.beachparty.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.beachparty.util.BeachpartyTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "onPlaced", at = @At("TAIL"))
    private void injected(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (state.getBlock() == Blocks.HAY_BLOCK) {
            if (world.getBiome(pos).is(BeachpartyTags.WARM_BIOME)) {
                int ticks = RandomSource.create().nextIntBetweenInclusive(30, 300);
                world.scheduleTick(pos, Blocks.HAY_BLOCK, ticks * 20);
            }
        }
    }
}
