package com.brand.netherthings.blocks.Crops;

import java.util.Random;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.items.NetherItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
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

	   public BlazingBerryBushBlock(Settings settings) {
		   super(settings);
		   this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AGE, 0));
	   }

	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		return new ItemStack(NetherItems.BLAZING_BERRIES);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if ((Integer)state.get(AGE) == 0) {
			return SMALL_SHAPE;
		} else {
			return (Integer)state.get(AGE) < 3 ? LARGE_SHAPE : super.getOutlineShape(state, world, pos, context);
		}
	}

	public boolean hasRandomTicks(BlockState state) {
		return (Integer)state.get(AGE) < 3;
	}

	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		int i = (Integer)state.get(AGE);
		if (i < 3 && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
			world.setBlockState(pos, (BlockState)state.with(AGE, i + 1), 2);
		}

	}

	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
			entity.slowMovement(state, new Vec3d(0.800000011920929D, 0.75D, 0.800000011920929D));
			if (!world.isClient && (Integer)state.get(AGE) > 0 && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
				double d = Math.abs(entity.getX() - entity.lastRenderX);
				double e = Math.abs(entity.getZ() - entity.lastRenderZ);
				if (d >= 0.003000000026077032D || e >= 0.003000000026077032D) {
					entity.damage(DamageSource.IN_FIRE, 1.0F);
				}
			}

		}
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		int i = (Integer)state.get(AGE);
		boolean bl = i == 3;
		if (!bl && player.getStackInHand(hand).isOf(Items.BONE_MEAL)) {
			return ActionResult.PASS;
		} else if (i > 1) {
			int j = 1 + world.random.nextInt(2);
			dropStack(world, pos, new ItemStack(Items.SWEET_BERRIES, j + (bl ? 1 : 0)));
			world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
			world.setBlockState(pos, (BlockState)state.with(AGE, 1), 2);
			return ActionResult.success(world.isClient);
		} else {
			return super.onUse(state, world, pos, player, hand, hit);
		}
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(new Property[]{AGE});
	}

	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return (Integer)state.get(AGE) < 3;
	}

	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		int i = Math.min(3, (Integer)state.get(AGE) + 1);
		world.setBlockState(pos, (BlockState)state.with(AGE, i), 2);
	}

	static {
		AGE = Properties.AGE_3;
		SMALL_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
		LARGE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	}
}
