package com.brand.netherthings.entities.MobEntity.Glowmoo;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

@Environment(EnvType.CLIENT)
@SuppressWarnings({"rawtypes", "unchecked"})
public class GlowmooEntityRenderer extends MobEntityRenderer<GlowmooEntity, CowEntityModel<GlowmooEntity>> {
    private static final Map<GlowmooEntity.Type, Identifier> TEXTURES = (Map) Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(GlowmooEntity.Type.BLUE, new Identifier("textures/entity/glowmoo/blue_glowmoo.png"));
        hashMap.put(GlowmooEntity.Type.GREEN, new Identifier("textures/entity/glowmoos/green_glowmoo.png"));
        hashMap.put(GlowmooEntity.Type.PURPLE, new Identifier("textures/entity/glowmoos/purple_glowmoo.png"));
    });

    public GlowmooEntityRenderer(Object context) {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM)), 0.7F);
        this.addFeature(new GlowmooMushroomFeatureRenderer(this));
    }

    public Identifier getTexture(GlowmooEntity GlowmooEntity) {
        return TEXTURES.get(GlowmooEntity.getMooshroomType());
    }
}

