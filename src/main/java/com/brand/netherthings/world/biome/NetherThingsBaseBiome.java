package com.brand.netherthings.world.biome;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.content.Ores;
import com.brand.netherthings.features.NetherThingsFeatures;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceConfig;

import net.minecraft.block.Blocks;
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
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public abstract class NetherThingsBaseBiome extends Biome {
	
	protected NetherThingsBaseBiome(String name, NetherThingsSurfaceConfig config, int fireCount) {
		this(name, NetherSurfaces.NETHER_THINGS, config, fireCount);
	}

	protected NetherThingsBaseBiome(String name, SurfaceBuilder<NetherThingsSurfaceConfig> surface, NetherThingsSurfaceConfig config, int fireCount) {
		super((new Biome.Settings()).configureSurfaceBuilder(surface, config).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).waterColor(4159204).waterFogColor(329011).parent((String)null));
		
		// Nether Fortress
		this.addStructureFeature(Feature.NETHER_BRIDGE, FeatureConfig.DEFAULT);
		
		// Cave
		this.addCarver(GenerationStep.Carver.AIR, configureCarver(Carver.HELL_CAVE, new ProbabilityConfig(0.2F)));
		
		// Ore; targets netherrack and blazing netherrack
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getDefaultState(), 14), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 10, 20, 128)));
		
		if (NetherThings.CONFIG.enableOverworldOresInNether) {
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_COAL_ORE.getDefaultState(), 9), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 0, 0, 128)));
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_IRON_ORE.getDefaultState(), 9), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 0, 0, 128)));
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_GOLD_ORE.getDefaultState(), 10), Decorator.COUNT_RANGE, new RangeDecoratorConfig(7, 0, 0, 85)));
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_REDSTONE_ORE.getDefaultState(), 8), Decorator.COUNT_RANGE, new RangeDecoratorConfig(8, 0, 0, 60)));
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_LAPIS_ORE.getDefaultState(), 6), Decorator.COUNT_RANGE, new RangeDecoratorConfig(6, 0, 0, 110)));
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_DIAMOND_ORE.getDefaultState(), 8), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 40)));
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_EMERALD_ORE.getDefaultState(), 4), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 40)));
		}
		if (NetherThings.CONFIG.enableNetherVibraniumOre)	{
		       this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NETHERRACK, Ores.NETHER_VIBRANIUM_ORE.getDefaultState(), 4), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 60)));       
		}
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
