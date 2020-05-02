package com.brand.netherthings.entities;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.entities.MobEntity.Glowmoo.GlowmooEntity;
import com.brand.netherthings.entities.MobEntity.WitherPigman.WitherPigmanEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class NetherEntities {


	public static final EntityType<WitherPigmanEntity> WITHER_PIGMAN = register("wither_pigman", FabricEntityTypeBuilder.create(EntityCategory.MONSTER, WitherPigmanEntity::new).fireImmune().dimensions(EntityDimensions.changing(0.6F, 1.95F)).build());
	public static final EntityType<GlowmooEntity> BLUE_GLOWMOO = register("blue_glowmoo", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, GlowmooEntity::new).dimensions(EntityDimensions.changing(0.9F, 1.4F)).build());
	public static final EntityType<GlowmooEntity> GREEN_GLOWMOO = register("green_glowmoo", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, GlowmooEntity::new).dimensions(EntityDimensions.changing(0.9F, 1.4F)).build());
	public static final EntityType<GlowmooEntity> PURPLE_GLOWMOO = register("purple_glowmoo", FabricEntityTypeBuilder.create(EntityCategory.CREATURE, GlowmooEntity::new).dimensions(EntityDimensions.changing(0.9F, 1.4F)).build());

	private static <T extends Entity> EntityType<T> register(String name, EntityType<T> builder) {
		return Registry.register(Registry.ENTITY_TYPE, new Identifier(NetherThings.MOD_ID, name), builder);
	}
}