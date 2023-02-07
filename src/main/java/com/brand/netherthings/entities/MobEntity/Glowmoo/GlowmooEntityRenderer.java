package com.brand.netherthings.entities.MobEntity.Glowmoo;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

@Environment(EnvType.CLIENT)

public class GlowmooEntityRenderer extends MobEntityRenderer<GlowmooEntity, CowEntityModel<GlowmooEntity>> {
    private static final Map<GlowmooEntity.Type, Identifier> TEXTURES = (Map) Util.make(Maps.newHashMap(), (map) -> {
        map.put(GlowmooEntity.Type.BLUE, new Identifier("textures/entity/glowmoo/blue_glowmoo.png"));
        map.put(GlowmooEntity.Type.GREEN, new Identifier("textures/entity/glowmoos/green_glowmoo.png"));
        map.put(GlowmooEntity.Type.PURPLE, new Identifier("textures/entity/glowmoos/purple_glowmoo.png"));
    });

    public GlowmooEntityRenderer(Context context) {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM)), 0.7F);
        this.addFeature(new GlowmooMushroomFeatureRenderer(this, context.getBlockRenderManager()));
    }

    public Identifier getTexture(GlowmooEntity GlowmooEntity) {
        return (Identifier)TEXTURES.get(GlowmooEntity.getVariant());
    }
}

