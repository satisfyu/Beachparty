package satisfyu.beachparty.item.armor;

import de.cristelknight.doapi.common.item.CustomHatItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;

public class BetterCustomArmorModelItem extends CustomHatItem {

    private ResourceLocation textureLocation;

    private float offset;

    public BetterCustomArmorModelItem(ArmorMaterial material, Type slot, Properties settings, ResourceLocation textureLocation, float offset){
        super(material, slot, settings);
        this.textureLocation = textureLocation;
        this.offset = offset;
    }
    @Override
    public ResourceLocation getTexture() {
        return textureLocation;
    }

    @Override
    public Float getOffset() {
        return offset;
    }
}
