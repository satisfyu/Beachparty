package satisfyu.beachparty.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.recipe.MiniFridgeRecipe;
import satisfyu.beachparty.recipe.TikiBarRecipe;

import java.util.HashMap;
import java.util.Map;

public class RecipeRegistry {
    private static final Map<ResourceLocation, RecipeSerializer<?>> RECIPE_SERIALIZERS = new HashMap<>();
    private static final Map<ResourceLocation, RecipeType<?>> RECIPE_TYPES = new HashMap<>();
    public static final RecipeType<TikiBarRecipe> TIKI_BAR_RECIPE_RECIPE_TYPE = create("tiki_bar_mixing");
    public static final RecipeSerializer<TikiBarRecipe> TIKI_BAR_RECIPE_RECIPE_SERIALIZER = create("tiki_bar_mixing", new TikiBarRecipe.Serializer());
    public static final RecipeType<MiniFridgeRecipe> MINI_FRIDGE_RECIPE_RECIPE_TYPE = create("mini_fridge_mixing");
    public static final RecipeSerializer<MiniFridgeRecipe> MINI_FRIDGE_RECIPE_RECIPE_SERIALIZER = create("mini_fridge_mixing", new MiniFridgeRecipe.Serializer());


    private static <T extends Recipe<?>> RecipeSerializer<T> create(String name, RecipeSerializer<T> serializer) {
        RECIPE_SERIALIZERS.put(new BeachpartyIdentifier(name), serializer);
        return serializer;
    }

    private static <T extends Recipe<?>> RecipeType<T> create(String name) {
        final RecipeType<T> type = new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        RECIPE_TYPES.put(new BeachpartyIdentifier(name), type);
        return type;
    }

    public static void init() {
        for (Map.Entry<ResourceLocation, RecipeSerializer<?>> entry : RECIPE_SERIALIZERS.entrySet()) {
            Registry.register(Registry.RECIPE_SERIALIZER, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<ResourceLocation, RecipeType<?>> entry : RECIPE_TYPES.entrySet()) {
            Registry.register(Registry.RECIPE_TYPE, entry.getKey(), entry.getValue());
        }
    }


}
