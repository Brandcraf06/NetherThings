package com.brand.netherthings.world;

import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.features.NetherThingsFeatures;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.BushFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.RandomBooleanFeatureConfig;

public class NetherVegetation {
	public static void addNetherVegetation() {
    	for (Biome biome : Registry.BIOME) {
    		if  (biome.getCategory() == Biome.Category.NETHER) { 
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.NETHER_CACTUS, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(40)));
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfig(NetherThingsFeatures.HUGE_GREEN_GLOWING_MUSHROOM, FeatureConfig.DEFAULT, NetherThingsFeatures.HUGE_BLUE_GLOWING_MUSHROOM, FeatureConfig.DEFAULT), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(55)));
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.HUGE_PURPLE_GLOWING_MUSHROOM, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(25)));
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.GREEN_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(3)));
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.BLUE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(3)));
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.PURPLE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(3)));
      }
    }
  }
}