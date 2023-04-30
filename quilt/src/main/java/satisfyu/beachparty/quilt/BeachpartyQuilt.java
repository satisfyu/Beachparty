package satisfyu.beachparty.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import satisfyu.beachparty.fabriclike.BeachpartyFabricLike;

public class BeachpartyQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        BeachpartyFabricLike.init();
    }
}
