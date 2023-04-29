package satisfyu.beachparty.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.registry.MaterialsRegistry;

public class RubberRingPelicanItem extends CustomModelArmorItem {


    public RubberRingPelicanItem(Properties settings) {
        super(MaterialsRegistry.RING, EquipmentSlot.CHEST, settings);
    }

    @Override
    public ResourceLocation getTexture() {
        return new BeachpartyIdentifier("textures/entity/rubber_ring_pelican.png");
    }

    @Override
    public Float getOffset() {
        return -0.7f;
    }

}
