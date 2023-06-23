package satisfyu.beachparty.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.fabric.entity.EntitySpawnFabric;
import satisfyu.beachparty.fabric.registry.VillagersFabric;
import satisfyu.beachparty.fabric.world.BeachpartyBiomeModification;

import java.util.Set;

public class BeachpartyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Beachparty.init();
        Beachparty.commonSetup();

        //Fabric only
        BeachpartyBiomeModification.init();
        VillagersFabric.init();
        registerLootTable();
        EntitySpawnFabric.addEntitySpawn();
    }

    protected static void registerLootTable() {
        Set<ResourceLocation> chestsId = Set.of(
                BuiltInLootTables.BURIED_TREASURE);

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            ResourceLocation injectId = new ResourceLocation(Beachparty.MOD_ID, "inject/" + id.getPath());
            if (chestsId.contains(id)) {
                tableBuilder.pool(LootPool.lootPool().add(LootTableReference.lootTableReference(injectId).setWeight(1).setQuality(0)).build());
            }
        });

    }

}
