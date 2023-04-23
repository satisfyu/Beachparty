package net.satisfyu.beachparty.entity.pelican;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.client.BeachPartyClient;

import static net.satisfyu.beachparty.Beachparty.MOD_ID;


public class PelicanRenderer extends MobEntityRenderer<PelicanEntity, PelicanModel<PelicanEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/pelican.png");

    public PelicanRenderer(EntityRendererFactory.Context context) {
        super(context, new PelicanModel<>(context.getPart(BeachPartyClient.PELICAN_MODEL_LAYER)), 0.7f);
    }

    @Override
    public Identifier getTexture(PelicanEntity entity) {
        return TEXTURE;
    }
}
