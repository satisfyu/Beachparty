package net.satisfyu.beachparty.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.StructureTags;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;

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

        Random random = new Random();
        int randomNumber = random.nextInt(100);
        ItemStack itemStack;

        if (randomNumber < 50) {
            itemStack = new ItemStack(Items.PAPER);
        } else if (randomNumber < 80) {
            itemStack = new ItemStack(Items.MAP);
        } else {
            itemStack = getRandomMap(user);
        }
        if(itemStack == null) itemStack = new ItemStack(Items.PAPER);

        ItemEntity entity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), itemStack);
        world.spawnEntity(entity);

        ItemStack glassBottleStack = new ItemStack(Items.GLASS_BOTTLE, 1);
        ItemEntity glassBottleEntity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), glassBottleStack);
        world.spawnEntity(glassBottleEntity);

        user.getStackInHand(hand).decrement(1);

        return TypedActionResult.success(user.getStackInHand(hand));
    }


    public static @Nullable ItemStack getRandomMap(Entity entity){
        int lootChest = entity.world.getRandom().nextInt(4);

        ItemStack map = null;

        switch (lootChest) {
            case 0 -> // Buried Treasure
                    map = createMansionMap(entity);
            case 1 -> // Shipwreck Map
                    map = createMansionMap(entity);
            case 2 -> // Shipwreck Supply
                    map = createMonumentMap(entity);
            case 3 -> // Shipwreck Treasure
                    map = createMonumentMap(entity);
        }
        return map;
    }


    public static ItemStack createMonumentMap(Entity entity){
        return createMap(entity, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapIcon.Type.MONUMENT);
    }

    public static ItemStack createMansionMap(Entity entity){
        return createMap(entity, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapIcon.Type.MANSION);
    }

    public static @Nullable ItemStack createMap(Entity entity, TagKey<Structure> structure, String nameKey, MapIcon.Type iconType) {
        if (!(entity.world instanceof ServerWorld serverWorld)) {
            return null;
        }
        BlockPos blockPos = serverWorld.locateStructure(structure, entity.getBlockPos(), 100, true);
        if (blockPos != null) {
            ItemStack itemStack = FilledMapItem.createMap(serverWorld, blockPos.getX(), blockPos.getZ(), (byte)2, true, true);
            FilledMapItem.fillExplorationMap(serverWorld, itemStack);
            MapState.addDecorationsNbt(itemStack, blockPos, "+", iconType);
            itemStack.setCustomName(Text.translatable(nameKey));
            return itemStack;
        }
        return null;
    }

}

