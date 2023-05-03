package satisfyu.beachparty.registry;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import satisfyu.beachparty.util.PlatformHelper;
import satisfyu.beachparty.util.worldgen.PalmFoliagePlacer;
import satisfyu.beachparty.util.worldgen.CrookedTrunkPlacer;

public class PlacerTypesRegistry {
    public static final TrunkPlacerType<CrookedTrunkPlacer> CROOKED_TRUNK_PLACER = PlatformHelper.registerTrunkPlacerType("crooked_trunk_placer", () -> new TrunkPlacerType<>(CrookedTrunkPlacer.CODEC));
    public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = PlatformHelper.registerFoliagePlacerType("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));

    public static void init() {}
}
