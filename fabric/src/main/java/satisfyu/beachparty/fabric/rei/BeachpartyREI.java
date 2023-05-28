package satisfyu.beachparty.fabric.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import satisfyu.beachparty.fabric.rei.category.MiniFridgeCategory;
import satisfyu.beachparty.fabric.rei.category.TikiBarCategory;
import satisfyu.beachparty.fabric.rei.display.MiniFridgeDisplay;
import satisfyu.beachparty.fabric.rei.display.TikiBarDisplay;
import satisfyu.beachparty.recipe.MiniFridgeRecipe;
import satisfyu.beachparty.recipe.TikiBarRecipe;
import satisfyu.beachparty.registry.ObjectRegistry;

public class BeachpartyREI implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new MiniFridgeCategory());
        registry.add(new TikiBarCategory());

        registry.addWorkstations(MiniFridgeCategory.MINE_FRIDGE_DISPLAY, EntryStacks.of(ObjectRegistry.MINI_FRIDGE.get()));
        registry.addWorkstations(TikiBarCategory.TIKI_BAR_DISPLAY, EntryStacks.of(ObjectRegistry.TIKI_BAR.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(MiniFridgeRecipe.class, MiniFridgeDisplay::new);
        registry.registerFiller(TikiBarRecipe.class, TikiBarDisplay::new);
    }
}
