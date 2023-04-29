package net.satisfyu.beachparty.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.satisfyu.beachparty.Beachparty;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;


public class SeashellItem extends Item {
    public SeashellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.success(user.getStackInHand(hand));
        }

        List<ItemStack> possibleItems = new ArrayList<>();
        possibleItems.add(new ItemStack(Items.DIAMOND));
        possibleItems.add(new ItemStack(Items.REDSTONE));
        possibleItems.add(new ItemStack(Items.EMERALD));
        possibleItems.add(new ItemStack(Items.GOLD_NUGGET));
        possibleItems.add(new ItemStack(Items.LAPIS_LAZULI));
        possibleItems.add(new ItemStack(Items.FLINT));

        Random random = world.random;
        int randomNumber = random.nextInt(100);
        ItemStack itemStack;

        if (randomNumber < 2) {
            itemStack = new ItemStack(Items.DIAMOND);
        } else if (randomNumber < 7) {
            itemStack = new ItemStack(Items.REDSTONE);
        } else if (randomNumber < 17) {
            itemStack = new ItemStack(Items.EMERALD);
        } else if (randomNumber < 47) {
            itemStack = new ItemStack(Items.GOLD_NUGGET);
        } else if (randomNumber < 97) {
            itemStack = new ItemStack(Items.LAPIS_LAZULI);
        } else {
            itemStack = new ItemStack(Items.FLINT);
        }

        ItemEntity entity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), itemStack);
        world.spawnEntity(entity);

        ItemStack musselMeatStack = new ItemStack(ObjectRegistry.RAW_MUSSEL_MEAT, 1);
        ItemEntity musselMeatEntity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), musselMeatStack);
        world.spawnEntity(musselMeatEntity);

        user.getStackInHand(hand).decrement(1);

        return TypedActionResult.success(user.getStackInHand(hand));
    }

}