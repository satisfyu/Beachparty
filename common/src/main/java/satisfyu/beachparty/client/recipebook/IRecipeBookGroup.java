package satisfyu.beachparty.client.recipebook;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

public interface IRecipeBookGroup {
    boolean fitRecipe(Recipe<? extends Container> recipe, RegistryAccess registryManager);
    List<ItemStack> getIcons();
}
