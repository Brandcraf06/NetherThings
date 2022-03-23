package com.brand.netherthings;

import com.brand.netherthings.contentNew.NetherBlocks;
import com.brand.netherthings.entities.MobEntity.Glowmoo.GlowmooEntityRenderer;
import com.brand.netherthings.entities.NetherEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry ;
import net.minecraft.client.render.RenderLayer;

public class NetherThingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(NetherEntities.GLOWMOO, ((context) -> new GlowmooEntityRenderer(context)));


        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                NetherBlocks.GHOST_WHEAT
        );
    }
}