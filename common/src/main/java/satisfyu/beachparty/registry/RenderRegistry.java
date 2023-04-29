package satisfyu.beachparty.registry;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.client.model.feature.BeachHatModel;
import satisfyu.beachparty.client.model.feature.RubberRingAxolotlModel;
import satisfyu.beachparty.client.model.feature.RubberRingModel;
import satisfyu.beachparty.client.model.feature.RubberRingPelicanModel;

import java.util.Map;

public class RenderRegistry{


    public static void registerModels(){
        EntityModelLayerRegistry.registerModelLayer(BeachHatModel.LAYER_LOCATION, BeachHatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RubberRingPelicanModel.LAYER_LOCATION, RubberRingPelicanModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RubberRingAxolotlModel.LAYER_LOCATION, RubberRingAxolotlModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RubberRingModel.LAYER_LOCATION, RubberRingModel::getTexturedModelData);

    }


    public static  <T extends LivingEntity> void registerArmor(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {
        models.put(ObjectRegistry.BEACH_HAT, new BeachHatModel<>(modelLoader.bakeLayer(BeachHatModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_PELICAN, new RubberRingPelicanModel<>(modelLoader.bakeLayer(RubberRingPelicanModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_AXOLOTL, new RubberRingAxolotlModel<>(modelLoader.bakeLayer(RubberRingAxolotlModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_BLUE, new RubberRingModel<>(modelLoader.bakeLayer(RubberRingModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_PINK, new RubberRingModel<>(modelLoader.bakeLayer(RubberRingModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_STRIPPED, new RubberRingModel<>(modelLoader.bakeLayer(RubberRingModel.LAYER_LOCATION)));
    }
}
