package net.satisfyu.beachparty.registry;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.satisfyu.beachparty.client.model.feature.BeachHatModel;
import net.satisfyu.beachparty.client.model.feature.RubberRingAxolotlModel;
import net.satisfyu.beachparty.client.model.feature.RubberRingModel;
import net.satisfyu.beachparty.client.model.feature.RubberRingPelicanModel;

import java.util.Map;

public class RenderRegistry{


    public static void registerModels(){
        EntityModelLayerRegistry.registerModelLayer(BeachHatModel.LAYER_LOCATION, BeachHatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RubberRingPelicanModel.LAYER_LOCATION, RubberRingPelicanModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RubberRingAxolotlModel.LAYER_LOCATION, RubberRingAxolotlModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RubberRingModel.LAYER_LOCATION, RubberRingModel::getTexturedModelData);

    }


    public static  <T extends LivingEntity> void registerArmor(Map<Item, EntityModel<T>> models, EntityModelLoader modelLoader) {
        models.put(ObjectRegistry.BEACH_HAT, new BeachHatModel<>(modelLoader.getModelPart(BeachHatModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_PELICAN, new RubberRingPelicanModel<>(modelLoader.getModelPart(RubberRingPelicanModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_AXOLOTL, new RubberRingAxolotlModel<>(modelLoader.getModelPart(RubberRingAxolotlModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_BLUE, new RubberRingModel<>(modelLoader.getModelPart(RubberRingModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_PINK, new RubberRingModel<>(modelLoader.getModelPart(RubberRingModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_STRIPPED, new RubberRingModel<>(modelLoader.getModelPart(RubberRingModel.LAYER_LOCATION)));
    }
}
