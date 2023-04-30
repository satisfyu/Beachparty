package satisfyu.beachparty.item;

import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import satisfyu.beachparty.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;


public class SeashellItem extends Item {
    public SeashellItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (world.isClientSide()) {
            return InteractionResultHolder.success(user.getItemInHand(hand));
        }

        List<ItemStack> possibleItems = new ArrayList<>();
        possibleItems.add(new ItemStack(Items.DIAMOND));
        possibleItems.add(new ItemStack(Items.REDSTONE));
        possibleItems.add(new ItemStack(Items.EMERALD));
        possibleItems.add(new ItemStack(Items.GOLD_NUGGET));
        possibleItems.add(new ItemStack(Items.LAPIS_LAZULI));
        possibleItems.add(new ItemStack(Items.FLINT));

        RandomSource random = world.random;
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
        world.addFreshEntity(entity);

        ItemStack musselMeatStack = new ItemStack(ObjectRegistry.RAW_MUSSEL_MEAT.get(), 1);
        ItemEntity musselMeatEntity = new ItemEntity(world, user.getX(), user.getY(), user.getZ(), musselMeatStack);
        world.addFreshEntity(musselMeatEntity);

        user.getItemInHand(hand).shrink(1);

        return InteractionResultHolder.success(user.getItemInHand(hand));
    }

}