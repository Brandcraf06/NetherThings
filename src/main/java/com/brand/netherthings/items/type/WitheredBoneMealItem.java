package com.brand.netherthings.items.type;
import com.brand.netherthings.blocks.Crops.Fertilizable.WitherFertilizable;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class WitheredBoneMealItem extends Item {
	   public WitheredBoneMealItem(Settings settings) {
		   super(settings);
	   }

	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockPos blockPos2 = blockPos.offset(context.getSide());
		if (useOnFertilizable(context.getStack(), world, blockPos)) {
			if (!world.isClient) {
				world.syncWorldEvent(1505, blockPos, 0);
			}

			return ActionResult.success(world.isClient);
		} else {
			BlockState blockState = world.getBlockState(blockPos);
			boolean bl = blockState.isSideSolidFullSquare(world, blockPos, context.getSide());
			if (bl && useOnGround(context.getStack(), world, blockPos2, context.getSide())) {
				if (!world.isClient) {
					world.syncWorldEvent(1505, blockPos2, 0);
				}

				return ActionResult.success(world.isClient);
			} else {
				return ActionResult.PASS;
			}
		}
	}

	public static boolean useOnFertilizable(ItemStack stack, World world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		if (blockState.getBlock() instanceof WitherFertilizable) {
			WitherFertilizable witherfertilizable = (WitherFertilizable)blockState.getBlock();
			if (witherfertilizable.isWitherFertilizable(world, pos, blockState, world.isClient)) {
				if (world instanceof ServerWorld) {
					if (witherfertilizable.canGrow(world, world.random, pos, blockState)) {
						witherfertilizable.grow((ServerWorld)world, world.random, pos, blockState);
					}

					stack.decrement(1);
				}

				return true;
			}
		}

		return false;
	}

	       public static boolean useOnGround(ItemStack stack, World world, BlockPos blockPos, @Nullable Direction facing) {
			return false;
		   }

	     public static void createParticles(WorldAccess world, BlockPos pos, int count) {
		       if (count == 0) {
			count = 15;
		}

			 BlockState blockState = world.getBlockState(pos);
			 if (!blockState.isAir()) {
				 double d = 0.5D;
				 double e;
				 if (blockState.isOpaqueFullCube(world, pos)) {
					 pos = pos.up();
					 count *= 3;
					 d = 3.0D;
					 e = 1.0D;
				 } else {
					 e = blockState.getOutlineShape(world, pos).getMax(Direction.Axis.Y);
				 }

				 world.addParticle(ParticleTypes.LARGE_SMOKE, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
				 Random random = world.getRandom();

				 for(int i = 0; i < count; ++i) {
					 double f = random.nextGaussian() * 0.02D;
					 double g = random.nextGaussian() * 0.02D;
					 double h = random.nextGaussian() * 0.02D;
					 double j = 0.5D - d;
					 double k = (double)pos.getX() + j + random.nextDouble() * d * 2.0D;
					 double l = (double)pos.getY() + random.nextDouble() * e;
					 double m = (double)pos.getZ() + j + random.nextDouble() * d * 2.0D;
					 if (!world.getBlockState((new BlockPos(k, l, m)).down()).isAir()) {
						 world.addParticle(ParticleTypes.LARGE_SMOKE, k, l, m, f, g, h);
					 }
				 }

			 }
		 }
}


