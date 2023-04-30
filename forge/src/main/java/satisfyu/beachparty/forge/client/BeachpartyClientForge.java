package satisfyu.beachparty.forge.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.client.BeachPartyClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Beachparty.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BeachpartyClientForge {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BeachPartyClient.initClient();
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        Map<Supplier<EntityType<?>>, EntityRendererProvider<?>> entityRenderers = new ConcurrentHashMap<>();
        BeachPartyClient.getEntityEntityRenderers(entityRenderers);

        for (Map.Entry<Supplier<EntityType<?>>, EntityRendererProvider<?>> entry : entityRenderers.entrySet()) {
            event.registerEntityRenderer(entry.getKey().get(), (EntityRendererProvider<Entity>) entry.getValue());
        }

    }

    @SubscribeEvent
    public static void onEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        Map<ModelLayerLocation, Supplier<LayerDefinition>> modelLayers = new HashMap<>();
        BeachPartyClient.getEntityModelLayers(modelLayers);
        modelLayers.forEach(event::registerLayerDefinition);
    }
}
