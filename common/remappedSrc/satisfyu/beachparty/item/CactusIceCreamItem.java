package satisfyu.beachparty.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CactusIceCreamItem extends Item {
    public CactusIceCreamItem(Item.Properties settings) {
        super(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(
                (new FoodProperties.Builder())
                        .nutrition(2)
                        .saturationMod(-1.0f)
                        .effect(new MobEffectInstance(MobEffects.LUCK, 600, 0), 1.0f) // add +luck effect for 30 seconds
                        .build()
        ));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        user.hurt(DamageSource.MAGIC, 2.0f);
        return super.use(world, user, hand);
    }
}