package com.brand.netherthings.features;

import java.util.Random;
import java.util.function.Function;

import com.brand.netherthings.content.OtherBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.ReedFeature;

public class GlowingReedsFeature extends ReedFeature {   
	public GlowingReedsFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
	      super(function_1);
	   }

	@Override
	public boolean method_13782(IWorld iWorld_1, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator_1, Random random_1, BlockPos blockPos_1, DefaultFeatureConfig defaultFeatureConfig_1) {
	      int int_1 = 0;

	      for(int int_2 = 0; int_2 < 20; ++int_2) {
	         BlockPos blockPos_2 = blockPos_1.add(random_1.nextInt(4) - random_1.nextInt(4), 0, random_1.nextInt(4) - random_1.nextInt(4));
	         if (iWorld_1.isAir(blockPos_2)) {
	            BlockPos blockPos_3 = blockPos_2.down();
	            if (iWorld_1.getFluidState(blockPos_3.west()).matches(FluidTags.LAVA) || iWorld_1.getFluidState(blockPos_3.east()).matches(FluidTags.LAVA) || iWorld_1.getFluidState(blockPos_3.north()).matches(FluidTags.LAVA) || iWorld_1.getFluidState(blockPos_3.south()).matches(FluidTags.LAVA)) {
	               int int_3 = 2 + random_1.nextInt(random_1.nextInt(3) + 1);

	               for(int int_4 = 0; int_4 < int_3; ++int_4) {
	                  if (OtherBlocks.GLOWING_REEDS.getDefaultState().canPlaceAt(iWorld_1, blockPos_2)) {
	                     iWorld_1.setBlockState(blockPos_2.up(int_4), OtherBlocks.GLOWING_REEDS.getDefaultState(), 2);
	                     ++int_1;
	                  }
	               }
	            }
	         }
	      }

	      return int_1 > 0;
	   }
	}