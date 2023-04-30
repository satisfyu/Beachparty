package satisfyu.beachparty.util.boat.impl.client;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import satisfyu.beachparty.client.BeachPartyClient;
import satisfyu.beachparty.util.boat.impl.TerraformBoatInitializer;

import java.util.Map;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class TerraformBoatClientInitializer {

	public static void init(Map<Supplier<EntityType<?>>, EntityRendererProvider<?>> map) {
		BeachPartyClient.registerEntityRenderer(map, TerraformBoatInitializer.BOAT, context -> new TerraformBoatEntityRenderer(context, false));
		BeachPartyClient.registerEntityRenderer(map, TerraformBoatInitializer.CHEST_BOAT, context -> new TerraformBoatEntityRenderer(context, true));
	}
}
