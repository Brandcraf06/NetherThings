package com.brand.netherthings.blocks;

import java.util.Queue;
import java.util.Random;

import com.brand.netherthings.NetherThings;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class BaseWetSpongeBlock extends Block {

	public BaseWetSpongeBlock(String name, float hardness, float resistance) {
		super(FabricBlockSettings.of(Material.LEAVES).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.PICKAXES, 0).strength(hardness, resistance).build());
		Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
		Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().stackSize(64).itemGroup(ItemGroup.DECORATIONS)));
	}

	   @Environment(EnvType.CLIENT)
	   public void randomDisplayTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
	      Direction direction_1 = Direction.random(random_1);
	      if (direction_1 != Direction.UP) {
	         BlockPos blockPos_2 = blockPos_1.offset(direction_1);
	         BlockState blockState_2 = world_1.getBlockState(blockPos_2);
	         if (!blockState_1.isFullBoundsCubeForCulling() || !Block.isSolidFullSquare(blockState_2, world_1, blockPos_2, direction_1.getOpposite())) {
	            double double_1 = (double)blockPos_1.getX();
	            double double_2 = (double)blockPos_1.getY();
	            double double_3 = (double)blockPos_1.getZ();
	            if (direction_1 == Direction.DOWN) {
	               double_2 -= 0.05D;
	               double_1 += random_1.nextDouble();
	               double_3 += random_1.nextDouble();
	            } else {
	               double_2 += random_1.nextDouble() * 0.8D;
	               if (direction_1.getAxis() == Direction.Axis.X) {
	                  double_3 += random_1.nextDouble();
	                  if (direction_1 == Direction.EAST) {
	                     ++double_1;
	                  } else {
	                     double_1 += 0.05D;
	                  }
	               } else {
	                  double_1 += random_1.nextDouble();
	                  if (direction_1 == Direction.SOUTH) {
	                     ++double_3;
	                  } else {
	                     double_3 += 0.05D;
	                  }
	               }
	            }

	            world_1.addParticle(ParticleTypes.DRIPPING_LAVA, double_1, double_2, double_3, 0.0D, 0.0D, 0.0D);
	         }
	      }
	   }
	}
