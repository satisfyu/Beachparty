package satisfyu.beachparty.registry;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.client.model.BeachHatModel;
import satisfyu.beachparty.client.model.RubberRingAxolotlModel;
import satisfyu.beachparty.client.model.RubberRingModel;
import satisfyu.beachparty.client.model.RubberRingPelicanModel;

import java.util.Map;

public class CustomArmorRegistry {


    public static void registerCustomArmorLayers(){
        EntityModelLayerRegistry.register(BeachHatModel.LAYER_LOCATION, BeachHatModel::getTexturedModelData);
        EntityModelLayerRegistry.register(RubberRingPelicanModel.LAYER_LOCATION, RubberRingPelicanModel::getTexturedModelData);
        EntityModelLayerRegistry.register(RubberRingAxolotlModel.LAYER_LOCATION, RubberRingAxolotlModel::getTexturedModelData);
        EntityModelLayerRegistry.register(RubberRingModel.LAYER_LOCATION, RubberRingModel::getTexturedModelData);

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
