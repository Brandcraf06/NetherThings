package com.brand.netherthings.blocks;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.OtherBlocks;

import com.brand.netherthings.contentNew.NetherBlocks;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DeadGrassBlock extends PlantBlock {
	protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	
	public DeadGrassBlock(Settings settings) {
		super(settings);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.isOf(NetherBlocks.BURNT_GRASS_BLOCK) || floor.isIn(BlockTags.DIRT);
	}
}
