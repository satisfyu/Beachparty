package satisfyu.beachparty.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

public abstract class CustomModelArmorItem extends BeachpartyArmorItem {
    public CustomModelArmorItem(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    public abstract ResourceLocation getTexture();

    public abstract Float getOffset();


}