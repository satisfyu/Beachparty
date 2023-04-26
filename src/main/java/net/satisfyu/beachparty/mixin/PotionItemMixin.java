package net.satisfyu.beachparty.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void onSand(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack itemStack = context.getStack();
        BlockState blockState = world.getBlockState(blockPos);
        if (context.getSide() != Direction.DOWN && (blockState.getBlock() == Blocks.SAND || blockState.getBlock() == Blocks.GRAVEL) && PotionUtil.getPotion(itemStack) == Potions.WATER) {
            world.playSound( null, blockPos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.PLAYERS, 1.0F, 1.0F);
            playerEntity.setStackInHand(context.getHand(), ItemUsage.exchangeStack(itemStack, playerEntity, new ItemStack(Items.GLASS_BOTTLE)));
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;

                for (int i = 0; i < 5; ++i) {
                    serverWorld.spawnParticles(ParticleTypes.SPLASH, (double) blockPos.getX() + world.random.nextDouble(), (double) (blockPos.getY() + 1), (double) blockPos.getZ() + world.random.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
                }
            }

            world.playSound( null, blockPos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent( null, GameEvent.FLUID_PLACE, blockPos);
            world.setBlockState(blockPos, blockState.getBlock() == Blocks.GRAVEL ? Blocks.SAND.getDefaultState() : ObjectRegistry.SANDWAVES.getDefaultState());
            cir.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}
