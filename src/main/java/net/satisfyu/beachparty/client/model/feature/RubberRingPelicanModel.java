package net.satisfyu.beachparty.client.model.feature;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.satisfyu.beachparty.BeachpartyIdentifier;

public class RubberRingPelicanModel<T extends Entity> extends EntityModel<T> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new BeachpartyIdentifier("rubber_ring_pelican"), "main");
	private final ModelPart group;
	private final ModelPart comb;
	private final ModelPart bone;
	public RubberRingPelicanModel(ModelPart root) {
		this.group = root.getChild("group");
		this.comb = root.getChild("comb");
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData group = modelPartData.addChild("group", ModelPartBuilder.create().uv(7, 0).cuboid(-5.5F, -2.5F, -15.5F, 14.0F, 4.0F, 14.0F, new Dilation(0.0F))
				.uv(50, 26).cuboid(5.5F, 1.5F, -4.5F, -8.0F, -4.0F, -8.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, 22.5F, 8.5F));

		ModelPartData comb = modelPartData.addChild("comb", ModelPartBuilder.create().uv(1, 31).cuboid(-2.0F, 3.0F, -1.0F, 4.0F, 6.0F, 8.0F, new Dilation(0.0F))
				.uv(1, 22).cuboid(-2.0F, 2.0F, -1.0F, 4.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, -17.0F));

		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -10.0F, 3.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F))
				.uv(18, 18).cuboid(0.0F, -7.0F, 7.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, -13.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		group.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		comb.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}