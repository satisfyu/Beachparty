package net.satisfyu.beachparty.item;

import net.minecraft.ChatFormatting;
import net.minecraft.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PoolNoodleItem extends SwordItem implements DyeableLeatherItem {

    public PoolNoodleItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
        tooltip.add(Component.translatable(  "tooltip.beachparty.dyeable").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }
}
