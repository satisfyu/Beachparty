package satisfyu.beachparty.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.util.boat.api.TerraformBoatType;
import satisfyu.beachparty.util.boat.api.TerraformBoatTypeRegistry;
import satisfyu.beachparty.util.boat.api.item.CustomBoatItemHelper;

public class BoatRegistry {

    public static final ResourceLocation PALM_BOAT_ID = new ResourceLocation(Beachparty.MOD_ID, "palm_boat");
    public static final ResourceLocation PALM_CHEST_BOAT_ID = new ResourceLocation(Beachparty.MOD_ID, "palm_chest_boat");

    public static final RegistrySupplier<Item> PALM_BOAT = CustomBoatItemHelper.registerBoatItem(PALM_BOAT_ID, PALM_BOAT_ID, false);
    public static final RegistrySupplier<Item> PALM_CHEST_BOAT = CustomBoatItemHelper.registerBoatItem(PALM_CHEST_BOAT_ID, PALM_BOAT_ID, true);

    public static TerraformBoatType PALM;

    public static void initItems(){

    }

    public static void init() {
        PALM = new TerraformBoatType.Builder().item(PALM_BOAT.get()).chestItem(PALM_CHEST_BOAT.get()).planks(ObjectRegistry.PALM_PLANKS.get().asItem()).build();
        TerraformBoatTypeRegistry.register(PALM_BOAT_ID, PALM);

        CustomBoatItemHelper.registerBoatDispenserBehavior(PALM_BOAT.get(), PALM_BOAT_ID, false);
        CustomBoatItemHelper.registerBoatDispenserBehavior(PALM_CHEST_BOAT.get(), PALM_BOAT_ID, true);
    }
}
