package net.satisfyu.beachparty.registry;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.satisfyu.beachparty.client.model.feature.BeachHatModel;
import net.satisfyu.beachparty.item.CustomModelArmorItem;

public class ArmorRegistry {

    /**
     * Das Item muss {@link CustomModelArmorItem} erweitern
    **/


    public static void registerModels(){
        EntityModelLayerRegistry.registerModelLayer(BeachHatModel.LAYER_LOCATION, BeachHatModel::getTexturedModelData);
    }

}