package net.satisfyu.beachparty.item;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class IcecreamItem extends Item {
    public IcecreamItem(Properties settings) {
        super(settings);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {


        tooltip.add(Component.translatable("tooltip.beachparty.icecream").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }



}
