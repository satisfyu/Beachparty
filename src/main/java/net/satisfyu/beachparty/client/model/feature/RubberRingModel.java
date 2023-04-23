package net.satisfyu.beachparty.client.model.feature;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.satisfyu.beachparty.BeachpartyIdentifier;

public class RubberRingModel<T extends Entity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new BeachpartyIdentifier("rubber_ring_blue"), "main");
	private final ModelPart group;
	public RubberRingModel(ModelPart root) {
		this.group = root.getChild("group");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData group = modelPartData.addChild("group", ModelPartBuilder.create().uv(3, 0).cuboid(-5.5F, -2.5F, -15.5F, 14.0F, 4.0F, 14.0F, new Dilation(0.0F))
				.uv(39, 26).cuboid(5.5F, 1.5F, -4.5F, -8.0F, -4.0F, -8.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 22.5F, 8.5F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		group.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}