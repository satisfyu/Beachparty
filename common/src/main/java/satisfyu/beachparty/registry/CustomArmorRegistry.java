package satisfyu.beachparty.registry;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.client.model.feature.BeachHatModel;
import satisfyu.beachparty.client.model.feature.RubberRingAxolotlModel;
import satisfyu.beachparty.client.model.feature.RubberRingModel;
import satisfyu.beachparty.client.model.feature.RubberRingPelicanModel;

import java.util.Map;
import java.util.function.Supplier;

public class CustomArmorRegistry {


    public static void registerCustomArmorLayers(Map<ModelLayerLocation, Supplier<LayerDefinition>> map){
        map.put(BeachHatModel.LAYER_LOCATION, BeachHatModel::getTexturedModelData);
        map.put(RubberRingPelicanModel.LAYER_LOCATION, RubberRingPelicanModel::getTexturedModelData);
        map.put(RubberRingAxolotlModel.LAYER_LOCATION, RubberRingAxolotlModel::getTexturedModelData);
        map.put(RubberRingModel.LAYER_LOCATION, RubberRingModel::getTexturedModelData);

    }


    public static  <T extends LivingEntity> void registerArmor(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {
        models.put(ObjectRegistry.BEACH_HAT.get(), new BeachHatModel<>(modelLoader.bakeLayer(BeachHatModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_PELICAN.get(), new RubberRingPelicanModel<>(modelLoader.bakeLayer(RubberRingPelicanModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_AXOLOTL.get(), new RubberRingAxolotlModel<>(modelLoader.bakeLayer(RubberRingAxolotlModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_BLUE.get(), new RubberRingModel<>(modelLoader.bakeLayer(RubberRingModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_PINK.get(), new RubberRingModel<>(modelLoader.bakeLayer(RubberRingModel.LAYER_LOCATION)));
        models.put(ObjectRegistry.RUBBER_RING_STRIPPED.get(), new RubberRingModel<>(modelLoader.bakeLayer(RubberRingModel.LAYER_LOCATION)));
    }
}
