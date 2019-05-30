package com.brand.netherthings.blocks;

import java.util.Queue;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.OtherBlocks;
import com.google.common.collect.Lists;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class BaseSpongeBlock extends Block {

	public BaseSpongeBlock(String name, float hardness, float resistance) {
		super(FabricBlockSettings.of(Material.LEAVES).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.PICKAXES, 0).strength(hardness, resistance).build());
		Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
		Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().stackSize(64).itemGroup(ItemGroup.DECORATIONS)));
	}

	   public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
	      if (blockState_2.getBlock() != blockState_1.getBlock()) {
	         this.update(world_1, blockPos_1);
	      }
	   }

	   public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2, boolean boolean_1) {
	      this.update(world_1, blockPos_1);
	      super.neighborUpdate(blockState_1, world_1, blockPos_1, block_1, blockPos_2, boolean_1);
	   }

	   protected void update(World world_1, BlockPos blockPos_1) {
	      if (this.absorbWater(world_1, blockPos_1)) {
	         world_1.setBlockState(blockPos_1, OtherBlocks.WET_LAVA_SPONGE.getDefaultState(), 2);
	         world_1.playLevelEvent(2001, blockPos_1, Block.getRawIdFromState(Blocks.LAVA.getDefaultState()));
	      }

	   }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean absorbWater(World world_1, BlockPos blockPos_1) {
	      Queue<Pair<BlockPos, Integer>> queue_1 = Lists.newLinkedList();
	      queue_1.add(new Pair(blockPos_1, 0));
	      int int_1 = 0;

	      while(!queue_1.isEmpty()) {
	         Pair<BlockPos, Integer> pair_1 = (Pair)queue_1.poll();
	         BlockPos blockPos_2 = (BlockPos)pair_1.getLeft();
	         int int_2 = (Integer)pair_1.getRight();
	         Direction[] var8 = Direction.values();
	         int var9 = var8.length;

	         for(int var10 = 0; var10 < var9; ++var10) {
	            Direction direction_1 = var8[var10];
	            BlockPos blockPos_3 = blockPos_2.offset(direction_1);
	            BlockState blockState_1 = world_1.getBlockState(blockPos_3);
	            FluidState fluidState_1 = world_1.getFluidState(blockPos_3);
	            if (fluidState_1.matches(FluidTags.LAVA)) {
	               if (blockState_1.getBlock() instanceof FluidDrainable && ((FluidDrainable)blockState_1.getBlock()).tryDrainFluid(world_1, blockPos_3, blockState_1) != Fluids.EMPTY) {
	                  ++int_1;
	                  if (int_2 < 1000) {
	                     queue_1.add(new Pair(blockPos_3, int_2 + 1));
	                  }
	               } else if (blockState_1.getBlock() instanceof FluidBlock) {
	                  world_1.setBlockState(blockPos_3, Blocks.AIR.getDefaultState(), 3);
	                  ++int_1;
	                  if (int_2 < 1000) {
	                     queue_1.add(new Pair(blockPos_3, int_2 + 1));
	                  }
	               }
	            }
	         }

	         if (int_1 > 3000) {
	            break;
	         }
	      }

	      return int_1 > 0;
	   }
	}
