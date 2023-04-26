package net.satisfyu.beachparty.client.recipebook.custom;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.satisfyu.beachparty.client.recipebook.IRecipeBookGroup;
import net.satisfyu.beachparty.recipe.TikiBarRecipe;
import net.satisfyu.beachparty.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum TikiBarRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    COCKTAIL(new ItemStack(ObjectRegistry.COCONUT_COCKTAIL)),
    MISC(new ItemStack(ObjectRegistry.COCONUT));

    public static final List<IRecipeBookGroup> TIKI_GROUPS = ImmutableList.of(SEARCH, COCKTAIL, MISC);

    private final List<ItemStack> icons;

    TikiBarRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<?> recipe) {
        if (recipe instanceof TikiBarRecipe tikiBarRecipe) {
            switch (this) {
                case SEARCH -> {
                    return true;
                }
                case COCKTAIL -> {
                    if (tikiBarRecipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultStack()))) {
                        return true;
                    }
                }
                case MISC -> {
                    if (tikiBarRecipe.getIngredients().stream().noneMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultStack()))) {
                        return true;
                    }
                }
                default -> {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}
