package com.brand.netherthings.features;

import java.util.Random;
import java.util.function.Function;

import com.brand.netherthings.content.OtherBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.CactusFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class NetherCactusFeature extends CactusFeature {
	public NetherCactusFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
	      super(function_1);
	   }

	@Override
		   public boolean method_12853(IWorld iWorld_1, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator_1, Random random_1, BlockPos blockPos_1, DefaultFeatureConfig defaultFeatureConfig_1) {
		      for(int int_1 = 0; int_1 < 10; ++int_1) {
		         BlockPos blockPos_2 = blockPos_1.add(random_1.nextInt(8) - random_1.nextInt(8), random_1.nextInt(4) - random_1.nextInt(4), random_1.nextInt(8) - random_1.nextInt(8));
		         if (iWorld_1.isAir(blockPos_2)) {
		            int int_2 = 1 + random_1.nextInt(random_1.nextInt(3) + 1);
		            

		            for(int int_3 = 0; int_3 < int_2; ++int_3) {
		               if (OtherBlocks.NETHER_CACTUS.getDefaultState().canPlaceAt(iWorld_1, blockPos_2)) {
		                  iWorld_1.setBlockState(blockPos_2.up(int_3), OtherBlocks.NETHER_CACTUS.getDefaultState(), 4);
		               }
		            }
		         }
		      }

		      return true;
		   }
		}