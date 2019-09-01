package com.brand.netherthings.blocks.Crops;

import java.util.Random;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.items.NetherItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlazingBerryBushBlock extends NetherPlantBlock implements Fertilizable {
	   public static final IntProperty AGE;
	   private static final VoxelShape SMALL_SHAPE;
	   private static final VoxelShape LARGE_SHAPE;

	   public BlazingBerryBushBlock(String name, float hardness, float resistance) {
			super(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).ticksRandomly().noCollision().lightLevel(5).strength(hardness, resistance).build());
			Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
	        this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(AGE, 0));
	   }

	   @Environment(EnvType.CLIENT)
	   public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
	      return new ItemStack(NetherItems.BLAZING_BERRIES);
	   }

	   public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
	      if ((Integer)blockState_1.get(AGE) == 0) {
	         return SMALL_SHAPE;
	      } else {
	         return (Integer)blockState_1.get(AGE) < 3 ? LARGE_SHAPE : super.getOutlineShape(blockState_1, blockView_1, blockPos_1, entityContext_1);
	      }
	   }

	   public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
	      super.onScheduledTick(blockState_1, world_1, blockPos_1, random_1);
	      int int_1 = (Integer)blockState_1.get(AGE);
	      if (int_1 < 3 && random_1.nextInt(5) == 0 && world_1.getLightLevel(blockPos_1.up(), 0) >= 2) {
	         world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(AGE, int_1 + 1), 2);
	      }

	   }

	   public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
	      if (entity_1 instanceof LivingEntity && entity_1.getType() != EntityType.FOX) {
	         entity_1.slowMovement(blockState_1, new Vec3d(0.800000011920929D, 0.75D, 0.800000011920929D));
	         if (!world_1.isClient && (Integer)blockState_1.get(AGE) > 0 && (entity_1.prevRenderX != entity_1.x || entity_1.prevRenderZ != entity_1.z)) {
	            double double_1 = Math.abs(entity_1.x - entity_1.prevRenderX);
	            double double_2 = Math.abs(entity_1.z - entity_1.prevRenderZ);
	            if (double_1 >= 0.003000000026077032D || double_2 >= 0.003000000026077032D) {
	               entity_1.damage(DamageSource.IN_FIRE, NetherThings.CONFIG.blazingBerryBushDamage);
	            }
	         }

	      }
	   }

	   public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
	      int int_1 = (Integer)blockState_1.get(AGE);
	      boolean boolean_1 = int_1 == 3;
	      if (!boolean_1 && playerEntity_1.getStackInHand(hand_1).getItem() == Items.BONE_MEAL) {
	         return false;
	      } else if (int_1 > 1) {
	         int int_2 = 1 + world_1.random.nextInt(2);
	         dropStack(world_1, blockPos_1, new ItemStack(NetherItems.BLAZING_BERRIES, int_2 + (boolean_1 ? 1 : 0)));
	         world_1.playSound((PlayerEntity)null, blockPos_1, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world_1.random.nextFloat() * 0.4F);
	         world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(AGE, 1), 2);
	         return true;
	      } else {
	         return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
	      }
	   }

	   protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
	      stateFactory$Builder_1.add(AGE);
	   }

	   public boolean isFertilizable(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1, boolean boolean_1) {
	      return (Integer)blockState_1.get(AGE) < 3;
	   }

	   public boolean canGrow(World world_1, Random random_1, BlockPos blockPos_1, BlockState blockState_1) {
	      return true;
	   }

	   public void grow(World world_1, Random random_1, BlockPos blockPos_1, BlockState blockState_1) {
	      int int_1 = Math.min(3, (Integer)blockState_1.get(AGE) + 1);
	      world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(AGE, int_1), 2);
	   }

	   static {
	      AGE = Properties.AGE_3;
	      SMALL_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	      LARGE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	   }
	}
