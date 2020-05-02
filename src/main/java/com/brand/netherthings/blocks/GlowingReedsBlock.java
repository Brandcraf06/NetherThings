package com.brand.netherthings.blocks;

import java.util.Iterator;
import java.util.Random;

import com.brand.netherthings.NetherThings;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
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
import net.minecraft.world.WorldView;

public class GlowingReedsBlock extends Block {
	   public static final IntProperty AGE;
	   protected static final VoxelShape SHAPE;

	   public GlowingReedsBlock(String name, float hardness, float resistance) {
			super(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GLASS).ticksRandomly().noCollision().breakInstantly().lightLevel(7).strength(hardness, resistance));
			Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
			Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
		    this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
	   }

	   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
	      return SHAPE;
	   }

	   public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
	      if (!state.canPlaceAt(world, pos)) {
	         world.breakBlock(pos, true);
	      }

	   }

	   public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
	      if (world.isAir(pos.up())) {
	         int i;
	         for(i = 1; world.getBlockState(pos.down(i)).isOf(this); ++i) {
	         }

	         if (i < 3) {
	            int j = (Integer)state.get(AGE);
	            if (j == 15) {
	               world.setBlockState(pos.up(), this.getDefaultState());
	               world.setBlockState(pos, (BlockState)state.with(AGE, 0), 4);
	            } else {
	               world.setBlockState(pos, (BlockState)state.with(AGE, j + 1), 4);
	            }
	         }
	      }

	   }

	   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos posFrom) {
	      if (!state.canPlaceAt(world, pos)) {
	         world.getBlockTickScheduler().schedule(pos, this, 1);
	      }

	      return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
	   }

	   @SuppressWarnings("rawtypes")
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
	      BlockState blockState = world.getBlockState(pos.down());
	      if (blockState.getBlock() == this) {
	         return true;
	      } else {
	         if (blockState.isOf(Blocks.NETHERRACK) || blockState.isOf(Blocks.WARPED_NYLIUM) || blockState.isOf(Blocks.CRIMSON_NYLIUM) || blockState.isOf(Blocks.SOUL_SAND) || blockState.isOf(Blocks.SOUL_SOIL) || blockState.isOf(Blocks.MAGMA_BLOCK)) {
	            BlockPos blockPos = pos.down();
	            Iterator var6 = Direction.Type.HORIZONTAL.iterator();

	            while(var6.hasNext()) {
	               Direction direction = (Direction)var6.next();
	               FluidState fluidState = world.getFluidState(blockPos.offset(direction));
	               if (fluidState.matches(FluidTags.LAVA)) {
	                  return true;
	               }
	            }
	         }

	         return false;
	      }
	   }

	   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
	      builder.add(AGE);
	   }

	   static {
	      AGE = Properties.AGE_15;
	      SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	   }
	}
