package net.satisfyu.beachparty.item;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import org.jetbrains.annotations.Nullable;

import static net.satisfyu.beachparty.block.SandCastleBlock.STAGE;

public class SandBucketItem extends Item {

    public SandBucketItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        } else {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos offsetPos = blockPos.offset(direction);
            if (world.canPlayerModifyAt(user, blockPos)) {
                if (this == ObjectRegistry.EMPTY_SAND_BUCKET && world.getBlockState(blockPos).getBlock() == Blocks.SAND) {
                    world.breakBlock(blockPos, false);
                    ItemStack sandBucket = new ItemStack(ObjectRegistry.SAND_BUCKET);
                    ItemStack returnStack = ItemUsage.exchangeStack(itemStack, user, sandBucket);
                    return TypedActionResult.success(returnStack, world.isClient());
                } else if (this == ObjectRegistry.SAND_BUCKET && user.canPlaceOn(offsetPos, direction, itemStack)){
                    if (world.getBlockState(blockPos).getBlock() == ObjectRegistry.SAND_CASTLE && world.getBlockState(blockPos).get(STAGE) < 5) {
                        BlockState sandCastleState = world.getBlockState(blockPos);
                        world.setBlockState(blockPos, sandCastleState.with(STAGE, world.getBlockState(blockPos).get(STAGE) + 1), 3);
                        return TypedActionResult.success(getEmptiedStack(itemStack, user), world.isClient());
                    } else if (world.getBlockState(offsetPos).isAir() && ObjectRegistry.SAND_CASTLE.getDefaultState().canPlaceAt(world, offsetPos)){
                        world.setBlockState(offsetPos, ObjectRegistry.SAND_CASTLE.getDefaultState(), 3);
                        return TypedActionResult.success(getEmptiedStack(itemStack, user), world.isClient());
                    }
                }
            }
        }
        return TypedActionResult.fail(itemStack);
    }

    public static ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        return !player.getAbilities().creativeMode ? new ItemStack(ObjectRegistry.EMPTY_SAND_BUCKET) : stack;
    }

    protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = SoundEvents.ITEM_BUCKET_EMPTY;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.emitGameEvent(player, GameEvent.BLOCK_PLACE, pos);
    }
}
