package net.satisfyu.beachparty.item;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CactusIceCreamItem extends Item {
    public CactusIceCreamItem() {
        super(new Item.Settings().group(ItemGroup.FOOD).food(
                (new FoodComponent.Builder())
                        .hunger(2)
                        .saturationModifier(-1.0f)
                        .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 600, 0), 1.0f) // add +luck effect for 30 seconds
                        .build()
        ));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.damage(DamageSource.MAGIC, 2.0f);
        return super.use(world, player, hand);
    }
}