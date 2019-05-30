package com.brand.netherthings.entities;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.entities.MobEntity.WitherPigman.WitherPigmanEntity;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetherMobs {

	public static final EntityType<WitherPigmanEntity> WITHER_PIGMAN = register("wither_pigman", FabricEntityTypeBuilder.<WitherPigmanEntity>create(EntityCategory.MONSTER, WitherPigmanEntity::new).setImmuneToFire().size(EntitySize.constant(0.6F, 1.95F)));

    public static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> entityBuilder) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(NetherThings.MOD_ID, name), entityBuilder.build());
    }
}