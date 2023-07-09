package satisfyu.beachparty.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.entity.CoconutEntity;
import satisfyu.beachparty.entity.pelican.PelicanEntity;

import java.util.function.Supplier;

public class EntityRegistry {


    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Beachparty.MOD_ID, Registries.ENTITY_TYPE);

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
        ENTITY_TYPES.register();
        registerPelican();
    }


    public static  void registerPelican(){
       EntityAttributeRegistry.register(PELICAN, PelicanEntity::createAttributes);

        //SpawnMobAccessor.callRegister(PELICAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        //BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.RIVER), MobCategory.CREATURE, entityType, 10, 4, 4);
    }

}
