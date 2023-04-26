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

public class MiniFridgeRecipe implements Recipe<Inventory> {

    final Identifier id;
    private final DefaultedList<Ingredient> inputs;
    private final ItemStack output;

    public MiniFridgeRecipe(Identifier id, DefaultedList<Ingredient> inputs, ItemStack output) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return BeachpartyUtil.matchesRecipe(inventory, inputs, 1, 2);
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
        return RecipeRegistry.MINI_FRIDGE_RECIPE_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.MINI_FRIDGE_RECIPE_RECIPE_TYPE;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<MiniFridgeRecipe> {

        @Override
        public MiniFridgeRecipe read(Identifier id, JsonObject json) {
            final var ingredients = BeachpartyUtil.deserializeIngredients(JsonHelper.getArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for Mini Fridge Recipe");
            } else if (ingredients.size() > 4) {
                throw new JsonParseException("Too many ingredients for Mini Fridge Recipe");
            } else {
                return new MiniFridgeRecipe(id, ingredients, ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result")));
            }
        }

        @Override
        public MiniFridgeRecipe read(Identifier id, PacketByteBuf buf) {
            final var ingredients  = DefaultedList.ofSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromPacket(buf));
            return new MiniFridgeRecipe(id, ingredients, buf.readItemStack());
        }

        @Override
        public void write(PacketByteBuf buf, MiniFridgeRecipe recipe) {
            buf.writeVarInt(recipe.inputs.size());
            recipe.inputs.forEach(entry -> entry.write(buf));
            buf.writeItemStack(recipe.getOutput());
        }
    }

}