package net.satisfyu.beachparty.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.satisfyu.beachparty.util.BeachpartyTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "onPlaced", at = @At("TAIL"))
    private void injected(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (state.getBlock() == Blocks.HAY_BLOCK) {
            if (world.getBiome(pos).isIn(BeachpartyTags.WARM_BIOME)) {
                int ticks = Random.create().nextBetween(30, 300);
                world.createAndScheduleBlockTick(pos, Blocks.HAY_BLOCK, ticks * 20);
            }
        }
    }
}
