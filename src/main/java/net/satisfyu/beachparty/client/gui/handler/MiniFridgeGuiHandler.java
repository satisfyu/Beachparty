package net.satisfyu.beachparty.client.gui.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.satisfyu.beachparty.client.gui.slot.TikiBarOutputSlot;
import net.satisfyu.beachparty.registry.ScreenHandlerTypesRegistry;

public class MiniFridgeGuiHandler extends ScreenHandler  {

    private final PropertyDelegate delegate;


    public MiniFridgeGuiHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(6), new ArrayPropertyDelegate(2));
    }
    public MiniFridgeGuiHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ScreenHandlerTypesRegistry.MINI_FRIDGE_GUI_HANDLER, syncId);

        // Output
        this.addSlot(new TikiBarOutputSlot(playerInventory.player, inventory, 0, 128,  35));
        // Inputs
        this.addSlot(new Slot(inventory, 1, 55, 26));
        this.addSlot(new Slot(inventory, 2, 55, 44));
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        this.delegate = propertyDelegate;
        addProperties(this.delegate);
    }
    public int getCookProgress() {
        int i = this.delegate.get(0);
        int j = this.delegate.get(1);
        if (j == 0 || i == 0) {
            return 0;
        }
        return i * 24 / j;
    }
    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }
}