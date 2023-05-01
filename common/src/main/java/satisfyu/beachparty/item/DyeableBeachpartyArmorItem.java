package satisfyu.beachparty.item;


import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;


public class DyeableBeachpartyArmorItem extends BeachpartyArmorItem implements DyeableLeatherItem {
    public DyeableBeachpartyArmorItem(ArmorMaterial material, EquipmentSlot slot, Item.Properties settings) {
        super(material, slot, settings);
    }
}