package satisfyu.beachparty.util;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import satisfyu.beachparty.BeachpartyIdentifier;

import java.util.function.Supplier;

public class PlatformHelper {
    public static <T extends FoliagePlacer> FoliagePlacerType<T> registerFoliagePlacerType(String name, Supplier<FoliagePlacerType<T>> foliagePlacerType) {
        return Registry.register(Registry.FOLIAGE_PLACER_TYPES, new BeachpartyIdentifier(name), foliagePlacerType.get());
    }

    public static <T extends TrunkPlacer> TrunkPlacerType<T> registerTrunkPlacerType(String name, Supplier<TrunkPlacerType<T>> trunkPlacerType) {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, new BeachpartyIdentifier(name), trunkPlacerType.get());
    }
}
