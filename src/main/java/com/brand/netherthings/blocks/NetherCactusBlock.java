package com.brand.netherthings.blocks;

import java.util.Iterator;
import java.util.Random;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.OtherBlocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class NetherCactusBlock extends Block implements Tickable {
	   public static final IntProperty AGE;
	   protected static final VoxelShape COLLISION_SHAPE;
	   protected static final VoxelShape OUTLINE_SHAPE;
            
public NetherCactusBlock(String name, float hardness, float resistance) {
		super(FabricBlockSettings.of(Material.CACTUS).sounds(BlockSoundGroup.WOOL).strength(hardness, resistance).ticksRandomly().build());
		Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
		Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));

		    }

	   protected NetherCactusBlock(Block.Settings block$Settings_1) {
	      super(block$Settings_1);
	      this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(AGE, 0));
	   }

	   public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
	      if (!blockState_1.canPlaceAt(world_1, blockPos_1)) {
	         world_1.breakBlock(blockPos_1, true);
	      } else {
	         BlockPos blockPos_2 = blockPos_1.up();
	         if (world_1.isAir(blockPos_2)) {
	            int int_1;
	            for(int_1 = 1; world_1.getBlockState(blockPos_1.down(int_1)).getBlock() == this; ++int_1) {
	            }

	            if (int_1 < 3) {
	               int int_2 = (Integer)blockState_1.get(AGE);
	               if (int_2 == 15) {
	                  world_1.setBlockState(blockPos_2, this.getDefaultState());
	                  BlockState blockState_2 = (BlockState)blockState_1.with(AGE, 0);
	                  world_1.setBlockState(blockPos_1, blockState_2, 4);
	                  blockState_2.neighborUpdate(world_1, blockPos_2, this, blockPos_1, false);
	               } else {
	                  world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(AGE, int_2 + 1), 4);
	               }

	            }
	         }
	      }
	   }

	   public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
	      return COLLISION_SHAPE;
	   }

	   public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
	      return OUTLINE_SHAPE;
	   }

	   public boolean isFullBoundsCubeForCulling(BlockState blockState_1) {
	      return true;
	   }

	   public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
	      if (!blockState_1.canPlaceAt(iWorld_1, blockPos_1)) {
	         iWorld_1.getBlockTickScheduler().schedule(blockPos_1, this, 1);
	      }

	      return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
	   }

	   @SuppressWarnings("rawtypes")
	public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
		Iterator var4 = Direction.Type.HORIZONTAL.iterator();

	      Direction direction_1;
	      Material material_1;
	      do {
	         if (!var4.hasNext()) {
	            Block block_1 = viewableWorld_1.getBlockState(blockPos_1.down()).getBlock();
	            return (block_1 == OtherBlocks.NETHER_CACTUS || block_1 == Blocks.NETHERRACK || block_1 == Blocks.SOUL_SAND || block_1 == Blocks.MAGMA_BLOCK) && !viewableWorld_1.getBlockState(blockPos_1.up()).getMaterial().isLiquid();
	         }

	         direction_1 = (Direction)var4.next();
	         BlockState blockState_2 = viewableWorld_1.getBlockState(blockPos_1.offset(direction_1));
	         material_1 = blockState_2.getMaterial();
	      } while(!material_1.isSolid() && !viewableWorld_1.getFluidState(blockPos_1.offset(direction_1)).matches(FluidTags.LAVA));

	      return false;
	   }

	   public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
	      entity_1.damage(DamageSource.ON_FIRE, NetherThings.CONFIG.netherCactusDamage);
	   }

	   public BlockRenderLayer getRenderLayer() {
	      return BlockRenderLayer.CUTOUT;
	   }

	   protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
	      stateFactory$Builder_1.add(AGE);
	   }

	   public boolean canPlaceAtSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, BlockPlacementEnvironment blockPlacementEnvironment_1) {
	      return false;
	   }

	   static {
	      AGE = Properties.AGE_15;
	      COLLISION_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
	      OUTLINE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	   }

	@Override
	public void tick() {
		
	     }
	}

