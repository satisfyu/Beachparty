package net.satisfyu.beachparty.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.satisfyu.beachparty.registry.MaterialsRegistry;

public class BetterCustomArmorModelItem extends CustomModelArmorItem {

    public ResourceLocation texture;

    public float offset;
    public BetterCustomArmorModelItem(EquipmentSlot slot, Properties settings, ResourceLocation texture, float offset) {
        super(MaterialsRegistry.RING, slot, settings);
        this.texture = texture;
        this.offset = offset;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public Float getOffset() {
        return offset;
    }

}
