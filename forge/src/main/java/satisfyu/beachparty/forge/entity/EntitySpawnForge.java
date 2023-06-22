package satisfyu.beachparty.forge.entity;


import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import satisfyu.beachparty.registry.EntityRegistry;

public class EntitySpawnForge {
    public static void registerEntitySpawn() {
        SpawnPlacements.register(EntityRegistry.PELICAN.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules);

    }
}
