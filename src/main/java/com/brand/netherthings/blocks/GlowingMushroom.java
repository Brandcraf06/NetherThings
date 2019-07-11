package com.brand.netherthings.blocks;

import java.util.Random;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.features.NetherThingsFeatures;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlantedFeatureConfig;

public class GlowingMushroom extends MushroomPlantBlock { 
	
	public GlowingMushroom(String name, float hardness, float resistance) {
		super(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).lightLevel(7).strength(hardness, resistance).build());
		Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
		Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.GROUP)));
	}
	@Override
	public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
	      BlockPos blockPos_2 = blockPos_1.down();
	      BlockState blockState_2 = viewableWorld_1.getBlockState(blockPos_2);
	      Block block_1 = blockState_2.getBlock();
	      if (block_1 != Blocks.NETHERRACK && block_1 != Blocks.SOUL_SAND) {
	         return viewableWorld_1.getLightLevel(blockPos_1, 0) < 13 && this.canPlantOnTop(blockState_2, viewableWorld_1, blockPos_2);
	      } else {
	         return true;
	      }
	   }
	
	@Override
	   public boolean trySpawningBigMushroom(IWorld iWorld_1, BlockPos blockPos_1, BlockState blockState_1, Random random_1) {
	      iWorld_1.clearBlockState(blockPos_1, false);
	      Feature<PlantedFeatureConfig> feature_1 = null;
	      if (this == OtherBlocks.GREEN_GLOWING_MUSHROOM) {
	         feature_1 = NetherThingsFeatures.HUGE_GREEN_GLOWING_MUSHROOM;
	      } else if (this == OtherBlocks.BLUE_GLOWING_MUSHROOM) {
	          feature_1 = NetherThingsFeatures.HUGE_BLUE_GLOWING_MUSHROOM;
	      } else if (this == OtherBlocks.PURPLE_GLOWING_MUSHROOM) {
	          feature_1 = NetherThingsFeatures.HUGE_PURPLE_GLOWING_MUSHROOM;
	      }

	      if (feature_1 != null && feature_1.generate(iWorld_1, iWorld_1.getChunkManager().getChunkGenerator(), random_1, blockPos_1, new PlantedFeatureConfig(true))) {
	          return true;
	       } else {
	          iWorld_1.setBlockState(blockPos_1, blockState_1, 3);
	          return false;
	      }
	   }
     }

