package com.brand.netherthings.world.biome;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.NetherBiomes;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceConfig;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public abstract class NetherThingsBaseBiome extends Biome {

	protected NetherThingsBaseBiome(String name, NetherThingsSurfaceConfig config, int fireCount) {
		super((new Biome.Settings()).configureSurfaceBuilder(NetherBiomes.NETHER_THINGS, config).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
		
		// Nether Fortress
		this.addStructureFeature(Feature.NETHER_BRIDGE, FeatureConfig.DEFAULT);
		
		// Cave
		this.addCarver(GenerationStep.Carver.AIR, configureCarver(Carver.HELL_CAVE, new ProbabilityConfig(0.2F)));
		
		// Fortress (part 2) and fire
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.NETHER_BRIDGE, FeatureConfig.DEFAULT, Decorator.NOPE, DecoratorConfig.DEFAULT));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.HELL_FIRE, FeatureConfig.DEFAULT, Decorator.HELL_FIRE, new CountDecoratorConfig(fireCount)));
		
		Registry.register(Registry.BIOME, new Identifier(NetherThings.MOD_ID, name), this);
	}
	
	public final NetherThingsBaseBiome addDefaultMobs() {
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.GHAST, 50, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE_PIGMAN, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.MAGMA_CUBE, 2, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4));
		
		return this;
	}

}
