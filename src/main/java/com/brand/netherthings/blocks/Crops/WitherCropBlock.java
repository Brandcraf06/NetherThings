package com.brand.netherthings.blocks.Crops;

import java.util.Random;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.blocks.TilledSoulSandBlock;
import com.brand.netherthings.blocks.Crops.Fertilizable.WitherFertilizable;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.items.NetherItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class WitherCropBlock extends NetherPlantBlock implements WitherFertilizable {
	   public static final IntProperty AGE;
	   private static final VoxelShape[] AGE_TO_SHAPE;

	    public WitherCropBlock(String name, float hardness, float resistance) {
			super(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).ticksRandomly().collidable(false).strength(hardness, resistance).build());
			Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
	        this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(this.getAgeProperty(), 0));
	   }

	   public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
	      return AGE_TO_SHAPE[(Integer)blockState_1.get(this.getAgeProperty())];
	   }

	   protected boolean canPlantOnTop(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
	      return blockState_1.getBlock() == OtherBlocks.TILLED_SOUL_SAND;
	   }

	   public IntProperty getAgeProperty() {
	      return AGE;
	   }

	   public int getMaxAge() {
	      return 7;
	   }

	   protected int getAge(BlockState blockState_1) {
	      return (Integer)blockState_1.get(this.getAgeProperty());
	   }

	   public BlockState withAge(int int_1) {
	      return (BlockState)this.getDefaultState().with(this.getAgeProperty(), int_1);
	   }

	   public boolean isMature(BlockState blockState_1) {
	      return (Integer)blockState_1.get(this.getAgeProperty()) >= this.getMaxAge();
	   }

	   public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
	      super.onScheduledTick(blockState_1, world_1, blockPos_1, random_1);
	      if (world_1.getLightLevel(blockPos_1, 0) >= 9) {
	         int int_1 = this.getAge(blockState_1);
	         if (int_1 < this.getMaxAge()) {
	            float float_1 = getAvailableMoisture(this, world_1, blockPos_1);
	            if (random_1.nextInt((int)(25.0F / float_1) + 1) == 0) {
	               world_1.setBlockState(blockPos_1, this.withAge(int_1 + 1), 2);
	            }
	         }
	      }

	   }

	   public void applyGrowth(World world_1, BlockPos blockPos_1, BlockState blockState_1) {
	      int int_1 = this.getAge(blockState_1) + this.getGrowthAmount(world_1);
	      int int_2 = this.getMaxAge();
	      if (int_1 > int_2) {
	         int_1 = int_2;
	      }

	      world_1.setBlockState(blockPos_1, this.withAge(int_1), 2);
	   }

	   protected int getGrowthAmount(World world_1) {
	      return MathHelper.nextInt(world_1.random, 2, 5);
	   }

	   protected static float getAvailableMoisture(Block block_1, BlockView blockView_1, BlockPos blockPos_1) {
	      float float_1 = 1.0F;
	      BlockPos blockPos_2 = blockPos_1.down();

	      for(int int_1 = -1; int_1 <= 1; ++int_1) {
	         for(int int_2 = -1; int_2 <= 1; ++int_2) {
	            float float_2 = 0.0F;
	            BlockState blockState_1 = blockView_1.getBlockState(blockPos_2.add(int_1, 0, int_2));
	            if (blockState_1.getBlock() == OtherBlocks.TILLED_SOUL_SAND) {
	               float_2 = 1.0F;
	               if ((Integer)blockState_1.get(TilledSoulSandBlock.MOISTURE) > 0) {
	                  float_2 = 3.0F;
	               }
	            }

	            if (int_1 != 0 || int_2 != 0) {
	               float_2 /= 4.0F;
	            }

	            float_1 += float_2;
	         }
	      }

	      BlockPos blockPos_3 = blockPos_1.north();
	      BlockPos blockPos_4 = blockPos_1.south();
	      BlockPos blockPos_5 = blockPos_1.west();
	      BlockPos blockPos_6 = blockPos_1.east();
	      boolean boolean_1 = block_1 == blockView_1.getBlockState(blockPos_5).getBlock() || block_1 == blockView_1.getBlockState(blockPos_6).getBlock();
	      boolean boolean_2 = block_1 == blockView_1.getBlockState(blockPos_3).getBlock() || block_1 == blockView_1.getBlockState(blockPos_4).getBlock();
	      if (boolean_1 && boolean_2) {
	         float_1 /= 2.0F;
	      } else {
	         boolean boolean_3 = block_1 == blockView_1.getBlockState(blockPos_5.north()).getBlock() || block_1 == blockView_1.getBlockState(blockPos_6.north()).getBlock() || block_1 == blockView_1.getBlockState(blockPos_6.south()).getBlock() || block_1 == blockView_1.getBlockState(blockPos_5.south()).getBlock();
	         if (boolean_3) {
	            float_1 /= 2.0F;
	         }
	      }

	      return float_1;
	   }

	   public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
	      return (viewableWorld_1.getLightLevel(blockPos_1, 0) >= 8 || !viewableWorld_1.isSkyVisible(blockPos_1)) && super.canPlaceAt(blockState_1, viewableWorld_1, blockPos_1);
	   }

	   public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
	      if (entity_1 instanceof RavagerEntity && world_1.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
	         world_1.breakBlock(blockPos_1, true);
	      }

	      super.onEntityCollision(blockState_1, world_1, blockPos_1, entity_1);
	      
	      if (!world_1.isClient && world_1.getDifficulty() != Difficulty.PEACEFUL) {
	             if (entity_1 instanceof LivingEntity) {
	                LivingEntity livingEntity_1 = (LivingEntity)entity_1;
	                if (!livingEntity_1.isInvulnerableTo(DamageSource.WITHER)) {
	                   livingEntity_1.addPotionEffect(new StatusEffectInstance(StatusEffects.WITHER, 40));
	   }
	  }
	 }
	}

	   @Environment(EnvType.CLIENT)
	   public void randomDisplayTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
	      VoxelShape voxelShape_1 = this.getOutlineShape(blockState_1, world_1, blockPos_1, EntityContext.absent());
	      Vec3d vec3d_1 = voxelShape_1.getBoundingBox().getCenter();
	      double double_1 = (double)blockPos_1.getX() + vec3d_1.x;
	      double double_2 = (double)blockPos_1.getZ() + vec3d_1.z;

	      for(int int_1 = 0; int_1 < 3; ++int_1) {
	         if (random_1.nextBoolean()) {
	            world_1.addParticle(ParticleTypes.LARGE_SMOKE, double_1 + (double)(random_1.nextFloat() / 5.0F), (double)blockPos_1.getY() + (0.5D - (double)random_1.nextFloat()), double_2 + (double)(random_1.nextFloat() / 5.0F), 0.0D, 0.0D, 0.0D);
	         }
	      }

	   }

	   @Environment(EnvType.CLIENT)
	   protected ItemConvertible getSeedsItem() {
	      return NetherItems.WITHER_SEEDS;
	   }

	   @Environment(EnvType.CLIENT)
	   public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
	      return new ItemStack(this.getSeedsItem());
	   }

	   public boolean isFertilizable(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1, boolean boolean_1) {
	      return !this.isMature(blockState_1);
	   }

	   public boolean canGrow(World world_1, Random random_1, BlockPos blockPos_1, BlockState blockState_1) {
	      return true;
	   }

	   public void grow(World world_1, Random random_1, BlockPos blockPos_1, BlockState blockState_1) {
	      this.applyGrowth(world_1, blockPos_1, blockState_1);
	   }

	   protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
	      stateFactory$Builder_1.add(AGE);
	   }

	   static {
	      AGE = Properties.AGE_7;
	      AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
	   }
	}
