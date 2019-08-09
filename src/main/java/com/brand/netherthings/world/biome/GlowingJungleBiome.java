package com.brand.netherthings.world.biome;

import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.features.NetherThingsFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.BushFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.NetherSpringFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlantedFeatureConfig;
import net.minecraft.world.gen.feature.RandomBooleanFeatureConfig;

public class GlowingJungleBiome extends NetherThingsBaseBiome {

	public GlowingJungleBiome() {
		super("glowing_jungle", NetherSurfaces.DEFAULT_CONFIG, 2);

		// shroom
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfig(NetherThingsFeatures.HUGE_GREEN_GLOWING_MUSHROOM, new PlantedFeatureConfig(false), NetherThingsFeatures.HUGE_BLUE_GLOWING_MUSHROOM, new PlantedFeatureConfig(false)), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(160)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.HUGE_PURPLE_GLOWING_MUSHROOM, new PlantedFeatureConfig(false), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(80)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.GREEN_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(7)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.BLUE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(5)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.PURPLE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(5)));

		// glowstone
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.GLOWSTONE_BLOB, FeatureConfig.DEFAULT, Decorator.LIGHT_GEM_CHANCE, new CountDecoratorConfig(18)));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.GLOWSTONE_BLOB, FeatureConfig.DEFAULT, Decorator.COUNT_RANGE, new RangeDecoratorConfig(14, 0, 0, 128)));

		// nether spring and magma block
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.NETHER_SPRING, new NetherSpringFeatureConfig(true), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 10, 20, 128)));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Blocks.MAGMA_BLOCK.getDefaultState(), 33), Decorator.MAGMA, new CountDecoratorConfig(7)));

		this.addDefaultMobs();
	}

}
