package net.satisfyu.beachparty.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.registry.MaterialsRegistry;

public class BeachHatItem extends CustomModelArmorItem {


    public BeachHatItem(Item.Settings settings) {
        super(MaterialsRegistry.BEACH_ARMOR, EquipmentSlot.HEAD, settings);
    }

    @Override
    public Identifier getTexture() {
        return new BeachpartyIdentifier("textures/entity/beach_hat.png");
    }

    @Override
    public Float getOffset() {
        return -1.8f;
    }

}
