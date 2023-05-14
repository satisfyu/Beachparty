package satisfyu.beachparty.util.boat.impl.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import satisfyu.beachparty.util.boat.impl.TerraformBoatInitializer;

@Environment(EnvType.CLIENT)
public final class TerraformBoatClientInitializer  {

	public static void init() {
		EntityRendererRegistry.register(TerraformBoatInitializer.BOAT, context -> new CustomBoatEntityRenderer(context, false));
		EntityRendererRegistry.register(TerraformBoatInitializer.CHEST_BOAT, context -> new CustomBoatEntityRenderer(context, true));
	}
}
