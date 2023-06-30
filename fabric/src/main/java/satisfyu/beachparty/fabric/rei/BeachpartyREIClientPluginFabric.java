package satisfyu.beachparty.fabric.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import satisfyu.beachparty.compat.rei.BeachpartyREIClientPlugin;
import satisfyu.beachparty.compat.rei.category.MiniFridgeCategory;
import satisfyu.beachparty.compat.rei.category.TikiBarCategory;
import satisfyu.beachparty.compat.rei.display.MiniFridgeDisplay;
import satisfyu.beachparty.compat.rei.display.TikiBarDisplay;
import satisfyu.beachparty.recipe.MiniFridgeRecipe;
import satisfyu.beachparty.recipe.TikiBarRecipe;
import satisfyu.beachparty.registry.ObjectRegistry;

public class BeachpartyREIClientPluginFabric implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        BeachpartyREIClientPlugin.registerCategories(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        BeachpartyREIClientPlugin.registerDisplays(registry);
    }
}
