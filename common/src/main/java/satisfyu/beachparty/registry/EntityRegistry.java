package satisfyu.beachparty.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
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


import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class EntityRegistry {

    private static final Map<ResourceLocation, BlockEntityType<?>> BLOCK_ENTITY_TYPES = new HashMap<>();
    private static final Map<ResourceLocation, EntityType<?>> ENTITY_TYPES = new HashMap<>();


    public static final EntityType<ChairEntity> CHAIR = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(Beachparty.MOD_ID, "chair"),
            FabricEntityTypeBuilder.<ChairEntity>create(MobCategory.MISC, ChairEntity::new).dimensions(EntityDimensions.fixed(0.001F, 0.001F)).build()
    );

    public static final EntityType<PelicanEntity> PELICAN = Registry.register(Registry.ENTITY_TYPE,
            new ResourceLocation(Beachparty.MOD_ID, "pelican"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, PelicanEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );
    public static final BlockEntityType<CabinetBlockEntity> CABINET_BLOCK_ENTITY = create("cabinet", FabricBlockEntityTypeBuilder.create(CabinetBlockEntity::new, ObjectRegistry.CABINET).build());
    public static final BlockEntityType<TikiBarBlockEntity> TIKI_BAR_BLOCK_ENTITY = create("tiki_bar", FabricBlockEntityTypeBuilder.create(TikiBarBlockEntity::new, ObjectRegistry.TIKI_BAR).build());
    public static final BlockEntityType<MiniFridgeBlockEntity> MINI_FRIDGE_BLOCK_ENTITY = create("mini_fridge", FabricBlockEntityTypeBuilder.create(MiniFridgeBlockEntity::new, ObjectRegistry.MINI_FRIDGE).build());
    public static final EntityType<CoconutEntity> COCONUT = create("coconut", FabricEntityTypeBuilder.<CoconutEntity>create(MobCategory.MISC, CoconutEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    private static <T extends BlockEntityType<?>> T create(final String path, final T type) {
        BLOCK_ENTITY_TYPES.put(new BeachpartyIdentifier(path), type);
        return type;
    }

    private static <T extends EntityType<?>> T create(final String path, final T type) {
        ENTITY_TYPES.put(new BeachpartyIdentifier(path), type);
        return type;
    }

    public static void registerEntities(){
        Beachparty.LOGGER.debug("Registering Mod Entities for " + Beachparty.MOD_ID);


        registerPelican(PELICAN, BiomeSelectors.includeByKey(Biomes.BEACH, Biomes.RIVER));
    }

    public static void init() {
        for (Map.Entry<ResourceLocation, BlockEntityType<?>> entry : BLOCK_ENTITY_TYPES.entrySet()) {
            Registry.register(Registry.BLOCK_ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<ResourceLocation, EntityType<?>> entry : ENTITY_TYPES.entrySet()) {
            Registry.register(Registry.ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
    }

    public static  void registerPelican(EntityType entityType, Predicate<BiomeSelectionContext> biomes){
        FabricDefaultAttributeRegistry.register(entityType, PelicanEntity.createAttributes());
        SpawnMobAccessor.callRegister(entityType, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        BiomeModifications.addSpawn(biomes, MobCategory.CREATURE, entityType, 10, 4, 4);
    }

}
