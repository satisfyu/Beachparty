package net.satisfyu.beachparty.entity.pelican;

import net.satisfyu.beachparty.client.BeachPartyClient;

import static net.satisfyu.beachparty.Beachparty.MOD_ID;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class PelicanRenderer extends MobRenderer<PelicanEntity, PelicanModel<PelicanEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/pelican.png");

    public PelicanRenderer(EntityRendererProvider.Context context) {
        super(context, new PelicanModel<>(context.bakeLayer(BeachPartyClient.PELICAN_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTexture(PelicanEntity entity) {
        return TEXTURE;
    }
}
