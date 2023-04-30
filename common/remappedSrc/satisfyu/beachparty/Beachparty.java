package satisfyu.beachparty;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.apache.logging.log4j.LogManager;
import satisfyu.beachparty.registry.*;

import java.util.Set;

public class Beachparty {
    public static final String MOD_ID = "beachparty";
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final CreativeModeTab CREATIVE_TAB = CreativeTabRegistry.create(new BeachpartyIdentifier("creative_tab"), () -> new ItemStack(ObjectRegistry.COCONUT_COCKTAIL));

    public static void init() {
        ObjectRegistry.init();
        EntityRegistry.init();
        EntityRegistry.registerEntities();
        BoatRegistry.init();
        RecipeRegistry.init();
        SoundEventRegistry.init();
        ScreenHandlerTypesRegistry.init();


        //registerLootTable();

    }

    protected static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PALM_LEAVES.asItem(), .65f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PALM_SAPLING.asItem(), .65f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.COCONUT.asItem(), .65f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.COCONUT_OPEN, .65f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRY_BUSH.asItem(), .65f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DRY_BUSH_TALL.asItem(), .65f);

    }


    public static void commonSetup(){
        registerCompostables();
    }

    protected static void registerLootTable() {
        Set<ResourceLocation> chestsId = Set.of(
                BuiltInLootTables.BURIED_TREASURE);
        /*
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            ResourceLocation injectId = new ResourceLocation(Beachparty.MOD_ID, "inject/" + id.getPath());
            if (chestsId.contains(id)) {
                tableBuilder.pool(LootPool.lootPool().add(LootTableReference.lootTableReference(injectId).setWeight(1).setQuality(0)).build());
            }
        });
         */
    }
}