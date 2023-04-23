package net.satisfyu.beachparty.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
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

public class SandBucketItem extends BlockItem {
    public SandBucketItem(Block block, Settings settings) {
        super(block, settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer().isSneaking()) {
            ActionResult actionResult = this.place(new ItemPlacementContext(context));
            if (!actionResult.isAccepted() && this.isFood()) {
                ActionResult actionResult2 = this.use(context.getWorld(), context.getPlayer(), context.getHand()).getResult();
                return actionResult2 == ActionResult.CONSUME ? ActionResult.CONSUME_PARTIAL : actionResult2;
            }
            return actionResult;
        } else {
            return this.use(context.getWorld(), context.getPlayer(), context.getHand()).getResult();
        }
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
                if (this == ObjectRegistry.EMPTY_SAND_BUCKET && world.getBlockState(blockPos).isIn(BlockTags.SAND)) {
                    world.breakBlock(blockPos, false);
                    ItemStack sandBucket = new ItemStack(ObjectRegistry.SAND_BUCKET);
                    ItemStack returnStack = exchangeStack(itemStack, user, sandBucket);
                    return TypedActionResult.success(returnStack, world.isClient());
                } else if (this == ObjectRegistry.SAND_BUCKET && user.canPlaceOn(offsetPos, direction, itemStack)){
                    if (world.getBlockState(offsetPos).isAir() && ObjectRegistry.SANDCASTLE.getDefaultState().canPlaceAt(world, offsetPos)){
                        world.setBlockState(offsetPos, ObjectRegistry.SANDCASTLE.getDefaultState(), 3);
                        ItemStack returnStack = exchangeStack(itemStack, user, getEmptiedStack(itemStack, user));
                        return TypedActionResult.success(returnStack, world.isClient());
                    }
                }
            }
        }
        return TypedActionResult.fail(itemStack);
    }

    private ItemStack exchangeStack(ItemStack handStack, PlayerEntity player, ItemStack possibleReturnStack) {
        ItemStack returnStack = !player.getAbilities().creativeMode ? possibleReturnStack : handStack;
        if (player.isCreative()) {
            if (!player.getInventory().contains(possibleReturnStack)) {
                player.getInventory().insertStack(possibleReturnStack);
            }
            return handStack;
        }
        PlayerInventory inventory = player.getInventory();
        int slot = inventory.getSlotWithStack(handStack);
        handStack.decrement(1);
        if(player.getInventory().getStack(slot).isEmpty()) {
            if (!inventory.insertStack(slot, returnStack)) {
                player.dropItem(returnStack, false);
            }
        } else {
            if (!inventory.insertStack(returnStack)) {
                player.dropItem(returnStack, false);
            }
        }
        return possibleReturnStack;
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
