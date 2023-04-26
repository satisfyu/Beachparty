package net.satisfyu.beachparty.client.gui.handler;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.Slot;
import net.satisfyu.beachparty.client.gui.slot.IceSlot;
import net.satisfyu.beachparty.client.gui.slot.TikiBarOutputSlot;
import net.satisfyu.beachparty.client.recipebook.IRecipeBookGroup;
import net.satisfyu.beachparty.client.recipebook.custom.TikiBarRecipeBookGroup;
import net.satisfyu.beachparty.recipe.TikiBarRecipe;
import net.satisfyu.beachparty.registry.ScreenHandlerTypesRegistry;

import java.util.List;

public class TikiBarGuiHandler extends AbstractRecipeBookGUIScreenHandler {

    public TikiBarGuiHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(2));
    }
    public TikiBarGuiHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ScreenHandlerTypesRegistry.TIKI_BAR_GUI_HANDLER, syncId, 2, playerInventory, inventory, propertyDelegate);

        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(PlayerInventory playerInventory, Inventory inventory) {
        // Output
        this.addSlot(new TikiBarOutputSlot(playerInventory.player, inventory, 0, 128,  35));
        // Inputs
        this.addSlot(new Slot(inventory, 1, 55, 26));
        this.addSlot(new Slot(inventory, 2, 55, 44));
    }

    private void buildPlayerContainer(Inventory playerInventory) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public int getShakeXProgress() {
        int progress = this.propertyDelegate.get(0);
        int totalProgress = this.propertyDelegate.get(1);
        if (totalProgress == 0 || progress == 0) {
            return 0;
        }
        return progress * 22 / totalProgress + 1;
    }

    public int getShakeYProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = this.propertyDelegate.get(1);
        if (totalProgress == 0 || progress == 0) {
            return 0;
        }
        return progress * 19 / totalProgress + 1;
    }

    @Override
    public List<IRecipeBookGroup> getGroups() {
        return TikiBarRecipeBookGroup.TIKI_GROUPS;
    }

    @Override
    public boolean hasIngredient(Recipe<?> recipe) {
        if (recipe instanceof TikiBarRecipe tikiBarRecipe) {
            for (Ingredient ingredient : tikiBarRecipe.getIngredients()) {
                boolean found = false;
                for (Slot slot : this.slots) {
                    if (ingredient.test(slot.getStack())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return 2;
    }
}