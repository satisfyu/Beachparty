package net.satisfyu.beachparty.client;


import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.client.gui.MiniFridgeGui;
import net.satisfyu.beachparty.client.gui.TikiBarGui;
import net.satisfyu.beachparty.entity.chair.ChairRenderer;
import net.satisfyu.beachparty.networking.BeachpartyMessages;
import net.satisfyu.beachparty.registry.EntityRegistry;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import net.satisfyu.beachparty.registry.RenderRegistry;
import net.satisfyu.beachparty.registry.ScreenHandlerTypesRegistry;

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
                ObjectRegistry.COCOA_COCKTAIL, ObjectRegistry.SANDCASTLE
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
    }
}