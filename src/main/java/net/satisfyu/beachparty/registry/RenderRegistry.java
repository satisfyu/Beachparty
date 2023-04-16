package net.satisfyu.beachparty.registry;

import net.minecraft.block.Block;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.satisfyu.beachparty.Beachparty;
import net.satisfyu.beachparty.client.model.feature.BeachHatModel;
import net.satisfyu.beachparty.util.BeachpartyApi;

import java.util.Map;
import java.util.Set;

public class RenderRegistry implements BeachpartyApi {
    @Override
    public void registerBlocks(Set<Block> blocks) {

    }

    public static void init(){
        Beachparty.LOGGER.debug("Registering Storage Block Renderers!");
    }


    @Override
    public <T extends LivingEntity> void registerArmor(Map<Item, EntityModel<T>> models, EntityModelLoader modelLoader) {
        models.put(ObjectRegistry.BEACH_HAT, new BeachHatModel<>(modelLoader.getModelPart(BeachHatModel.LAYER_LOCATION)));
    }
}
