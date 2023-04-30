package satisfyu.beachparty.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.entity.CabinetBlockEntity;
import satisfyu.beachparty.entity.MiniFridgeBlockEntity;
import satisfyu.beachparty.entity.CoconutEntity;
import satisfyu.beachparty.entity.TikiBarBlockEntity;
import satisfyu.beachparty.entity.chair.ChairEntity;
import satisfyu.beachparty.entity.pelican.PelicanEntity;
import satisfyu.beachparty.mixin.SpawnMobAccessor;
import satisfyu.beachparty.util.boat.impl.TerraformBoatInitializer;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityRegistry {


    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Beachparty.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);


    public static final RegistrySupplier<EntityType<ChairEntity>> CHAIR = create("chair",
            () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC).sized(0.001F, 0.001F).build(new BeachpartyIdentifier("chair").toString())
    );

    public static final RegistrySupplier<EntityType<PelicanEntity>> PELICAN = create(
            "pelican",
            () -> EntityType.Builder.of(PelicanEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new BeachpartyIdentifier("pelican").toString())
    );

    public static final RegistrySupplier<EntityType<CoconutEntity>> COCONUT = create(
            "coconut",
            () -> EntityType.Builder.<CoconutEntity>of(CoconutEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).build(new BeachpartyIdentifier("coconut").toString())
    );



    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(new BeachpartyIdentifier(path), type);
    }

    public static void init(){
        Beachparty.LOGGER.debug("Registering Mod Entities for " + Beachparty.MOD_ID);
        TerraformBoatInitializer.init();
        ENTITY_TYPES.register();
        registerPelican();
    }


    public static  void registerPelican(){
       EntityAttributeRegistry.register(PELICAN, PelicanEntity::createAttributes);

        //SpawnMobAccessor.callRegister(PELICAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        //BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.RIVER), MobCategory.CREATURE, entityType, 10, 4, 4);
    }

}
