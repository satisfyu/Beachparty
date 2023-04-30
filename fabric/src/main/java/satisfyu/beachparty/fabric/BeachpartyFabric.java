package satisfyu.beachparty.fabric;

import net.fabricmc.api.ModInitializer;
import satisfyu.beachparty.fabriclike.BeachpartyFabricLike;

public class BeachpartyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BeachpartyFabricLike.init();
    }
}
