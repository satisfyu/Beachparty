package satisfyu.beachparty.fabriclike;


import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.fabriclike.registry.VillagersFabric;

public class BeachpartyFabricLike {
    public static void init() {
        Beachparty.init();
        Beachparty.commonSetup();
        VillagersFabric.init();
    }
}
