package net.satisfyu.beachparty.item;

import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageInABottleItem extends BlockItem {
    public MessageInABottleItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.success(user.getStackInHand(hand));
        }
            List<ItemStack> possibleItems = new ArrayList<>();
            possibleItems.add(new ItemStack(Items.PAPER));
            possibleItems.add(new ItemStack(Items.MAP));
            possibleItems.add(new ItemStack(Items.FILLED_MAP));

            Random random = new Random();
            int randomNumber = random.nextInt(100);
            ItemStack itemStack;

            if (randomNumber < 50) {
                itemStack = new ItemStack(Items.PAPER);
            } else if (randomNumber < 80) {
                itemStack = new ItemStack(Items.MAP);
            } else {
                itemStack = new ItemStack(Items.FILLED_MAP);
            }

            ItemEntity entity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), itemStack);
            world.spawnEntity(entity);

            ItemStack glassBottleStack = new ItemStack(Items.GLASS_BOTTLE, 1);
            ItemEntity glassBottleEntity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), glassBottleStack);
            world.spawnEntity(glassBottleEntity);

        user.getStackInHand(hand).decrement(1);

        return TypedActionResult.success(user.getStackInHand(hand));
    }

}

