package com.brand.netherthings.blocks;

import java.util.Iterator;
import java.util.Random;

import com.brand.netherthings.NetherThings;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.Material;
import net.minecraft.block.StemBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
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
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class PlowedNetherrackBlock extends Block {
	   public static final IntProperty MOISTURE;
	   protected static final VoxelShape SHAPE;

	   public PlowedNetherrackBlock(String name, float hardness, float resistance) {
			super(FabricBlockSettings.of(Material.STONE).strength(hardness, resistance).build());
			Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
			Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	        this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(MOISTURE, 0));
	   }

	   public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
	      if (direction_1 == Direction.UP && !blockState_1.canPlaceAt(iWorld_1, blockPos_1)) {
	         iWorld_1.getBlockTickScheduler().schedule(blockPos_1, this, 1);
	      }

	      return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
	   }

	   public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
	      BlockState blockState_2 = viewableWorld_1.getBlockState(blockPos_1.up());
	      return !blockState_2.getMaterial().isSolid() || blockState_2.getBlock() instanceof FenceGateBlock;
	   }

	   public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
	      return !this.getDefaultState().canPlaceAt(itemPlacementContext_1.getWorld(), itemPlacementContext_1.getBlockPos()) ? Blocks.NETHERRACK.getDefaultState() : super.getPlacementState(itemPlacementContext_1);
	   }

	   public boolean hasSidedTransparency(BlockState blockState_1) {
	      return true;
	   }

	   public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
	      return SHAPE;
	   }

	   public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
	      if (!blockState_1.canPlaceAt(world_1, blockPos_1)) {
	         setToNetherrack(blockState_1, world_1, blockPos_1);
	      } else {
	         int int_1 = (Integer)blockState_1.get(MOISTURE);
	         if (!isLavaNearby(world_1, blockPos_1)) {
	            if (int_1 > 0) {
	               world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(MOISTURE, int_1 - 1), 2);
	            } else if (!hasCrop(world_1, blockPos_1)) {
	               setToNetherrack(blockState_1, world_1, blockPos_1);
	            }
	         } else if (int_1 < 7) {
	            world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(MOISTURE, 7), 2);
	         }

	      }
	   }

	   public void onLandedUpon(World world_1, BlockPos blockPos_1, Entity entity_1, float float_1) {
	      if (!world_1.isClient && world_1.random.nextFloat() < float_1 - 0.5F && entity_1 instanceof LivingEntity && (entity_1 instanceof PlayerEntity || world_1.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) && entity_1.getWidth() * entity_1.getWidth() * entity_1.getHeight() > 0.512F) {
	         setToNetherrack(world_1.getBlockState(blockPos_1), world_1, blockPos_1);
	      }

	      super.onLandedUpon(world_1, blockPos_1, entity_1, float_1);
	   }

	   public static void setToNetherrack(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
	      world_1.setBlockState(blockPos_1, pushEntitiesUpBeforeBlockChange(blockState_1, Blocks.NETHERRACK.getDefaultState(), world_1, blockPos_1));
	   }

	   private static boolean hasCrop(BlockView blockView_1, BlockPos blockPos_1) {
	      Block block_1 = blockView_1.getBlockState(blockPos_1.up()).getBlock();
	      return block_1 instanceof CropBlock || block_1 instanceof StemBlock || block_1 instanceof AttachedStemBlock;
	   }

	   @SuppressWarnings("rawtypes")
	private static boolean isLavaNearby(ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
	      Iterator var2 = BlockPos.iterate(blockPos_1.add(-4, 0, -4), blockPos_1.add(4, 1, 4)).iterator();

	      BlockPos blockPos_2;
	      do {
	         if (!var2.hasNext()) {
	            return false;
	         }

	         blockPos_2 = (BlockPos)var2.next();
	      } while(!viewableWorld_1.getFluidState(blockPos_2).matches(FluidTags.LAVA));

	      return true;
	   }

	   protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
	      stateFactory$Builder_1.add(MOISTURE);
	   }

	   public boolean canPlaceAtSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, BlockPlacementEnvironment blockPlacementEnvironment_1) {
	      return false;
	   }

	   static {
	      MOISTURE = Properties.MOISTURE;
	      SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
	   }
	}

