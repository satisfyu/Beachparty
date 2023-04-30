package satisfyu.beachparty.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.util.boat.api.TerraformBoatType;
import satisfyu.beachparty.util.boat.api.TerraformBoatTypeRegistry;
import satisfyu.beachparty.util.boat.api.item.TerraformBoatItemHelper;

import java.util.function.Supplier;

public class BoatRegistry {

    public static TerraformBoatType PALM;

    public static void init() {
        RegistrySupplier<Item> palmBoat = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "palm_boat", () -> PALM, false, Beachparty.CREATIVE_TAB);
        RegistrySupplier<Item> palmChestBoat = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "palm_chest_boat", () -> PALM, true, Beachparty.CREATIVE_TAB);

        PALM = new TerraformBoatType.Builder().item(palmBoat).chestItem(palmChestBoat).build();

        TerraformBoatTypeRegistry.register(new BeachpartyIdentifier("palm"), PALM);
    }

}
