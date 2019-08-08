package com.brand.netherthings.world.biome.layer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.InitLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

public enum NetherBiomeLayer implements InitLayer {
	INSTANCE;
	
	private NetherBiomeLayer() {
		// Add vanilla nether
		this.addBiome(Biomes.NETHER, 10);
	}

	@Override
	public int sample(LayerRandomnessSource rand, int x, int z) {
		return Registry.BIOME.getRawId(biomes.get(rand.nextInt(totalWeight)));
	}
	
	private Biome[] biomesArray = new Biome[] {};
	private final List<Biome> biomes = new ArrayList<>();
	private int totalWeight = 0;
	
	/**
	 * @param biome the biome to be added to the nether
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
