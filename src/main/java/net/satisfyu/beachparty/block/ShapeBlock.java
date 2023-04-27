package net.satisfyu.beachparty.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ShapeBlock extends Block {
    protected final VoxelShape SHAPE;
    public ShapeBlock(Settings settings, VoxelShape shape) {
        super(settings);
        SHAPE = shape;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("tooltip.beachparty.message").formatted(Formatting.ITALIC, Formatting.GRAY));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItemStack = player.getStackInHand(hand);
        Random random = new Random();
        int chance = random.nextInt(100);

        if (heldItemStack.isEmpty()) {
            return super.onUse(state, world, pos, player, hand, hit);
        } else if (chance < 10) {
            player.dropItem(new ItemStack(Items.MAP), true);
            player.dropItem(new ItemStack(Items.GLASS_BOTTLE), true);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else if (chance < 40) {
            player.dropItem(new ItemStack(Items.MAP), true);
            player.dropItem(new ItemStack(Items.GLASS_BOTTLE), true);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else {
            player.dropItem(new ItemStack(Items.PAPER), true);
            player.dropItem(new ItemStack(Items.GLASS_BOTTLE), true);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        heldItemStack.decrement(1);

        return ActionResult.SUCCESS;
    }
}
