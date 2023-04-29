package satisfyu.beachparty.client.recipebook.custom;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import satisfyu.beachparty.client.recipebook.IRecipeBookGroup;
import satisfyu.beachparty.recipe.TikiBarRecipe;
import satisfyu.beachparty.registry.ObjectRegistry;

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
                    if (tikiBarRecipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultInstance()))) {
                        return true;
                    }
                }
                case MISC -> {
                    if (tikiBarRecipe.getIngredients().stream().noneMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultInstance()))) {
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
