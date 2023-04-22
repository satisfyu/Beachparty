package net.satisfyu.beachparty.entity.pelican;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class PelicanModel<T extends PelicanEntity> extends ChickenEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart wing0;
    private final ModelPart wing1;
    public PelicanModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.wing0 = root.getChild("wing0");
        this.wing1 = root.getChild("wing1");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(17, 1).cuboid(-3.0F, -4.0F, -4.0F, 6.0F, 11.0F, 8.0F, new Dilation(0.0F))
                .uv(18, 18).cuboid(0.0F, -2.0F, 4.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F))
                .uv(1, 30).cuboid(-2.0F, -5.0F, -10.0F, 4.0F, 6.0F, 8.0F, new Dilation(0.0F))
                .uv(1, 22).cuboid(-2.0F, -6.0F, -10.0F, 4.0F, 1.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, -4.0F));

        ModelPartData leg0 = modelPartData.addChild("leg0", ModelPartBuilder.create().uv(6, 45).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 19.0F, 1.0F));

        ModelPartData leg1 = modelPartData.addChild("leg1", ModelPartBuilder.create().uv(6, 45).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 19.0F, 1.0F));

        ModelPartData wing0 = modelPartData.addChild("wing0", ModelPartBuilder.create().uv(26, 21).cuboid(-1.0F, -1.0F, -3.0F, 1.0F, 7.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 13.0F, 0.0F));

        ModelPartData wing1 = modelPartData.addChild("wing1", ModelPartBuilder.create().uv(26, 21).cuboid(0.0F, -1.0F, -3.0F, 1.0F, 7.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 13.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg0.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        wing0.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        wing1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}