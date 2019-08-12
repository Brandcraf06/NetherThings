package com.brand.netherthings;

import com.brand.netherthings.entities.MobEntity.Glowmoo.GlowmooEntity;
import com.brand.netherthings.entities.MobEntity.Glowmoo.GlowmooEntityRenderer;
import com.brand.netherthings.entities.MobEntity.WitherPigman.WitherPigmanEntity;
import com.brand.netherthings.entities.MobEntity.WitherPigman.WitherPigmanEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;

public class NetherThingsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		EntityRendererRegistry.INSTANCE.register(WitherPigmanEntity.class, ((manager, context) -> new WitherPigmanEntityRenderer(manager)));
		EntityRendererRegistry.INSTANCE.register(GlowmooEntity.class, ((manager, context) -> new GlowmooEntityRenderer(manager)));
		
		}
	}
