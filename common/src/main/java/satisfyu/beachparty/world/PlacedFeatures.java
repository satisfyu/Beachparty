package satisfyu.beachparty.world;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import satisfyu.beachparty.BeachpartyIdentifier;

public class PlacedFeatures {

    public static final ResourceKey<PlacedFeature> PALM_TREE_KEY = registerKey("palm_tree");
    public static final ResourceKey<PlacedFeature> DIRTY_SAND_KEY = registerKey("dirty_sand");
    public static final ResourceKey<PlacedFeature> DRY_BUSH_KEY = registerKey("dry_bush");
    public static final ResourceKey<PlacedFeature> DRY_TALL_BUSH_KEY = registerKey("dry_tall_bush");
    public static final ResourceKey<PlacedFeature> BEACH_GRASS_KEY = registerKey("beach_grass");
    public static final ResourceKey<PlacedFeature> SAND_SLAB_KEY = registerKey("sand_slab");
    public static final ResourceKey<PlacedFeature> SANDWAVES_KEY = registerKey("sandwaves");
    public static final ResourceKey<PlacedFeature> SEASTARS_KEY = registerKey("seastars");



    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new BeachpartyIdentifier(name));
    }
}
