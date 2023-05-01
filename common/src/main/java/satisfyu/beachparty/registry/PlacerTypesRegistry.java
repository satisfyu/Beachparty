package satisfyu.beachparty.registry;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import satisfyu.beachparty.util.PlatformHelper;
import satisfyu.beachparty.util.worldgen.PalmFoliagePlacer;
import satisfyu.beachparty.util.worldgen.CrookedTrunkPlacer;

import java.util.function.Supplier;

public class PlacerTypesRegistry {
    public static void init() {}

    public static final Supplier<TrunkPlacerType<CrookedTrunkPlacer>> CROOKED_TRUNK_PLACER = PlatformHelper.registerTrunkPlacerType("crooked_trunk_placer", () -> new TrunkPlacerType<>(CrookedTrunkPlacer.CODEC));
    public static final Supplier<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = PlatformHelper.registerFoliagePlacerType("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
}
