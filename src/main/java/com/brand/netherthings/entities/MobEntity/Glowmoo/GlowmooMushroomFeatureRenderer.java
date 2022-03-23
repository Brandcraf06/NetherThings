package com.brand.netherthings.entities.MobEntity.Glowmoo;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class GlowmooMushroomFeatureRenderer<T extends GlowmooEntity> extends FeatureRenderer<T, CowEntityModel<T>> {
    public GlowmooMushroomFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T GlowmooEntity, float f, float g, float h, float j, float k, float l) {
        if (!GlowmooEntity.isBaby()) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            boolean bl = minecraftClient.hasOutline(GlowmooEntity) && GlowmooEntity.isInvisible();
            if (!GlowmooEntity.isInvisible() || bl) {
                BlockRenderManager blockRenderManager = minecraftClient.getBlockRenderManager();
                BlockState blockState = GlowmooEntity.getMooshroomType().getMushroomState();
                int m = LivingEntityRenderer.getOverlay(GlowmooEntity, 0.0F);
                BakedModel bakedModel = blockRenderManager.getModel(blockState);
                matrixStack.push();
                matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5D, -0.5D, -0.5D);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, blockState, m, bakedModel);
                matrixStack.pop();
                matrixStack.push();
                matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(42.0F));
                matrixStack.translate(0.10000000149011612D, 0.0D, -0.6000000238418579D);
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5D, -0.5D, -0.5D);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, blockState, m, bakedModel);
                matrixStack.pop();
                matrixStack.push();
                ((CowEntityModel)this.getContextModel()).getHead().rotate(matrixStack);
                matrixStack.translate(0.0D, -0.699999988079071D, -0.20000000298023224D);
                matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5D, -0.5D, -0.5D);
                this.renderMushroom(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, blockState, m, bakedModel);
                matrixStack.pop();
            }
        }
    }

    private void renderMushroom(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockRenderManager blockRenderManager, BlockState mushroomState, int overlay, BakedModel mushroomModel) {
        if (renderAsModel) {
            blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), mushroomState, mushroomModel, 0.0F, 0.0F, 0.0F, light, overlay);
        } else {
            blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }

    }
}
