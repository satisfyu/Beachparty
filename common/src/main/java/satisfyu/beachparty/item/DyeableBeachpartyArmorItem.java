package satisfyu.beachparty.item;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;


public class DyeableBeachpartyArmorItem extends BeachpartyArmorItem implements DyeableLeatherItem {
    public DyeableBeachpartyArmorItem(ArmorMaterial material, EquipmentSlot slot, Item.Properties settings) {
        super(material, slot, settings);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("tooltip.beachparty.dyeable").withStyle(ChatFormatting.WHITE, ChatFormatting.ITALIC));
    }
}