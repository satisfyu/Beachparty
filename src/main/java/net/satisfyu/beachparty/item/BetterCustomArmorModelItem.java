package net.satisfyu.beachparty.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.registry.MaterialsRegistry;

public class BetterCustomArmorModelItem extends CustomModelArmorItem {

    public Identifier texture;

    public float offset;
    public BetterCustomArmorModelItem(EquipmentSlot slot, Settings settings, Identifier texture, float offset) {
        super(MaterialsRegistry.BEACH_ARMOR, slot, settings);
        this.texture = texture;
        this.offset = offset;
    }

    @Override
    public Identifier getTexture() {
        return texture;
    }

    @Override
    public Float getOffset() {
        return offset;
    }

}
