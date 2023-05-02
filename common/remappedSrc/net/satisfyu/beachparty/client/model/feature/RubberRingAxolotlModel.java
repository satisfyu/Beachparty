package net.satisfyu.beachparty.client.model.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;
import net.satisfyu.beachparty.BeachpartyIdentifier;

public class RubberRingAxolotlModel<T extends Entity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new BeachpartyIdentifier("rubber_ring_axolotl"), "main");

	private final ModelPart group;
	private final ModelPart bone;
	public RubberRingAxolotlModel(ModelPart root) {
		this.group = root.getChild("group");
		this.bone = root.getChild("bone");
	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition group = modelPartData.addOrReplaceChild("group", CubeListBuilder.create().texOffs(8, 0).addBox(-5.5F, -2.5F, -15.5F, 14.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(40, 26).addBox(5.5F, 1.5F, -4.5F, -8.0F, -4.0F, -8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 22.5F, 8.5F));

		PartDefinition bone = modelPartData.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, -10).addBox(0.0F, -4.0F, 5.0F, 0.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(24, 26).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(24, 18).addBox(-4.0F, -5.0F, -9.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 27).addBox(5.5F, -5.0F, -13.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.5F, 1.5F, 7.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 27).addBox(6.5F, 1.2F, -13.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.5F, 7.0F, 0.0F, 0.0F, -0.3927F));
		return LayerDefinition.create(modelData, 64, 64);
	}
	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		group.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}