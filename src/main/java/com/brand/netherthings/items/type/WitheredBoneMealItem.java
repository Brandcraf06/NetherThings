package com.brand.netherthings.items.type;

import com.brand.netherthings.Nullable;
import com.brand.netherthings.blocks.Crops.Fertilizable.WitherFertilizable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class WitheredBoneMealItem extends Item {
	   public WitheredBoneMealItem(Item.Settings item$Settings_1) {
		      super(item$Settings_1);
		   }

		   public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {
		      World world_1 = itemUsageContext_1.getWorld();
		      BlockPos blockPos_1 = itemUsageContext_1.getBlockPos();
		      BlockPos blockPos_2 = blockPos_1.offset(itemUsageContext_1.getSide());
		      if (useOnFertilizable(itemUsageContext_1.getStack(), world_1, blockPos_1)) {
		         if (!world_1.isClient) {
		            world_1.playLevelEvent(2005, blockPos_1, 0);
		         }

		         return ActionResult.SUCCESS;
		      } else {
		         BlockState blockState_1 = world_1.getBlockState(blockPos_1);
		         boolean boolean_1 = blockState_1.isSideSolidFullSquare(world_1, blockPos_1, itemUsageContext_1.getSide());
		         if (boolean_1 && useOnGround(itemUsageContext_1.getStack(), world_1, blockPos_2, itemUsageContext_1.getSide())) {
		            if (!world_1.isClient) {
		               world_1.playLevelEvent(2005, blockPos_2, 0);
		            }

		            return ActionResult.SUCCESS;
		         } else {
		            return ActionResult.PASS;
		         }
		      }
		   }

		   public static boolean useOnFertilizable(ItemStack itemStack_1, World world_1, BlockPos blockPos_1) {
		      BlockState blockState_1 = world_1.getBlockState(blockPos_1);
		      if (blockState_1.getBlock() instanceof WitherFertilizable) {
		         WitherFertilizable fertilizable_1 = (WitherFertilizable)blockState_1.getBlock();
		         if (fertilizable_1.isFertilizable(world_1, blockPos_1, blockState_1, world_1.isClient)) {
		            if (!world_1.isClient) {
		               if (fertilizable_1.canGrow(world_1, world_1.random, blockPos_1, blockState_1)) {
		                  fertilizable_1.grow(world_1, world_1.random, blockPos_1, blockState_1);
		               }

		               itemStack_1.decrement(1);
		            }

		            return true;
		         }
		      }

		      return false;
		   }

		   public static boolean useOnGround(ItemStack itemStack_1, World world_1, BlockPos blockPos_1, @Nullable Direction direction_1) {
			return false;
		   }

		   @Environment(EnvType.CLIENT)
		   public static void createParticles(IWorld iWorld_1, BlockPos blockPos_1, int int_1) {
		      if (int_1 == 0) {
		         int_1 = 15;
		      }

		      BlockState blockState_1 = iWorld_1.getBlockState(blockPos_1);
		      if (!blockState_1.isAir()) {
		         for(int int_2 = 0; int_2 < int_1; ++int_2) {
		            double double_1 = RANDOM.nextGaussian() * 0.02D;
		            double double_2 = RANDOM.nextGaussian() * 0.02D;
		            double double_3 = RANDOM.nextGaussian() * 0.02D;
		            iWorld_1.addParticle(ParticleTypes.LARGE_SMOKE, (double)((float)blockPos_1.getX() + RANDOM.nextFloat()), (double)blockPos_1.getY() + (double)RANDOM.nextFloat() * blockState_1.getOutlineShape(iWorld_1, blockPos_1).getMaximum(Direction.Axis.Y), (double)((float)blockPos_1.getZ() + RANDOM.nextFloat()), double_1, double_2, double_3);
		         }

		      }
		   }
		}

