package com.brand.netherthings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceConfig;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.TheNetherDimension;
import net.minecraft.world.gen.chunk.CavesChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

@Mixin(value = TheNetherDimension.class, priority = 500)
public abstract class NetherDimensionMixin extends Dimension {
	public NetherDimensionMixin(World world, DimensionType type) {
		super(world, type, 0.1F);
	}

	@Inject(method = "createChunkGenerator", at = @At("RETURN"), cancellable = true)
	public void createChunkGenerator(CallbackInfoReturnable<ChunkGenerator<? extends ChunkGeneratorConfig>> info) {
		CavesChunkGeneratorConfig config = ChunkGeneratorType.CAVES.createConfig();

		// These can be changed in the surface builders.
		config.setDefaultBlock(Blocks.NETHERRACK.getDefaultState());
		config.setDefaultFluid(Blocks.LAVA.getDefaultState());

		// use vanilla chunk generator with custom biome source
		MultiNoiseBiomeSourceConfig multiNoiseBiomeSourceConfig = ((MultiNoiseBiomeSourceConfig)BiomeSourceType.MULTI_NOISE.getConfig(this.world.getSeed())).withBiomes(ImmutableList.of(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.BASALT_DELTAS));
		info.setReturnValue(ChunkGeneratorType.CAVES.create(this.world, BiomeSourceType.MULTI_NOISE.applyConfig(multiNoiseBiomeSourceConfig), config));
	}

}
