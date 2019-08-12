package com.brand.netherthings.features;

import java.util.Random;
import java.util.function.Function;

import com.brand.netherthings.content.OtherBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.HugeRedMushroomFeature;
import net.minecraft.world.gen.feature.PlantedFeatureConfig;
public class HugeGreenGlowingMushroomFeature extends HugeRedMushroomFeature {
	public HugeGreenGlowingMushroomFeature(Function<Dynamic<?>, ? extends PlantedFeatureConfig> function_1) {
		super(function_1);
	}
	@Override
	public boolean method_13398(IWorld iWorld_1, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator_1, Random random_1, BlockPos startPos, PlantedFeatureConfig defaultFeatureConfig_1) {
		BlockPos blockPos_1 = startPos;
		int height = random_1.nextInt(3) + 4;
		if (random_1.nextInt(12) == 0) {
			height *= 2;
		}
		int y = blockPos_1.getY();
		
		if (y >= 4 && y + height + 1 < 256) {

			int attemptsRemaining = y < 9 ? y - 3 : 8;
			int shiftDown = 1;

			Block block_01 = iWorld_1.getBlockState(blockPos_1.down()).getBlock();
			if (block_01 != Blocks.NETHERRACK && block_01 != Blocks.SOUL_SAND && block_01 != Blocks.GRASS_BLOCK) {
				while (attemptsRemaining > 0) {
					attemptsRemaining--;
					shiftDown++;

					Block block_3 = iWorld_1.getBlockState(blockPos_1.down(shiftDown)).getBlock();

					if (block_3 == Blocks.NETHERRACK || block_3 == Blocks.SOUL_SAND || block_3 == Blocks.GRASS_BLOCK) {
						blockPos_1 = startPos.down(shiftDown);
						break;
					}
					if (attemptsRemaining == 0) {
						return false;
					}
				}
			}

			y = blockPos_1.getY();
			BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();

			int int_8;
			for(int int_3 = 0; int_3 <= height; ++int_3) {
				int int_4 = 0;
				if (int_3 < height && int_3 >= height - 3) {
					int_4 = 2;
				} else if (int_3 == height) {
					int_4 = 1;
				}

				for(int_8 = -int_4; int_8 <= int_4; ++int_8) {
					for(int int_6 = -int_4; int_6 <= int_4; ++int_6) {
						BlockState blockState_1 = iWorld_1.getBlockState(blockPos$Mutable_1.set((Vec3i)blockPos_1).setOffset(int_8, int_3, int_6));
						if (!blockState_1.isAir() && !blockState_1.matches(BlockTags.LEAVES)) {
							return false;
						}
					}
				}
			}

			BlockState blockState_2 = (BlockState)OtherBlocks.GREEN_GLOWING_MUSHROOM_BLOCK.getDefaultState();

			for(int int_7 = height - 1; int_7 <= height; ++int_7) {
				int_8 = int_7 < height ? 3 : 2;

				for(int int_10 = -int_8; int_10 <= int_8; ++int_10) {
					for(int int_11 = -int_8; int_11 <= int_8; ++int_11) {
						boolean boolean_1 = int_10 == -int_8;
						boolean boolean_2 = int_10 == int_8;
						boolean boolean_3 = int_11 == -int_8;
						boolean boolean_4 = int_11 == int_8;
						boolean boolean_5 = boolean_1 || boolean_2;
						boolean boolean_6 = boolean_3 || boolean_4;
						if (int_7 >= height || boolean_5 != boolean_6) {
							blockPos$Mutable_1.set((Vec3i)blockPos_1).setOffset(int_10, int_7, int_11);
							if (!iWorld_1.getBlockState(blockPos$Mutable_1).isFullOpaque(iWorld_1, blockPos$Mutable_1)) {
								this.setBlockState(iWorld_1, blockPos$Mutable_1, blockState_2);
							}
						}
					}
				}
			}

			BlockState blockState_3 = (BlockState)((BlockState)Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false)).with(MushroomBlock.DOWN, false);

			for(int_8 = 0; int_8 < height; ++int_8) {
				blockPos$Mutable_1.set((Vec3i)blockPos_1).setOffset(Direction.UP, int_8);
				if (!iWorld_1.getBlockState(blockPos$Mutable_1).isFullOpaque(iWorld_1, blockPos$Mutable_1)) {
					this.setBlockState(iWorld_1, blockPos$Mutable_1, blockState_3);
				}
			}

			return true;
		} else {
			return false;
		}
	}
}