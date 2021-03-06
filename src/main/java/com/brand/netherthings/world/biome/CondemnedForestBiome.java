package com.brand.netherthings.world.biome;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.entities.NetherEntities;
import com.brand.netherthings.features.NetherThingsFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceRangeDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.BushFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.NetherSpringFeatureConfig;

public class CondemnedForestBiome extends NetherThingsBaseBiome {

	public CondemnedForestBiome() {
		super("condemned_forest", NetherSurfaces.CONDEMNED_BARRENS, NetherSurfaces.DEFAULT_CONFIG, 9);
		
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.CONDEMNED_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(100)));

		// glowing shrooms
		
		if (NetherThings.CONFIG.enableNetherMushrooms) {
			this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.GREEN_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
			this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.BLUE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
			this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.PURPLE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(1)));
		}

		// normal shrooms
		
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.BUSH, new BushFeatureConfig(Blocks.BROWN_MUSHROOM.getDefaultState()), Decorator.CHANCE_RANGE, new ChanceRangeDecoratorConfig(0.5F, 0, 0, 128)));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.BUSH, new BushFeatureConfig(Blocks.RED_MUSHROOM.getDefaultState()), Decorator.CHANCE_RANGE, new ChanceRangeDecoratorConfig(0.5F, 0, 0, 128)));

		// glowstone
		
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.GLOWSTONE_BLOB, FeatureConfig.DEFAULT, Decorator.LIGHT_GEM_CHANCE, new CountDecoratorConfig(3)));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.GLOWSTONE_BLOB, FeatureConfig.DEFAULT, Decorator.COUNT_RANGE, new RangeDecoratorConfig(9, 0, 0, 128)));

		// nether cactus
		
		if (NetherThings.CONFIG.enableNetherCactus) {
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.NETHER_CACTUS, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(200)));
		}
		
		
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.NETHER_SPRING, new NetherSpringFeatureConfig(true), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 10, 20, 128)));

		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(NetherEntities.WITHER_PIGMAN, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.WITHER_SKELETON, 50, 2, 2));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.GHAST, 25, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.MAGMA_CUBE, 2, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4));
		
	}

}
