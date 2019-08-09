package com.brand.netherthings.content;

import com.brand.netherthings.world.biome.BlazingSoilsBiome;
import com.brand.netherthings.world.biome.CondemnedBarrensBiome;
import com.brand.netherthings.world.biome.GlowingJungleBiome;
import com.brand.netherthings.world.biome.MushroomForestBiome;
import com.brand.netherthings.world.biome.layer.NetherBiomeLayer;
import com.brand.netherthings.world.biome.layer.NetherSubBiomeLayer;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class NetherBiomes {
	
	public static final GlowingJungleBiome GLOWING_JUNGLE = new GlowingJungleBiome();
	public static final CondemnedBarrensBiome CONDEMNED_BARRENS = new CondemnedBarrensBiome();
	public static final MushroomForestBiome MUSHROOM_FOREST = new MushroomForestBiome();
	public static final BlazingSoilsBiome BLAZING_SOILS = new BlazingSoilsBiome();
	
	public static void init() {
		
		// Biomes
		addBiome(GLOWING_JUNGLE, 10);
		addBiome(CONDEMNED_BARRENS, 10);
		addBiome(BLAZING_SOILS, 10);
		
		// Sub Biomes
		addSubBiome(Biomes.NETHER, MUSHROOM_FOREST, 25);
	}
	
	/**
	 * @param biome the biome to be added to the nether
	 * @param weight the chance of this biome being picked over other biomes. Average weight is 10.
	 */
	public static void addBiome(Biome biome, int weight) {
		NetherBiomeLayer.INSTANCE.addBiome(biome, weight);
	}
	
	/**
	 * @param parent the biome in which this biome generates
	 * @param subBiome the biome to be added as a sub biome
	 * @param chance the chance of this biome replacing the parent biome, out of 100
	 */
	public static void addSubBiome(Biome parent, Biome subBiome, int chance) {
		NetherSubBiomeLayer.INSTANCE.addSubBiome(parent, subBiome, chance);
	}
}
