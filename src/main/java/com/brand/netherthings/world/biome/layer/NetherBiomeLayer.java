package com.brand.netherthings.world.biome.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.type.InitLayer;
import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public enum NetherBiomeLayer implements InitLayer {
    INSTANCE;

    NetherBiomeLayer() {
        // Add vanilla nether
        this.addBiome(Biomes.NETHER_WASTES, 20);
        this.addBiome(Biomes.BASALT_DELTAS, 20);
        this.addBiome(Biomes.CRIMSON_FOREST, 20);
        this.addBiome(Biomes.WARPED_FOREST, 20);
        this.addBiome(Biomes.SOUL_SAND_VALLEY, 20);
    }

    @Override
    public int sample(LayerRandomnessSource rand, int x, int z) {
        return Registry.BIOME.getRawId(biomes.get(rand.nextInt(totalWeight)));
    }

    private Biome[] biomesArray = new Biome[]{};
    private final List<Biome> biomes = new ArrayList<>();
    private int totalWeight = 0;

    /**
     * @param biome  the biome to be added to the nether
     * @param weight the chance of this biome being picked over other biomes. Average weight is 10.
     */
    public void addBiome(Biome biome, int weight) {
        if (!biomes.contains(biome)) {
            // update biomes array
            biomesArray = ArrayUtils.add(biomesArray, biome);
        }

        for (int i = 0; i < weight; ++i) {
            biomes.add(biome);
        }
        totalWeight += weight;
    }

    public Biome[] getBiomes() {
        return biomesArray;
    }

}
