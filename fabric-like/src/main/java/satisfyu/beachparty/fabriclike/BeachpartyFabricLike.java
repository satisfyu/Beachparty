package satisfyu.beachparty.fabriclike;


import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.fabriclike.registry.VillagersFabric;
import satisfyu.beachparty.fabriclike.world.BeachpartyBiomeModification;

public class BeachpartyFabricLike {
    public static void init() {
        Beachparty.init();
        Beachparty.commonSetup();
        BeachpartyBiomeModification.init();
        VillagersFabric.init();
    }
}
