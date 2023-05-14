package satisfyu.beachparty.client;


import de.cristelknight.doapi.DoApiExpectPlatform;
import de.cristelknight.doapi.terraform.TerraformSignHelper;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import satisfyu.beachparty.client.gui.MiniFridgeGui;
import satisfyu.beachparty.client.gui.TikiBarGui;
import satisfyu.beachparty.entity.chair.ChairRenderer;
import satisfyu.beachparty.entity.pelican.PelicanModel;
import satisfyu.beachparty.entity.pelican.PelicanRenderer;
import satisfyu.beachparty.networking.BeachpartyMessages;
import satisfyu.beachparty.registry.*;

@Environment(EnvType.CLIENT)
public class BeachPartyClient {

    public static boolean rememberedRecipeBookOpen = false;
    public static boolean rememberedCraftableToggle = true;


    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(), ObjectRegistry.TABLE.get(), ObjectRegistry.CHAIR.get(),
                ObjectRegistry.TIKI_CHAIR.get(), ObjectRegistry.PALM_TRAPDOOR.get(), ObjectRegistry.PALM_DOOR.get(),
                ObjectRegistry.PALM_TORCH.get(), ObjectRegistry.PALM_WALL_TORCH.get(), ObjectRegistry.PALM_TALL_TORCH.get(),
                ObjectRegistry.DRY_BUSH.get(), ObjectRegistry.DRY_BUSH_TALL.get(), ObjectRegistry.MELON_COCKTAIL.get(), ObjectRegistry.COCONUT_COCKTAIL.get(),
                ObjectRegistry.HONEY_COCKTAIL.get(), ObjectRegistry.SWEETBERRIES_COCKTAIL.get(), ObjectRegistry.PUMPKIN_COCKTAIL.get(),
                ObjectRegistry.COCOA_COCKTAIL.get(), ObjectRegistry.SANDCASTLE.get(), ObjectRegistry.BEACH_GRASS.get(), ObjectRegistry.MESSAGE_IN_A_BOTTLE.get(),
                ObjectRegistry.BEACH_TOWEL.get(), ObjectRegistry.DECK_CHAIR.get(), ObjectRegistry.PALM_SAPLING.get(), ObjectRegistry.HAMMOCK.get()
        );

        MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.TIKI_BAR_GUI_HANDLER.get(), TikiBarGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.MINI_FRIDGE_GUI_HANDLER.get(), MiniFridgeGui::new);


        BeachpartyMessages.registerC2SPackets();
        BeachpartyMessages.registerS2CPackets();

        ClientUtil.registerColorArmor(ObjectRegistry.TRUNKS.get(), 16715535);
        ClientUtil.registerColorArmor(ObjectRegistry.BIKINI.get(), 987135);
        ClientUtil.registerColorArmor(ObjectRegistry.CROCS.get(), 1048335);
        ClientUtil.registerColorArmor(ObjectRegistry.POOL_NOODLE.get(), 1017855);
    }

    public static void preInitClient(){
        TerraformSignHelper.regsterSignSprite(TerraformRegistry.PALM_SIGN_TEXTURE_ID);
        registerEntityEntityRenderers();
        registerEntityModelLayers();
    }

    public static void registerEntityEntityRenderers(){
        EntityRendererRegistry.register(EntityRegistry.PELICAN, PelicanRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.CHAIR, ChairRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.COCONUT, ThrownItemRenderer::new);
    }

    public static void registerEntityModelLayers(){
        EntityModelLayerRegistry.register(PelicanModel.PELICAN_MODEL_LAYER, PelicanModel::getTexturedModelData);

        //"API"
        CustomArmorRegistry.registerCustomArmorLayers();
    }

}