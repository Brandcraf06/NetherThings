package com.brand.netherthings.world.biome.source;

import java.util.function.LongFunction;

import com.brand.netherthings.world.biome.layer.NetherBiomeLayer;
import com.brand.netherthings.world.biome.layer.NetherSubBiomeLayer;
import com.google.common.collect.ImmutableList;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.ScaleLayer;
import net.minecraft.world.biome.layer.util.CachingLayerContext;
import net.minecraft.world.biome.layer.util.CachingLayerSampler;
import net.minecraft.world.biome.layer.util.LayerFactory;
import net.minecraft.world.biome.layer.util.LayerSampleContext;
import net.minecraft.world.biome.layer.util.LayerSampler;
import net.minecraft.world.biome.source.BiomeLayerSampler;

public final class NetherThingsBiomeLayers {
	
	private static Biome[] biomes = new Biome[] {};
	
	public static BiomeLayerSampler[] createLayers(long seed) {
		ImmutableList<LayerFactory<CachingLayerSampler>> samplers = build(seed, (salt) -> {
			return new CachingLayerContext(25, seed, salt);
		});
		BiomeLayerSampler noiseLayer = new BiomeLayerSampler(samplers.get(0));
		BiomeLayerSampler biomeLayer = new BiomeLayerSampler(samplers.get(1));
		
		return new BiomeLayerSampler[]{noiseLayer, biomeLayer};
	}

	private static <T extends LayerSampler, C extends LayerSampleContext<T>> ImmutableList<LayerFactory<T>> build(long seed, LongFunction<C> context) {
		int biomeSize = 4;
		
		LayerFactory<T> biomeFactory = NetherBiomeLayer.INSTANCE.create(context.apply(1L));
		biomes = NetherBiomeLayer.INSTANCE.getBiomes();
		
		biomeFactory = ScaleLayer.NORMAL.create(context.apply(1000L), biomeFactory);
		biomeFactory = ScaleLayer.NORMAL.create(context.apply(1001L), biomeFactory);
		
		biomeFactory = NetherSubBiomeLayer.INSTANCE.create(context.apply(2L), biomeFactory);
		biomes = NetherSubBiomeLayer.INSTANCE.addBiomes(biomes);
		
		for (int i = 0; i < biomeSize; ++i) {
			biomeFactory = ScaleLayer.NORMAL.create(context.apply(2000 + i), biomeFactory);
		}
		
		LayerFactory<T> cellScaleFactory = ScaleLayer.NORMAL.create(context.apply(10L), biomeFactory);
		
		return ImmutableList.of(biomeFactory, cellScaleFactory);
	}
	
	public static Biome[] getBiomes() {
		return biomes;
	}
}
