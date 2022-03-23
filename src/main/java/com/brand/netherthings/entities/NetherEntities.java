package com.brand.netherthings.entities;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.entities.MobEntity.Glowmoo.GlowmooEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class NetherEntities {


    public static final EntityType<GlowmooEntity> GLOWMOO = register("glowmoo", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GlowmooEntity::new).dimensions(EntityDimensions.changing(0.9F, 1.4F)).trackRangeBlocks(10).build());


    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(NetherThings.MOD_ID, name), builder);
    }
}