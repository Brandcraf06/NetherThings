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
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class NetherThingsGreenBaseBiome extends Biome {
	
	protected NetherThingsGreenBaseBiome(String name, NetherThingsSurfaceConfig config, int fireCount) {
		this(name, NetherSurfaces.NETHER_MEADOW, config, fireCount);
	}

	protected NetherThingsGreenBaseBiome(String name, SurfaceBuilder<NetherThingsSurfaceConfig> surface, NetherThingsSurfaceConfig config, int fireCount) {
		super((new Biome.Settings()).configureSurfaceBuilder(surface, config).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(1.0F).temperature(2.0F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String)null));
				
		// Ores
		this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Ores.QUARTZ_ORE.getDefaultState(), 14), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 10, 20, 128)));
		
		if (NetherThings.CONFIG.enableOverworldOresInNether) {
			 this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 9), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 0, 0, 128)));
			 this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 0, 0, 128)));
			 this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 10), Decorator.COUNT_RANGE, new RangeDecoratorConfig(7, 0, 0, 85)));
			 this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 8), Decorator.COUNT_RANGE, new RangeDecoratorConfig(8, 0, 0, 60)));
			 this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 6), Decorator.COUNT_RANGE, new RangeDecoratorConfig(6, 0, 0, 110)));
			 this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.DIAMOND_ORE.getDefaultState(), 8), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 40)));
			 this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.EMERALD_ORE.getDefaultState(), 4), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 40)));
		}
		
	    if (NetherThings.CONFIG.enableGlowingReedsGeneration)	{
		     this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.GLOWING_REEDS, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(80)));
	    }
	
		Registry.register(Registry.BIOME, new Identifier(NetherThings.MOD_ID, name), this);
	}
	
	public final NetherThingsGreenBaseBiome addDefaultMobs() {
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE_PIGMAN, 50, 4, 4));
		
		return this;
	}

}
