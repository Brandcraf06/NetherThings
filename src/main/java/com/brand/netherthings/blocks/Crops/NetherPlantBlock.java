package com.brand.netherthings.blocks.Crops;

import com.brand.netherthings.content.NetherBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class NetherPlantBlock extends PlantBlock {
    protected NetherPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.NETHERRACK) || floor.isOf(Blocks.SOUL_SAND) || floor.isOf(Blocks.MAGMA_BLOCK) || floor.isOf(NetherBlocks.BLAZING_NETHERRACK) || floor.isOf(NetherBlocks.TILLED_SOUL_SAND);
    }
}