package com.brand.netherthings.blocks.Crops.Fertilizable;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public interface WitherFertilizable {

    boolean isWitherFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient);

    boolean canGrow(World world, Random random, BlockPos pos, BlockState state);

    void grow(World world, Random random, BlockPos pos, BlockState state);
}
