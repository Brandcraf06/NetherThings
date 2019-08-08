package com.brand.netherthings.content;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.world.biome.layer.NetherBiomeLayer;
import com.brand.netherthings.world.biome.layer.NetherSubBiomeLayer;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceBuilder;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceConfig;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class NetherBiomes {
	
	public static final NetherThingsSurfaceBuilder NETHER_THINGS = new NetherThingsSurfaceBuilder(NetherThingsSurfaceConfig::deserialize);
	
	public static void init() {
		// Surface Builder
		Registry.register(Registry.SURFACE_BUILDER, new Identifier(NetherThings.MOD_ID, "nether_things"), NETHER_THINGS);
		
		// Biomes
		
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
