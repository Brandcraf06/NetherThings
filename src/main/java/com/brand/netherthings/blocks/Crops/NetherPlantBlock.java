package com.brand.netherthings.blocks.Crops;

import com.brand.netherthings.content.OtherBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class NetherPlantBlock extends Block {
	   protected NetherPlantBlock(Block.Settings block$Settings_1) {
		      super(block$Settings_1);
		   }

		   protected boolean canPlantOnTop(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
		      Block block_1 = blockState_1.getBlock();
		      return block_1 == Blocks.NETHERRACK || block_1 == Blocks.SOUL_SAND || block_1 == Blocks.MAGMA_BLOCK || block_1 == OtherBlocks.BLAZING_NETHERRACK || block_1 == OtherBlocks.TILLED_SOUL_SAND;
		   }

		   public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
		      return !blockState_1.canPlaceAt(iWorld_1, blockPos_1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
		   }

		   public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
		      BlockPos blockPos_2 = blockPos_1.down();
		      return this.canPlantOnTop(viewableWorld_1.getBlockState(blockPos_2), viewableWorld_1, blockPos_2);
		   }

		   public BlockRenderLayer getRenderLayer() {
		      return BlockRenderLayer.CUTOUT;
		   }

		   public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
		      return true;
		   }
		}
