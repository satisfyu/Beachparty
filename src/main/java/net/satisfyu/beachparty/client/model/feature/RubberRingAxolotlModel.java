package net.satisfyu.beachparty.client.model.feature;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.satisfyu.beachparty.BeachpartyIdentifier;

public class RubberRingAxolotlModel<T extends Entity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new BeachpartyIdentifier("rubber_ring_axolotl"), "main");

	private final ModelPart group;
	private final ModelPart bone;
	public RubberRingAxolotlModel(ModelPart root) {
		this.group = root.getChild("group");
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData group = modelPartData.addChild("group", ModelPartBuilder.create().uv(8, 0).cuboid(-5.5F, -2.5F, -15.5F, 14.0F, 4.0F, 14.0F, new Dilation(0.0F))
				.uv(40, 26).cuboid(5.5F, 1.5F, -4.5F, -8.0F, -4.0F, -8.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 22.5F, 8.5F));

		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, -10).cuboid(0.0F, -4.0F, 5.0F, 0.0F, 10.0F, 10.0F, new Dilation(0.0F))
				.uv(24, 26).cuboid(-8.0F, -8.0F, -6.0F, 16.0F, 6.0F, 0.0F, new Dilation(0.0F))
				.uv(24, 18).cuboid(-4.0F, -5.0F, -9.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = bone.addChild("cube_r1", ModelPartBuilder.create().uv(9, 27).cuboid(5.5F, -5.0F, -13.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-12.5F, 1.5F, 7.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r2 = bone.addChild("cube_r2", ModelPartBuilder.create().uv(9, 27).cuboid(6.5F, 1.2F, -13.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, 1.5F, 7.0F, 0.0F, 0.0F, -0.3927F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		group.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}