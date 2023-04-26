package net.satisfyu.beachparty.client.screen.recipe.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.satisfyu.beachparty.client.recipebook.PrivateRecipeBookWidget;
import net.satisfyu.beachparty.registry.RecipeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MiniFridgeRecipeBook extends PrivateRecipeBookWidget {
    private static final Text TOGGLE_COOKABLE_TEXT;

    public MiniFridgeRecipeBook() {
    }

    @Override
    public void showGhostRecipe(Recipe<?> recipe, List<Slot> slots) {
        this.ghostSlots.addSlot(recipe.getOutput(), slots.get(0).x, slots.get(0).y);
        int slot = 1;
        for (Ingredient ingredient : recipe.getIngredients()) {
            ItemStack[] inputStacks = ingredient.getMatchingStacks();
            ItemStack inputStack = inputStacks[Random.create().nextBetween(0, inputStacks.length - 1)];
            this.ghostSlots.addSlot(inputStack, slots.get(slot).x, slots.get(slot++).y);
        }
    }

    @Override
    public void insertRecipe(Recipe<?> recipe) {
        int usedInputSlots = 0;
        for (Ingredient ingredient : recipe.getIngredients()) {
            int slotIndex = 0;
            for (Slot slot : screenHandler.slots) {
                ItemStack itemStack = slot.getStack();

                if (ingredient.test(itemStack) && usedInputSlots < 3) {
                    MinecraftClient.getInstance().interactionManager.clickSlot(screenHandler.syncId, slotIndex, 0, SlotActionType.PICKUP, MinecraftClient.getInstance().player);
                    MinecraftClient.getInstance().interactionManager.clickSlot(screenHandler.syncId, usedInputSlots, 0, SlotActionType.PICKUP, MinecraftClient.getInstance().player);
                    ++usedInputSlots;
                    break;
                }
                ++slotIndex;
            }
        }
    }

    @Override
    protected void setCraftableButtonTexture() {
        this.toggleCraftableButton.setTextureUV(152, 41, 28, 18, TEXTURE);
    }

    @Override
    public void slotClicked(@Nullable Slot slot) {
        super.slotClicked(slot);
        if (slot != null && slot.id < this.screenHandler.getCraftingSlotCount()) {
            this.ghostSlots.reset();
        }
    }

    @Override
    protected RecipeType<? extends Recipe<Inventory>> getRecipeType() {
        return RecipeRegistry.MINI_FRIDGE_RECIPE_RECIPE_TYPE;
    }

    @Override
    protected Text getToggleCraftableButtonText() {
        return TOGGLE_COOKABLE_TEXT;
    }

    static {
        TOGGLE_COOKABLE_TEXT = Text.translatable("gui.vinery.recipebook.toggleRecipes.fidgeable");
    }
}
