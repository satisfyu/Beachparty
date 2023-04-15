package net.satisfyu.beachparty.client;


import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.client.gui.TikiBarGui;
import net.satisfyu.beachparty.registry.ArmorRegistry;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import net.satisfyu.beachparty.registry.ScreenHandlerTypesRegistry;

@Environment(EnvType.CLIENT)
public class BeachPartyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ObjectRegistry.TABLE, ObjectRegistry.CHAIR,
                ObjectRegistry.TIKI_CHAIR, ObjectRegistry.PALM_TRAPDOOR, ObjectRegistry.PALM_DOOR,
                ObjectRegistry.PALM_TORCH, ObjectRegistry.PALM_WALL_TORCH, ObjectRegistry.PALM_TALL_TORCH


        );

        ArmorRegistry.registerModels();


        TerraformBoatClientHelper.registerModelLayers(new BeachpartyIdentifier("palm"));

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ObjectRegistry.PALM_SIGN.getTexture()));

        HandledScreens.register(ScreenHandlerTypesRegistry.TIKI_BAR_GUI_HANDLER, TikiBarGui::new);

    }
}