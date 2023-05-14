package satisfyu.beachparty.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.registry.MaterialsRegistry;

public class BeachHatItem extends CustomModelArmorItem {


    public BeachHatItem(Item.Properties settings) {
        super(MaterialsRegistry.BEACH_HAT, Type.HELMET, settings);
    }

    @Override
    public ResourceLocation getTexture() {
        return new BeachpartyIdentifier("textures/entity/beach_hat.png");
    }

    @Override
    public Float getOffset() {
        return -1.8f;
    }

}
