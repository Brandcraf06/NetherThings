package com.brand.netherthings.world.biome;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.content.Ores;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.entities.NetherEntities;
import com.brand.netherthings.features.NetherThingsFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.decorator.NoiseHeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.BoulderFeatureConfig;
import net.minecraft.world.gen.feature.BushFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlantedFeatureConfig;
import net.minecraft.world.gen.feature.RandomBooleanFeatureConfig;

public class NetherMeadowBiome extends NetherThingsGreenBaseBiome {

	public NetherMeadowBiome() {
		super("nether_meadow", NetherSurfaces.NETHER_MEADOW, NetherSurfaces.MEADOW_CONFIG, 10);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfig(NetherThingsFeatures.HUGE_GREEN_GLOWING_MUSHROOM, new PlantedFeatureConfig(false), NetherThingsFeatures.HUGE_BLUE_GLOWING_MUSHROOM, new PlantedFeatureConfig(false)), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(50)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.HUGE_PURPLE_GLOWING_MUSHROOM, new PlantedFeatureConfig(false), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(30)));
		this.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.FOREST_ROCK, new BoulderFeatureConfig(Blocks.STONE.getDefaultState(), 0), Decorator.FOREST_ROCK, new CountDecoratorConfig(3)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Decorator.NOISE_HEIGHTMAP_DOUBLE, new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.GREEN_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(10)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.BLUE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(10)));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(OtherBlocks.PURPLE_GLOWING_MUSHROOM.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(10)));
		
		// Nether Fortress
		this.addStructureFeature(Feature.NETHER_BRIDGE, FeatureConfig.DEFAULT);
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.NETHER_BRIDGE, FeatureConfig.DEFAULT, Decorator.NOPE, DecoratorConfig.DEFAULT));
		
		// Ores
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Ores.QUARTZ_ORE.getDefaultState(), 14), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 10, 20, 128)));
		if (NetherThings.CONFIG.enableNetherVibraniumOre)	{
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Ores.VIBRANIUM_ORE.getDefaultState(), 4), Decorator.COUNT_RANGE, new RangeDecoratorConfig(2, 0, 0, 100)));       
		}
		this.addDefaultMobs();
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(NetherEntities.BLUE_GLOWMOO, 500, 2, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(NetherEntities.GREEN_GLOWMOO, 500, 2, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(NetherEntities.PURPLE_GLOWMOO, 500, 2, 4));
	}

}
