package satisfyu.beachparty.util.boat.impl;


import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.entity.pelican.PelicanEntity;
import satisfyu.beachparty.registry.EntityRegistry;
import satisfyu.beachparty.util.boat.impl.entity.TerraformBoatEntity;
import satisfyu.beachparty.util.boat.impl.entity.TerraformChestBoatEntity;

public final class TerraformBoatInitializer {

	private static final ResourceLocation BOAT_ID = new BeachpartyIdentifier("boat");

	public static final RegistrySupplier<EntityType<TerraformBoatEntity>> BOAT = EntityRegistry.create("boat",() -> EntityType.Builder.<TerraformBoatEntity>of(TerraformBoatEntity::new, MobCategory.MISC)
		.sized(1.375f, 0.5625f)
		.build(BOAT_ID.toString()));

	private static final ResourceLocation CHEST_BOAT_ID = new BeachpartyIdentifier("chest_boat");
	public static final RegistrySupplier<EntityType<TerraformChestBoatEntity>> CHEST_BOAT = EntityRegistry.create("chest_boat", () -> EntityType.Builder.<TerraformChestBoatEntity>of(TerraformChestBoatEntity::new, MobCategory.MISC)
		.sized(1.375f, 0.5625f)
		.build(CHEST_BOAT_ID.toString()));


	public static void init() {
		TerraformBoatTrackedData.register();
	}
}
