package satisfyu.beachparty.world;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import satisfyu.beachparty.BeachpartyIdentifier;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> PALM_TREE_KEY = registerKey("palm_tree");


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new BeachpartyIdentifier(name));
    }
}

