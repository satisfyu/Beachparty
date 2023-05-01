package satisfyu.beachparty.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import org.intellij.lang.annotations.Identifier;

public abstract class CustomModelArmorItem extends net.satisfyu.beachparty.item.BeachpartyArmorItem {
    public CustomModelArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    public abstract Identifier getTexture();

    public abstract Float getOffset();


}