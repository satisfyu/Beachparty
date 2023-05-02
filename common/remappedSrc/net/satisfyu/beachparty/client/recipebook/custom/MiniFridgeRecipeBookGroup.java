package net.satisfyu.beachparty.client.recipebook.custom;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import net.satisfyu.beachparty.client.recipebook.IRecipeBookGroup;
import net.satisfyu.beachparty.recipe.MiniFridgeRecipe;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum MiniFridgeRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    FRIDGE(new ItemStack(Blocks.ICE)),
    MISC(new ItemStack(Items.SNOWBALL));

    public static final List<IRecipeBookGroup> FRIDGE_GROUPS = ImmutableList.of(SEARCH, FRIDGE, MISC);

    private final List<ItemStack> icons;

    MiniFridgeRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<?> recipe) {
        if (recipe instanceof MiniFridgeRecipe miniFridgeRecipe) {
            switch (this) {
                case SEARCH -> {
                    return true;
                }
                case FRIDGE -> {
                    if (miniFridgeRecipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultInstance()))) {
                        return true;
                    }
                }
                case MISC -> {
                    if (miniFridgeRecipe.getIngredients().stream().noneMatch((ingredient) -> ingredient.test(Blocks.ICE.asItem().getDefaultInstance()))) {
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
