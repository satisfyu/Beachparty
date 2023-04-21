package net.satisfyu.beachparty.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.registry.MaterialsRegistry;

public class RubberRingPelicanItem extends CustomModelArmorItem {


    public RubberRingPelicanItem(Settings settings) {
        super(MaterialsRegistry.BEACH_ARMOR, EquipmentSlot.CHEST, settings);
    }

    @Override
    public Identifier getTexture() {
        return new BeachpartyIdentifier("textures/entity/rubber_ring_pelican.png");
    }

    @Override
    public Float getOffset() {
        return -0.7f;
    }

}
