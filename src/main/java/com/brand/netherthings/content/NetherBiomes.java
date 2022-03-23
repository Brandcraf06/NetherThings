package com.brand.netherthings.content;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.world.biome.*;
import com.brand.netherthings.world.biome.layer.NetherBiomeLayer;
import com.brand.netherthings.world.biome.layer.NetherSubBiomeLayer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class NetherBiomes {

    public static final GlowingJungleBiome GLOWING_JUNGLE = new GlowingJungleBiome();
    public static final CondemnedBarrensBiome CONDEMNED_BARRENS = new CondemnedBarrensBiome();
    public static final CondemnedForestBiome CONDEMNED_FOREST = new CondemnedForestBiome();
    public static final GlowshroomForestBiome GLOWSHROOM_FOREST = new GlowshroomForestBiome();
    public static final BlazingSoilsBiome BLAZING_SOILS = new BlazingSoilsBiome();
    public static final MagmaticSoilsBiome MAGMATIC_SOILS = new MagmaticSoilsBiome();
    public static final NetherMeadowBiome NETHER_MEADOW = new NetherMeadowBiome();
    public static final BurntMeadowBiome BURNT_MEADOW = new BurntMeadowBiome();

    public static void init() {

        // Biomes

        if (NetherThings.CONFIG.enableGlowingJungleBiome || NetherThings.CONFIG.enableHugeNetherMushroomsGeneration) {
            addBiome(GLOWING_JUNGLE, 15);
        }

        if (NetherThings.CONFIG.enableCondemnedBarrensBiome) {
            addBiome(CONDEMNED_BARRENS, 15);
        }

        if (NetherThings.CONFIG.enableBlazingSoilsBiome) {
            addBiome(BLAZING_SOILS, 15);

            if (NetherThings.CONFIG.enableNetherMeadowBiome) {
                addBiome(NETHER_MEADOW, 7);
            }

            // Sub Biomes

            if (NetherThings.CONFIG.enableGlowshroomForestBiome || NetherThings.CONFIG.enableHugeNetherMushroomsGeneration) {
                addSubBiome(Biomes.NETHER, GLOWSHROOM_FOREST, 25);
            }

            if (NetherThings.CONFIG.enableNetherMeadowBiome) {
                addSubBiome(NetherBiomes.NETHER_MEADOW, BURNT_MEADOW, 25);
            }

            if (NetherThings.CONFIG.enableCondemnedBarrensBiome || NetherThings.CONFIG.enableHugeNetherMushroomsGeneration) {
                addSubBiome(NetherBiomes.CONDEMNED_BARRENS, CONDEMNED_FOREST, 40);
            }

            if (NetherThings.CONFIG.enableBlazingSoilsBiome) {
                addSubBiome(NetherBiomes.BLAZING_SOILS, MAGMATIC_SOILS, 20);
            }
        }
    }

    /**
     * @param biome  the biome to be added to the nether
     * @param weight the chance of this biome being picked over other biomes. Average weight is 10.
     */
    public static void addBiome(Biome biome, int weight) {
        NetherBiomeLayer.INSTANCE.addBiome(biome, weight);
    }

    /**
     * @param parent   the biome in which this biome generates
     * @param subBiome the biome to be added as a sub biome
     * @param chance   the chance of this biome replacing the parent biome, out of 100
     */
    public static void addSubBiome(Biome parent, Biome subBiome, int chance) {
        NetherSubBiomeLayer.INSTANCE.addSubBiome(parent, subBiome, chance);
    }
}
