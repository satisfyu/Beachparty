package net.satisfyu.beachparty.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

public abstract class CustomModelArmorItem extends BeachpartyArmorItem {
    public CustomModelArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
    }

    public abstract ResourceLocation getTexture();

    public abstract Float getOffset();


}
