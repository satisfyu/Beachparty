package satisfyu.beachparty.util.boat.impl;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import satisfyu.beachparty.util.boat.api.TerraformBoatType;
import satisfyu.beachparty.util.boat.api.TerraformBoatTypeRegistry;

public final class TerraformBoatTrackedData {
	private TerraformBoatTrackedData() {
		return;
	}

	public static final EntityDataSerializer<TerraformBoatType> HANDLER = new EntityDataSerializer<>() {
		public void write(FriendlyByteBuf buf, TerraformBoatType boat) {
			buf.writeResourceLocation(TerraformBoatTypeRegistry.getId(boat));
		}

		public TerraformBoatType read(FriendlyByteBuf buf) {
			return TerraformBoatTypeRegistry.get(buf.readResourceLocation());
		}

		public TerraformBoatType copy(TerraformBoatType boat) {
			return boat;
		}
	};

	public static void register() {
		EntityDataSerializers.registerSerializer(HANDLER);
	}
}
