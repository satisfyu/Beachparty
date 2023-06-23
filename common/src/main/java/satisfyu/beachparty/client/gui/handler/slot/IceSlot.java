package satisfyu.beachparty.client.gui.handler.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class IceSlot extends Slot {
    public IceSlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() == Items.ICE || stack.getItem() == Items.SNOW_BLOCK || stack.getItem() == Items.PACKED_ICE || stack.getItem() == Items.SNOWBALL;
    }
}

