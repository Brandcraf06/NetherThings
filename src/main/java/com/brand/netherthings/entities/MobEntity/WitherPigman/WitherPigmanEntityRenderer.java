package com.brand.netherthings.entities.MobEntity.WitherPigman;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.feature.ArmorBipedFeatureRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WitherPigmanEntityRenderer extends BipedEntityRenderer<WitherPigmanEntity, ZombieEntityModel<WitherPigmanEntity>> {
   private static final Identifier SKIN = new Identifier("textures/entity/wither_pigman.png");

   @SuppressWarnings({ "rawtypes", "unchecked" })
public WitherPigmanEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
      super(entityRenderDispatcher_1, new ZombieEntityModel(), 0.5F);
      this.addFeature(new ArmorBipedFeatureRenderer(this, new ZombieEntityModel(0.5F, true), new ZombieEntityModel(1.0F, true)));
   }

   protected Identifier getTexture(WitherPigmanEntity witherPigmanEntity_1) {
      return SKIN;
   }
}
