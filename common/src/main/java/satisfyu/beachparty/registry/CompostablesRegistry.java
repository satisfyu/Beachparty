package satisfyu.beachparty.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostablesRegistry {
    
    public static void init(){
        registerCompostableItem(ObjectRegistry.DRY_BUSH, 0.4F);
        registerCompostableItem(ObjectRegistry.DRY_BUSH_TALL, 0.4F);
        registerCompostableItem(ObjectRegistry.PALM_LEAVES, 0.6F);
        registerCompostableItem(ObjectRegistry.PALM_SAPLING, 0.4F);
        registerCompostableItem(ObjectRegistry.BEACH_GRASS, 0.4F);
        registerCompostableItem(ObjectRegistry.COCONUT, 0.3F);
        registerCompostableItem(ObjectRegistry.COCONUT_OPEN, 0.3F);
    }



    public static <T extends ItemLike> void registerCompostableItem(RegistrySupplier<T> item, float chance) {
        if (item.get().asItem() != Items.AIR) {
            ComposterBlock.COMPOSTABLES.put(item.get(), chance);
        }
    }
}
