package satisfyu.beachparty.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.world.placers.PalmFoliagePlacer;
import satisfyu.beachparty.world.placers.CrookedTrunkPlacer;

public class PlacerTypesRegistry {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(Beachparty.MOD_ID, Registry.FOLIAGE_PLACER_TYPE_REGISTRY);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Beachparty.MOD_ID, Registry.TRUNK_PLACER_TYPE_REGISTRY);


    public static final RegistrySupplier<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACER_TYPES.register("palm_foliage_placer" ,() -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
    public static final RegistrySupplier<TrunkPlacerType<CrookedTrunkPlacer>> CROOKED_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("crooked_trunk_placer" ,() -> new TrunkPlacerType<>(CrookedTrunkPlacer.CODEC));

    public static void init() {
        FOLIAGE_PLACER_TYPES.register();
        TRUNK_PLACER_TYPES.register();
    }
}
