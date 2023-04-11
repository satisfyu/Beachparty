package net.satisfyu.beachparty.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.satisfyu.beachparty.registry.RecipeRegistry;
import net.satisfyu.beachparty.util.BeachpartyUtil;

public class TikiBarRecipe implements Recipe<Inventory> {

    final Identifier id;
    private final DefaultedList<Ingredient> inputs;
    private final ItemStack output;

    public TikiBarRecipe(Identifier id, DefaultedList<Ingredient> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return BeachpartyUtil.matchesRecipe(inventory, inputs, 0, 6);
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output.copy();
    }

    @Override
    public Identifier getId() {
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
    public DefaultedList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<TikiBarRecipe> {

        @Override
        public TikiBarRecipe read(Identifier id, JsonObject json) {
            final var ingredients = BeachpartyUtil.deserializeIngredients(JsonHelper.getArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for TikiBar Recipe");
            } else if (ingredients.size() > 6) {
                throw new JsonParseException("Too many ingredients for TikiBar Recipe");
            } else {
                return new TikiBarRecipe(id, ingredients, ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result")));
            }
        }

        @Override
        public TikiBarRecipe read(Identifier id, PacketByteBuf buf) {
            final var ingredients  = DefaultedList.ofSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromPacket(buf));
            return new TikiBarRecipe(id, ingredients, buf.readItemStack());
        }

        @Override
        public void write(PacketByteBuf buf, TikiBarRecipe recipe) {
            buf.writeVarInt(recipe.inputs.size());
            recipe.inputs.forEach(entry -> entry.write(buf));
            buf.writeItemStack(recipe.getOutput());
        }
    }

}