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
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
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

    public static final EntityModelLayer PELICAN_MODEL_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "pelican"), "main");

    private static void registerPelican(){
        EntityRendererRegistry.register(EntityRegistry.PELICAN, PelicanRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(PELICAN_MODEL_LAYER, PelicanModel::getTexturedModelData);
    }


        @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ObjectRegistry.TABLE, ObjectRegistry.CHAIR,
                ObjectRegistry.TIKI_CHAIR, ObjectRegistry.PALM_TRAPDOOR, ObjectRegistry.PALM_DOOR,
                ObjectRegistry.PALM_TORCH, ObjectRegistry.PALM_WALL_TORCH, ObjectRegistry.PALM_TALL_TORCH,
                ObjectRegistry.DRY_BUSH, ObjectRegistry.DRY_BUSH_TALL, ObjectRegistry.MELON_COCKTAIL, ObjectRegistry.COCONUT_COCKTAIL,
                ObjectRegistry.HONEY_COCKTAIL, ObjectRegistry.SWEETBERRIES_COCKTAIL, ObjectRegistry.PUMPKIN_COCKTAIL,
                ObjectRegistry.COCOA_COCKTAIL, ObjectRegistry.SANDCASTLE, ObjectRegistry.BEACH_GRASS, ObjectRegistry.MESSAGE_IN_A_BOTTLE,
                ObjectRegistry.BEACH_TOWEL, ObjectRegistry.DECK_CHAIR, ObjectRegistry.HAMMOCK
        );

        RenderRegistry.registerModels();

        TerraformBoatClientHelper.registerModelLayers(new BeachpartyIdentifier("palm"));

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ObjectRegistry.PALM_SIGN.getTexture()));

        HandledScreens.register(ScreenHandlerTypesRegistry.TIKI_BAR_GUI_HANDLER, TikiBarGui::new);
        HandledScreens.register(ScreenHandlerTypesRegistry.MINI_FRIDGE_GUI_HANDLER, MiniFridgeGui::new);
        EntityRendererRegistry.register(EntityRegistry.CHAIR, ChairRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.COCONUT, FlyingItemEntityRenderer::new);
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
        NbtCompound displayTag = itemStack.getSubNbt("display");
        if (displayTag != null && displayTag.contains("color", NbtElement.NUMBER_TYPE))
            return displayTag.getInt("color");
        return defaultColor;
    }

    public static void appendTooltip(List<Text> tooltip) {
        tooltip.add(Text.translatable(  "tooltip.beachparty.swimwearline1").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable(  "tooltip.beachparty.swimwearline2").formatted(Formatting.BLUE));

        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        boolean helmet = BeachpartyArmorItem.hasSwimearHelmet(player);
        boolean breastplate = BeachpartyArmorItem.hasSwimwearBreastplate(player);
        boolean leggings = BeachpartyArmorItem.hasSwimearLeggings(player);
        boolean boots = BeachpartyArmorItem.hasSwimearBoots(player);

        tooltip.add(Text.of(""));
        tooltip.add(Text.translatable("tooltip.beachparty.swimwear_set").formatted(Formatting.DARK_GREEN));
        tooltip.add(helmet ? Text.translatable("tooltip.beachparty.swimwearhelmet").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearhelmet").formatted(Formatting.GRAY));
        tooltip.add(breastplate ? Text.translatable("tooltip.beachparty.swimwearbreastplate").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearbreastplate").formatted(Formatting.GRAY));
        tooltip.add(leggings ? Text.translatable("tooltip.beachparty.swimwearleggings").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearleggings").formatted(Formatting.GRAY));
        tooltip.add(boots ? Text.translatable("tooltip.beachparty.swimwearboots").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearboots").formatted(Formatting.GRAY));
        tooltip.add(Text.of(""));
        tooltip.add(Text.translatable("tooltip.beachparty.swimwear_seteffect").formatted(Formatting.GRAY));
        tooltip.add(helmet && breastplate && leggings && boots ? Text.translatable("tooltip.beachparty.swimwear_effect").formatted(Formatting.DARK_GREEN) : Text.translatable("tooltip.beachparty.swimwear_effect").formatted(Formatting.GRAY));
    }
}