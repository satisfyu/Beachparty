package net.satisfyu.beachparty.util;

import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.satisfyu.beachparty.BeachpartyIdentifier;

public class BeachpartyTags {
    public static final TagKey<Biome> WARM_BIOME = TagKey.of(Registry.BIOME_KEY, new BeachpartyIdentifier("warm_biome"));
}
