package satisfyu.beachparty.client;


import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.client.gui.MiniFridgeGui;
import satisfyu.beachparty.client.gui.TikiBarGui;
import satisfyu.beachparty.entity.chair.ChairRenderer;
import satisfyu.beachparty.entity.pelican.PelicanModel;
import satisfyu.beachparty.entity.pelican.PelicanRenderer;
import satisfyu.beachparty.networking.BeachpartyMessages;
import satisfyu.beachparty.registry.*;
import satisfyu.beachparty.util.boat.api.client.TerraformBoatClientHelper;
import satisfyu.beachparty.util.boat.impl.client.TerraformBoatClientInitializer;
import satisfyu.beachparty.util.sign.SpriteIdentifierRegistry;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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



        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ObjectRegistry.PALM_SIGN.get().getTexture()));

        MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.TIKI_BAR_GUI_HANDLER.get(), TikiBarGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.MINI_FRIDGE_GUI_HANDLER.get(), MiniFridgeGui::new);


        BeachpartyMessages.registerC2SPackets();
        BeachpartyMessages.registerS2CPackets();

        registerColorArmor(ObjectRegistry.TRUNKS.get(), 16715535);
        registerColorArmor(ObjectRegistry.BIKINI.get(), 987135);
        registerColorArmor(ObjectRegistry.CROCS.get(), 1048335);
        registerColorArmor(ObjectRegistry.POOL_NOODLE.get(), 1017855);
    }

    private static void registerColorArmor(Item item, int defaultColor) {
        ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> tintIndex > 0 ? -1 : getColor(stack, defaultColor), item);
    }

    public static int getColor(ItemStack itemStack, int defaultColor) {
        CompoundTag displayTag = itemStack.getTagElement("display");
        if (displayTag != null && displayTag.contains("color", Tag.TAG_ANY_NUMERIC))
            return displayTag.getInt("color");
        return defaultColor;
    }

    public static void getEntityModelLayers(Map<ModelLayerLocation, Supplier<LayerDefinition>> map){
        map.put(PelicanModel.PELICAN_MODEL_LAYER, PelicanModel::getTexturedModelData);

        //"API"
        CustomArmorRegistry.registerCustomArmorLayers(map);
        TerraformBoatClientHelper.registerModelLayers(map, new BeachpartyIdentifier("palm"));
    }

    public static void getEntityEntityRenderers(Map<Supplier<EntityType<?>>, EntityRendererProvider<?>> map){
        registerEntityRenderer(map, EntityRegistry.PELICAN, PelicanRenderer::new);
        registerEntityRenderer(map, EntityRegistry.CHAIR, ChairRenderer::new);
        registerEntityRenderer(map, EntityRegistry.COCONUT, ThrownItemRenderer::new);

        //"API"
        TerraformBoatClientInitializer.init(map);
    }

    public static <T extends Entity> void registerEntityRenderer(Map<Supplier<EntityType<?>>, EntityRendererProvider<?>> map, Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> factory) {
        map.put((Supplier<EntityType<?>>) (Supplier<? extends EntityType<?>>)type, factory);
    }

}