package net.satisfyu.beachparty;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.satisfyu.beachparty.registry.*;
import net.satisfyu.beachparty.sound.BeachpartySounds;
import org.apache.logging.log4j.LogManager;

public class Beachparty implements ModInitializer {
    public static final String MOD_ID = "beachparty";
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ItemGroup CREATIVE_TAB = FabricItemGroupBuilder.build(new BeachpartyIdentifier("creative_tab"), () -> new ItemStack(ObjectRegistry.COCONUT_COCKTAIL));

    @Override
    public void onInitialize() {
        ObjectRegistry.init();
        EntityRegistry.init();
        BoatRegistry.init();
        RecipeRegistry.init();
        BeachpartySounds.init();
        ScreenHandlerTypesRegistry.init();
    }
}