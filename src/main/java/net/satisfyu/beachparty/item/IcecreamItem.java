package net.satisfyu.beachparty.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class IcecreamItem extends Item {
    public IcecreamItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {


        tooltip.add(Text.translatable("tooltip.beachparty.icecream").formatted(Formatting.ITALIC, Formatting.GRAY));
    }



}
