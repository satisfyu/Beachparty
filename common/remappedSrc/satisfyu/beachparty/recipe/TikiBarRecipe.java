package satisfyu.beachparty.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import satisfyu.beachparty.registry.RecipeRegistry;
import satisfyu.beachparty.util.BeachpartyUtil;

public class TikiBarRecipe implements Recipe<Container> {

    final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public TikiBarRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        return BeachpartyUtil.matchesRecipe(inventory, inputs, 1, 2);
    }

    @Override
    public ItemStack assemble(Container inventory) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.TIKI_BAR_RECIPE_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.TIKI_BAR_RECIPE_RECIPE_TYPE;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<TikiBarRecipe> {

        @Override
        public TikiBarRecipe fromJson(ResourceLocation id, JsonObject json) {
            final var ingredients = BeachpartyUtil.deserializeIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for TikiBar Recipe");
            } else if (ingredients.size() > 4) {
                throw new JsonParseException("Too many ingredients for TikiBar Recipe");
            } else {
                return new TikiBarRecipe(id, ingredients, ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result")));
            }
        }

        @Override
        public TikiBarRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            final var ingredients  = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            return new TikiBarRecipe(id, ingredients, buf.readItem());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, TikiBarRecipe recipe) {
            buf.writeVarInt(recipe.inputs.size());
            recipe.inputs.forEach(entry -> entry.toNetwork(buf));
            buf.writeItem(recipe.getResultItem());
        }

    }

}