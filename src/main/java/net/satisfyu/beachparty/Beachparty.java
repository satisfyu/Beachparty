package net.satisfyu.beachparty;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.registry.*;
import net.satisfyu.beachparty.registry.SoundEventRegistry;
import org.apache.logging.log4j.LogManager;

import java.util.Set;

public class Beachparty implements ModInitializer {
    public static final String MOD_ID = "beachparty";
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ItemGroup CREATIVE_TAB = FabricItemGroupBuilder.build(new BeachpartyIdentifier("creative_tab"), () -> new ItemStack(ObjectRegistry.COCONUT_COCKTAIL));

    @Override
    public void onInitialize() {
        ObjectRegistry.init();
        EntityRegistry.init();
        EntityRegistry.registerEntities();
        BoatRegistry.init();
        RecipeRegistry.init();
        SoundEventRegistry.init();
        ScreenHandlerTypesRegistry.init();


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
        Set<Identifier> chestsId = Set.of(
                LootTables.BURIED_TREASURE_CHEST);

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            Identifier injectId = new Identifier(Beachparty.MOD_ID, "inject/" + id.getPath());
            if (chestsId.contains(id)) {
                tableBuilder.pool(LootPool.builder().with(LootTableEntry.builder(injectId).weight(1).quality(0)).build());
            }
        });
    }
}