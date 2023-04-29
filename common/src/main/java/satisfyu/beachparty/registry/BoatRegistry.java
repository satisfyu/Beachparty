package satisfyu.beachparty.registry;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;

public class BoatRegistry {

    public static TerraformBoatType PALM;

    public static void init() {
        Item palmBoat = TerraformBoatItemHelper.registerBoatItem(new BeachpartyIdentifier("palm_boat"), () -> PALM, false, Beachparty.CREATIVE_TAB);
        Item palmChestBoat = TerraformBoatItemHelper.registerBoatItem(new BeachpartyIdentifier("palm_chest_boat"), () -> PALM, true, Beachparty.CREATIVE_TAB);
        PALM = new TerraformBoatType.Builder().item(palmBoat).chestItem(palmChestBoat).planks(ObjectRegistry.PALM_PLANKS.asItem()).build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, new BeachpartyIdentifier("palm"), PALM);
    }
}
