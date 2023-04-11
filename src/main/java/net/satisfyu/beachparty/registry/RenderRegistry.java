package net.satisfyu.beachparty.registry;

import net.minecraft.block.Block;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.client.model.feature.BeachHatModel;
import net.satisfyu.beachparty.util.BeachpartyApi;

import java.util.Map;
import java.util.Set;

public class RenderRegistry implements BeachpartyApi {
    @Override
    public void registerBlocks(Set<Block> blocks) {

    }


    @Override
    public <T extends LivingEntity> void registerArmor(Map<Item, EntityModel<T>> models, EntityModelLoader modelLoader) {
        models.put(ObjectRegistry.BEACH_HAT, new BeachHatModel<>(modelLoader.getModelPart(BeachHatModel.LAYER_LOCATION)));
    }
}
