package com.brand.netherthings.blocks;

import java.util.Iterator;
import java.util.Random;

import com.brand.netherthings.NetherThings;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class GlowingReedsBlock extends Block {
	   public static final IntProperty AGE;
	   protected static final VoxelShape SHAPE;

	   public GlowingReedsBlock(String name, float hardness, float resistance) {
			super(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).collidable(false).lightLevel(7).strength(hardness, resistance).build());
			Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
			Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
		}

	   public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
	      return SHAPE;
	   }

	   public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
	      if (!blockState_1.canPlaceAt(world_1, blockPos_1)) {
	         world_1.breakBlock(blockPos_1, true);
	      } else if (world_1.isAir(blockPos_1.up())) {
	         int int_1;
	         for(int_1 = 1; world_1.getBlockState(blockPos_1.down(int_1)).getBlock() == this; ++int_1) {
	         }

	         if (int_1 < 3) {
	            int int_2 = (Integer)blockState_1.get(AGE);
	            if (int_2 == 15) {
	               world_1.setBlockState(blockPos_1.up(), this.getDefaultState());
	               world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(AGE, 0), 4);
	            } else {
	               world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(AGE, int_2 + 1), 4);
	            }
	         }
	      }

	   }

	   public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
	      if (!blockState_1.canPlaceAt(iWorld_1, blockPos_1)) {
	         iWorld_1.getBlockTickScheduler().schedule(blockPos_1, this, 1);
	      }

	      return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
	   }

	   @SuppressWarnings("rawtypes")
	public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
	      Block block_1 = viewableWorld_1.getBlockState(blockPos_1.down()).getBlock();
	      if (block_1 == this) {
	         return true;
	      } else {
	         if (block_1 == Blocks.NETHERRACK|| block_1 == Blocks.SOUL_SAND || block_1 == Blocks.MAGMA_BLOCK) {
	            BlockPos blockPos_2 = blockPos_1.down();
	            Iterator var6 = Direction.Type.HORIZONTAL.iterator();

	            while(var6.hasNext()) {
	               Direction direction_1 = (Direction)var6.next();
	               FluidState fluidState_1 = viewableWorld_1.getFluidState(blockPos_2.offset(direction_1));
	               if (fluidState_1.matches(FluidTags.LAVA)) {
	                  return true;
	               }
	            }
	         }

	         return false;
	      }
	   }

	   public BlockRenderLayer getRenderLayer() {
	      return BlockRenderLayer.CUTOUT;
	   }

	   protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
	      stateFactory$Builder_1.add(AGE);
	   }

	   static {
	      AGE = Properties.AGE_15;
	      SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	   }
	}

