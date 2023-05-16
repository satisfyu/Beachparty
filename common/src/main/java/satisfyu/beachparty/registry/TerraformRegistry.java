package satisfyu.beachparty.registry;

import de.cristelknight.doapi.DoApiExpectPlatform;
import de.cristelknight.doapi.terraform.TerraformSignHelper;
import de.cristelknight.doapi.terraform.boat.TerraformBoatType;
import de.cristelknight.doapi.terraform.boat.item.TerraformBoatItemHelper;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;

public class TerraformRegistry {

    public static ResourceLocation PALM_BOAT_TYPE_LOCATION = new BeachpartyIdentifier("palm");
    public static final ResourceLocation PALM_SIGN_TEXTURE_ID = new BeachpartyIdentifier("entity/sign/palm");

    public static void init() {
        RegistrySupplier<Item> palmBoat = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "palm_boat", PALM_BOAT_TYPE_LOCATION, false, Beachparty.CREATIVE_TAB);
        RegistrySupplier<Item> palmChestBoat = TerraformBoatItemHelper.registerBoatItem(ObjectRegistry.ITEMS, "palm_chest_boat", PALM_BOAT_TYPE_LOCATION, true, Beachparty.CREATIVE_TAB);

        DoApiExpectPlatform.registerBoatType(PALM_BOAT_TYPE_LOCATION, TerraformBoatType.builder().item(palmBoat).chestItem(palmChestBoat).build());



        RegistrySupplier<Block> sign = ObjectRegistry.registerWithoutItem("palm_sign", () -> TerraformSignHelper.getSign(PALM_SIGN_TEXTURE_ID));
        RegistrySupplier<Block> wallSign = ObjectRegistry.registerWithoutItem("palm_wall_sign", () -> TerraformSignHelper.getWallSign(PALM_SIGN_TEXTURE_ID));

        RegistrySupplier<Item> palmSignItem = ObjectRegistry.registerItem("palm_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(Beachparty.CREATIVE_TAB), sign.get(), wallSign.get()));
    }

}
