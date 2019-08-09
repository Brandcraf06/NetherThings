package com.brand.netherthings.world;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.features.NetherThingsFeatures;
import com.brand.netherthings.world.biome.NetherThingsBaseBiome;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.BushFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlantedFeatureConfig;
import net.minecraft.world.gen.feature.RandomBooleanFeatureConfig;

public class NetherVegetation {
	// note: custom nether foliage is done in custom nether biomes 
	public static void addNetherVegetation() {
		if (NetherThings.CONFIG.enableNetherCactus) {
			for (Biome biome : Registry.BIOME) {
				if (biome.getCategory() == Biome.Category.NETHER && !(biome instanceof NetherThingsBaseBiome)) {
					biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.NETHER_CACTUS, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(40)));
				}
			}
		}

		if (NetherThings.CONFIG.enableNetherMushrooms) {
			for (Biome biome : Registry.BIOME) {
				if (biome.getCategory() == Biome.Category.NETHER && !(biome instanceof NetherThingsBaseBiome)) {
					biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfig(NetherThingsFeatures.HUGE_GREEN_GLOWING_MUSHROOM, new PlantedFeatureConfig(false), NetherThingsFeatures.HUGE_BLUE_GLOWING_MUSHROOM, new PlantedFeatureConfig(false)), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(30)));
					biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.HUGE_PURPLE_GLOWING_MUSHROOM, new PlantedFeatureConfig(false), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(15)));
					biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.GREEN_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(3)));
					biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.BLUE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(3)));
					biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.PURPLE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(3)));
				}
			}
		}
	}
}