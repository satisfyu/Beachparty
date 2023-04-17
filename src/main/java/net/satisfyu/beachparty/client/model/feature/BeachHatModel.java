package net.satisfyu.beachparty.client.model.feature;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.satisfyu.beachparty.BeachpartyIdentifier;

public class BeachHatModel<T extends Entity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new BeachpartyIdentifier("beach_hat"), "main");

	private final ModelPart beach_hat;
	public BeachHatModel(ModelPart root) {
		this.beach_hat = root.getChild("beach_hat");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData beach_hat = modelPartData.addChild("beach_hat", ModelPartBuilder.create().uv(-22, 15).cuboid(-11.0F, 0.0F, -11.0F, 22.0F, 0.0F, 22.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-5.0F, -5.01F, -5.0F, 10.0F, 5.0F, 10.0F, new Dilation(0.0F))
				.uv(0, 39).cuboid(-5.25F, -5.6F, -5.25F, 10.0F, 5.0F, 10.0F, new Dilation(0.0F))
				.uv(42, 6).cuboid(1.1F, -0.25F, -11.0F, 2.0F, 0.0F, 7.0F, new Dilation(0.0F))
				.uv(49, 14).cuboid(1.1F, -0.25F, -11.0F, 2.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		beach_hat.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}