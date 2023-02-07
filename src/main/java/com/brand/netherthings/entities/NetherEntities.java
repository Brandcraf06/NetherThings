package com.brand.netherthings.entities;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.entities.MobEntity.Glowmoo.GlowmooEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class NetherEntities {


    public static final EntityType<GlowmooEntity> GLOWMOO = register("glowmoo", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GlowmooEntity::new).dimensions(EntityDimensions.changing(0.9F, 1.4F)).trackRangeBlocks(10).build());


    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(NetherThings.MOD_ID, name), builder);
    }
}