package satisfyu.beachparty.util;

import net.minecraft.resources.ResourceLocation;
import satisfyu.beachparty.Beachparty;

public class BeachpartyPalmTreeGrower extends BeachpartyTreeGrower {
    @Override
    protected ResourceLocation getConfiguredFeatureLocation() {
        return new ResourceLocation(Beachparty.MOD_ID, "palm_tree");
    }
}