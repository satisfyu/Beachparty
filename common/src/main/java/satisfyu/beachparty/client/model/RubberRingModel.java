package satisfyu.beachparty.client.model;

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
import satisfyu.beachparty.BeachpartyIdentifier;

public class RubberRingModel<T extends Entity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new BeachpartyIdentifier("rubber_ring_blue"), "main");
	private final ModelPart group;
	public RubberRingModel(ModelPart root) {
		this.group = root.getChild("group");
	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition group = modelPartData.addOrReplaceChild("group", CubeListBuilder.create().texOffs(3, 0).addBox(-5.5F, -2.5F, -15.5F, 14.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(39, 26).addBox(5.5F, 1.5F, -4.5F, -8.0F, -4.0F, -8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 22.5F, 8.5F));
		return LayerDefinition.create(modelData, 64, 64);
	}
	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		group.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}