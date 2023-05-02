package net.satisfyu.beachparty.client;


import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.client.gui.MiniFridgeGui;
import net.satisfyu.beachparty.client.gui.TikiBarGui;
import net.satisfyu.beachparty.entity.chair.ChairRenderer;
import net.satisfyu.beachparty.entity.pelican.PelicanModel;
import net.satisfyu.beachparty.entity.pelican.PelicanRenderer;
import net.satisfyu.beachparty.item.BeachpartyArmorItem;
import net.satisfyu.beachparty.networking.BeachpartyMessages;
import net.satisfyu.beachparty.registry.EntityRegistry;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import net.satisfyu.beachparty.registry.RenderRegistry;
import net.satisfyu.beachparty.registry.ScreenHandlerTypesRegistry;

import java.util.List;

import static net.satisfyu.beachparty.Beachparty.MOD_ID;

@Environment(EnvType.CLIENT)
public class BeachPartyClient implements ClientModInitializer {

    public static boolean rememberedRecipeBookOpen = false;
    public static boolean rememberedCraftableToggle = true;

    public static final ModelLayerLocation PELICAN_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(MOD_ID, "pelican"), "main");

    private static void registerPelican(){
        EntityRendererRegistry.register(EntityRegistry.PELICAN, PelicanRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(PELICAN_MODEL_LAYER, PelicanModel::getTexturedModelData);
    }


        @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ObjectRegistry.TABLE, ObjectRegistry.CHAIR,
                ObjectRegistry.TIKI_CHAIR, ObjectRegistry.PALM_TRAPDOOR, ObjectRegistry.PALM_DOOR,
                ObjectRegistry.PALM_TORCH, ObjectRegistry.PALM_WALL_TORCH, ObjectRegistry.PALM_TALL_TORCH,
                ObjectRegistry.DRY_BUSH, ObjectRegistry.DRY_BUSH_TALL, ObjectRegistry.MELON_COCKTAIL, ObjectRegistry.COCONUT_COCKTAIL,
                ObjectRegistry.HONEY_COCKTAIL, ObjectRegistry.SWEETBERRIES_COCKTAIL, ObjectRegistry.PUMPKIN_COCKTAIL,
                ObjectRegistry.COCOA_COCKTAIL, ObjectRegistry.SANDCASTLE, ObjectRegistry.BEACH_GRASS, ObjectRegistry.MESSAGE_IN_A_BOTTLE,
                ObjectRegistry.BEACH_TOWEL, ObjectRegistry.DECK_CHAIR, ObjectRegistry.HAMMOCK
        );

        RenderRegistry.registerModels();

        TerraformBoatClientHelper.registerModelLayers(new BeachpartyIdentifier("palm"));

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ObjectRegistry.PALM_SIGN.getTexture()));

        MenuScreens.register(ScreenHandlerTypesRegistry.TIKI_BAR_GUI_HANDLER, TikiBarGui::new);
        MenuScreens.register(ScreenHandlerTypesRegistry.MINI_FRIDGE_GUI_HANDLER, MiniFridgeGui::new);
        EntityRendererRegistry.register(EntityRegistry.CHAIR, ChairRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.COCONUT, ThrownItemRenderer::new);
        registerPelican();

        BeachpartyMessages.registerC2SPackets();
        BeachpartyMessages.registerS2CPackets();

        registerColorArmor(ObjectRegistry.TRUNKS, 16715535);
        registerColorArmor(ObjectRegistry.BIKINI, 987135);
        registerColorArmor(ObjectRegistry.CROCS, 1048335);
        registerColorArmor(ObjectRegistry.POOL_NOODLE, 1017855);
    }

    private static void registerColorArmor(Item item, int defaultColor) {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : getColor(stack, defaultColor), item);
    }

    public static int getColor(ItemStack itemStack, int defaultColor) {
        CompoundTag displayTag = itemStack.getTagElement("display");
        if (displayTag != null && displayTag.contains("color", Tag.TAG_ANY_NUMERIC))
            return displayTag.getInt("color");
        return defaultColor;
    }

    public static void appendTooltip(List<Component> tooltip) {
        tooltip.add(Component.translatable(  "tooltip.beachparty.swimwearline1").withStyle(ChatFormatting.DARK_PURPLE));
        tooltip.add(Component.translatable(  "tooltip.beachparty.swimwearline2").withStyle(ChatFormatting.BLUE));

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        boolean helmet = BeachpartyArmorItem.hasSwimearHelmet(player);
        boolean breastplate = BeachpartyArmorItem.hasSwimwearBreastplate(player);
        boolean leggings = BeachpartyArmorItem.hasSwimearLeggings(player);
        boolean boots = BeachpartyArmorItem.hasSwimearBoots(player);

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
}