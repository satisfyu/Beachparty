package satisfyu.beachparty;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import satisfyu.beachparty.entity.villager.Villagers;
import net.satisfyu.beachparty.registry.*;
import org.apache.logging.log4j.LogManager;
import satisfyu.beachparty.registry.*;

import java.util.Set;

public class Beachparty implements ModInitializer {
    public static final String MOD_ID = "beachparty";
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final CreativeModeTab CREATIVE_TAB = FabricItemGroupBuilder.build(new BeachpartyIdentifier("creative_tab"), () -> new ItemStack(ObjectRegistry.COCONUT_COCKTAIL));

    @Override
    public void onInitialize() {
        ObjectRegistry.init();
        EntityRegistry.init();
        EntityRegistry.registerEntities();
        BoatRegistry.init();
        RecipeRegistry.init();
        SoundEventRegistry.init();
        ScreenHandlerTypesRegistry.init();
        Villagers.init();


        registerCompostables();
        registerLootTable();

    }

    protected void registerCompostables() {
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.PALM_LEAVES.asItem(), .65f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.PALM_SAPLING.asItem(), .65f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.COCONUT.asItem(), .65f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.COCONUT_OPEN, .65f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.DRY_BUSH.asItem(), .65f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.DRY_BUSH_TALL.asItem(), .65f);

    }

    protected void registerLootTable() {
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