package net.satisfyu.beachparty.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.util.Identifier;

public abstract class CustomModelArmorItem extends BeachpartyArmorItem {
    public CustomModelArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    public abstract Identifier getTexture();

    public abstract Float getOffset();


}
