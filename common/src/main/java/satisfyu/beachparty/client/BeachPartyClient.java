package satisfyu.beachparty.client;


import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.client.gui.MiniFridgeGui;
import satisfyu.beachparty.client.gui.TikiBarGui;
import satisfyu.beachparty.entity.chair.ChairRenderer;
import satisfyu.beachparty.entity.pelican.PelicanModel;
import satisfyu.beachparty.entity.pelican.PelicanRenderer;
import satisfyu.beachparty.networking.BeachpartyMessages;
import satisfyu.beachparty.registry.EntityRegistry;
import satisfyu.beachparty.registry.ObjectRegistry;
import satisfyu.beachparty.registry.RenderRegistry;
import satisfyu.beachparty.registry.ScreenHandlerTypesRegistry;

import static satisfyu.beachparty.Beachparty.MOD_ID;

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
                ObjectRegistry.BEACH_TOWEL, ObjectRegistry.DECK_CHAIR
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
    }
}