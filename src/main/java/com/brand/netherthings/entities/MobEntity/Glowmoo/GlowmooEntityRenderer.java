package com.brand.netherthings.entities.MobEntity.Glowmoo;

import com.brand.netherthings.NetherThings;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GlowmooEntityRenderer extends MobEntityRenderer<GlowmooEntity, CowEntityModel<GlowmooEntity>> {
	
	public GlowmooEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new CowEntityModel(), 0.7F);
		this.addFeature(new GlowmooMushroomFeatureRenderer(this));
	}

	@Override
	protected Identifier getTexture(GlowmooEntity glowmoo) {
			return new Identifier(NetherThings.MOD_ID, "textures/entity/glowmoos/" + glowmoo.id + ".png");
		}
	}	

