package net.satisfyu.beachparty.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableItem;

public class DyeableBeachpartyArmorItem extends BeachpartyArmorItem implements DyeableItem {
    public DyeableBeachpartyArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }
}
