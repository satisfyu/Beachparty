package satisfyu.beachparty.client;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import satisfyu.beachparty.item.IBeachpartyArmorSet;

import java.util.List;

public class ClientUtil {

    public static void appendTooltip(List<Component> tooltip) {
        tooltip.add(Component.translatable(  "tooltip.beachparty.swimwearline1").withStyle(ChatFormatting.DARK_PURPLE));
        tooltip.add(Component.translatable(  "tooltip.beachparty.swimwearline2").withStyle(ChatFormatting.BLUE));

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        boolean helmet = IBeachpartyArmorSet.hasSwimearHelmet(player);
        boolean breastplate = IBeachpartyArmorSet.hasSwimwearBreastplate(player);
        boolean leggings = IBeachpartyArmorSet.hasSwimearLeggings(player);
        boolean boots = IBeachpartyArmorSet.hasSwimearBoots(player);

        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.translatable("tooltip.beachparty.swimwear_set").withStyle(ChatFormatting.DARK_GREEN));
        tooltip.add(helmet ? Component.translatable("tooltip.beachparty.swimwearhelmet").withStyle(ChatFormatting.GREEN) : Component.translatable("tooltip.beachparty.swimwearhelmet").withStyle(ChatFormatting.GRAY));
        tooltip.add(breastplate ? Component.translatable("tooltip.beachparty.swimwearbreastplate").withStyle(ChatFormatting.GREEN) : Component.translatable("tooltip.beachparty.swimwearbreastplate").withStyle(ChatFormatting.GRAY));
        tooltip.add(leggings ? Component.translatable("tooltip.beachparty.swimwearleggings").withStyle(ChatFormatting.GREEN) : Component.translatable("tooltip.beachparty.swimwearleggings").withStyle(ChatFormatting.GRAY));
        tooltip.add(boots ? Component.translatable("tooltip.beachparty.swimwearboots").withStyle(ChatFormatting.GREEN) : Component.translatable("tooltip.beachparty.swimwearboots").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.translatable("tooltip.beachparty.swimwear_seteffect").withStyle(ChatFormatting.GRAY));
        tooltip.add(helmet && breastplate && leggings && boots ? Component.translatable("tooltip.beachparty.swimwear_effect").withStyle(ChatFormatting.DARK_GREEN) : Component.translatable("tooltip.beachparty.swimwear_effect").withStyle(ChatFormatting.GRAY));
    }

    public static void registerColorArmor(Item item, int defaultColor) {
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> tintIndex > 0 ? -1 : getColor(stack, defaultColor), item);
    }

    private static int getColor(ItemStack itemStack, int defaultColor) {
        CompoundTag displayTag = itemStack.getTagElement("display");
        if (displayTag != null && displayTag.contains("color", Tag.TAG_ANY_NUMERIC))
            return displayTag.getInt("color");
        return defaultColor;
    }

}
