package com.brand.netherthings.world.surfacebuilder;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class NetherThingsSurfaceBuilder extends SurfaceBuilder<NetherThingsSurfaceConfig> {
	protected static final BlockState CAVE_AIR;
	protected static final BlockState GRAVEL;
	protected static final BlockState GLOWSTONE;
	protected long seed;
	protected OctavePerlinNoiseSampler noise;

	public NetherThingsSurfaceBuilder(Function<Dynamic<?>, ? extends NetherThingsSurfaceConfig> function_1) {
		super(function_1);
	}

	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int int_3, double double_1, BlockState blockState_1, BlockState blockState_2, int int_4, long seed, NetherThingsSurfaceConfig config) {
		int int_5 = int_4 + 1;
		int localX = x & 15;
		int localZ = z & 15;

		boolean boolean_1 = this.noise.sample((double)x * 0.03125D, (double)z * 0.03125D, 0.0D) + rand.nextDouble() * 0.2D > 0.0D;
		boolean boolean_2 = this.noise.sample((double)x * 0.03125D, 109.0D, (double)z * 0.03125D) + rand.nextDouble() * 0.2D > 0.0D;
		int int_8 = (int)(double_1 / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();
		int int_9 = -1;
		BlockState blockState_3 = config.netherBlock;
		BlockState blockState_4 = config.netherBlock;

		for(int int_10 = 127; int_10 >= 0; --int_10) {
			blockPos$Mutable_1.set(localX, int_10, localZ);
			BlockState blockState_5 = chunk.getBlockState(blockPos$Mutable_1);
			if (blockState_5.getBlock() != null && !blockState_5.isAir()) {
				if (blockState_5.getBlock() == blockState_1.getBlock()) {
					if (int_9 == -1) {
						if (int_8 <= 0) {
							blockState_3 = CAVE_AIR;
							blockState_4 = config.netherBlock;
						} else if (int_10 >= int_5 - 4 && int_10 <= int_5 + 1) {
							blockState_3 = config.netherBlock;
							blockState_4 = config.netherBlock;
							if (boolean_2) {
								blockState_3 = GRAVEL;
								blockState_4 = config.netherBlock;
							}

							if (boolean_1) {
								blockState_3 = GLOWSTONE;
								blockState_4 = GLOWSTONE;
							}
						}

						if (int_10 < int_5 && (blockState_3 == null || blockState_3.isAir())) {
							blockState_3 = blockState_2;
						}

						int_9 = int_8;
						if (int_10 >= int_5 - 1) {
							chunk.setBlockState(blockPos$Mutable_1, blockState_3, false);
						} else {
							chunk.setBlockState(blockPos$Mutable_1, blockState_4, false);
						}
					} else if (int_9 > 0) {
						--int_9;
						chunk.setBlockState(blockPos$Mutable_1, blockState_4, false);
					}
				}
			} else {
				int_9 = -1;
			}
		}

	}

	public void initSeed(long long_1) {
		if (this.seed != long_1 || this.noise == null) {
			this.noise = new OctavePerlinNoiseSampler(new ChunkRandom(long_1), 4);
		}

		this.seed = long_1;
	}

	static {
		CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
		GRAVEL = Blocks.GRAVEL.getDefaultState();
		GLOWSTONE = Blocks.SOUL_SAND.getDefaultState();
	}
}