package com.brand.netherthings.world.biome;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.features.NetherThingsFeatures;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NoiseHeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;

public class BurntMeadowBiome extends NetherThingsGreenBaseBiome {

    public BurntMeadowBiome() {
        super("burnt_meadow", NetherSurfaces.NETHER_MEADOW, NetherSurfaces.BURNED_MEADOW_CONFIG, 7);

        if (NetherThings.CONFIG.enableHugeNetherMushroomsGeneration) {
            this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NetherThingsFeatures.HUGE_BURNT_MUSHROOM_STEM, new PlantedFeatureConfig(false), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(100)));
        }

        this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(OtherBlocks.DEAD_GRASS.getDefaultState()), Decorator.NOISE_HEIGHTMAP_DOUBLE, new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10)));

        // Nether Fortress
        this.addStructureFeature(Feature.NETHER_BRIDGE, FeatureConfig.DEFAULT);

        // Ores
        this.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, configureFeature(NetherThingsFeatures.BLAZING_NETHERRACK_ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Ores.QUARTZ_ORE.getDefaultState(), 14), Decorator.COUNT_RANGE, new RangeDecoratorConfig(16, 10, 20, 128)));
        if (NetherThings.CONFIG.enableNetherVibraniumOre) {
            this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Ores.VIBRANIUM_ORE.getDefaultState(), 4), Decorator.COUNT_RANGE, new RangeDecoratorConfig(2, 0, 0, 100)));
        }
        this.addDefaultMobs();
        this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.BLAZE, 10, 1, 1));
    }

}
