package satisfyu.beachparty.entity.pelican;

import com.google.common.collect.ImmutableList;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static satisfyu.beachparty.Beachparty.MOD_ID;

public class PelicanModel<T extends PelicanEntity> extends AgeableListModel<T> {

    public static final ModelLayerLocation PELICAN_MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(MOD_ID, "pelican"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart wing0;
    private final ModelPart wing1;
    public PelicanModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.wing0 = root.getChild("wing0");
        this.wing1 = root.getChild("wing1");
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(17, 1).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 11.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(18, 18).addBox(0.0F, -2.0F, 4.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(1, 30).addBox(-2.0F, -5.0F, -10.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(1, 22).addBox(-2.0F, -6.0F, -10.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -4.0F));

        PartDefinition leg0 = modelPartData.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(6, 45).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.0F, 1.0F));

        PartDefinition leg1 = modelPartData.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(6, 45).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 19.0F, 1.0F));

        PartDefinition wing0 = modelPartData.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(26, 21).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 13.0F, 0.0F));

        PartDefinition wing1 = modelPartData.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(26, 21).addBox(0.0F, -1.0F, -3.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 13.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg0.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        wing0.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        wing1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.leg0, this.leg1, this.wing0, this.wing1);
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180);
        this.head.yRot = headYaw * ((float)Math.PI / 180);

        this.leg0.xRot = Mth.cos(limbAngle * 0.6662f) * 1.4f * limbDistance;
        this.leg1.xRot = Mth.cos(limbAngle * 0.6662f + (float)Math.PI) * 1.4f * limbDistance;
    }
}