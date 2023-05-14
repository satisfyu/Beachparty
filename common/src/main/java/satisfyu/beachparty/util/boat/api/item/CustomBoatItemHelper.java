package satisfyu.beachparty.util.boat.api.item;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.DispenserBlock;
import satisfyu.beachparty.registry.ObjectRegistry;
import satisfyu.beachparty.util.boat.impl.item.TerraformBoatDispenserBehavior;
import satisfyu.beachparty.util.boat.impl.item.TerraformBoatItem;


public final class CustomBoatItemHelper {
	private CustomBoatItemHelper() {
		return;
	}


	public static RegistrySupplier<Item> registerBoatItem(ResourceLocation id, ResourceLocation key, boolean chest) {
		return registerBoatItem(id, key, chest, new Item.Properties().stacksTo(1));
	}
	public static RegistrySupplier<Item> registerBoatItem(ResourceLocation id, ResourceLocation key, boolean chest, Item.Properties settings) {
		return ObjectRegistry.ITEMS.register(id, () -> new TerraformBoatItem(key, chest, settings));
	}

	public static void registerBoatDispenserBehavior(ItemLike item, ResourceLocation boatKey, boolean chest) {
		DispenserBlock.registerBehavior(item, new TerraformBoatDispenserBehavior(boatKey, chest));
	}
}
