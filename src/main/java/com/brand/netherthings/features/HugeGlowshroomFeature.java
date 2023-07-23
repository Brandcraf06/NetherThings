package com.brand.netherthings.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public class HugeGlowshroomFeature extends HugeMushroomFeature {
    public HugeGlowshroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    protected void generateCap(WorldAccess world, Random random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        for(int i = y - 1; i <= y; ++i) {
            int j = i < y ? config.foliageRadius : config.foliageRadius - 1;
            int k = config.foliageRadius - 2;

            for(int l = -j; l <= j; ++l) {
                for(int m = -j; m <= j; ++m) {
                    boolean bl = l == -j;
                    boolean bl2 = l == j;
                    boolean bl3 = m == -j;
                    boolean bl4 = m == j;
                    boolean bl5 = bl || bl2;
                    boolean bl6 = bl3 || bl4;
                    if (i >= y || bl5 != bl6) {
                        mutable.set(start, l, i, m);
                        if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
                            BlockState blockState = config.capProvider.get(random, start);
                            if (blockState.contains(MushroomBlock.WEST) && blockState.contains(MushroomBlock.EAST) && blockState.contains(MushroomBlock.NORTH) && blockState.contains(MushroomBlock.SOUTH) && blockState.contains(MushroomBlock.UP)) {
                                blockState = (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)blockState.with(MushroomBlock.UP, i >= y - 1)).with(MushroomBlock.WEST, l < -k)).with(MushroomBlock.EAST, l > k)).with(MushroomBlock.NORTH, m < -k)).with(MushroomBlock.SOUTH, m > k);
                            }

                            this.setBlockState(world, mutable, blockState);
                        }
                    }
                }
            }
        }

    }

    @Override
    protected boolean canGenerate(WorldAccess world, BlockPos pos, int height, BlockPos.Mutable mutablePos, HugeMushroomFeatureConfig config) {
        int i = pos.getY();
        if (i >= world.getBottomY() + 1 && i + height + 1 < world.getTopY()) {
            BlockState blockState = world.getBlockState(pos.down());
            if (!blockState.isOf(Blocks.NETHERRACK) && !blockState.isOf(Blocks.SOUL_SAND) && !blockState.isOf(Blocks.SOUL_SOIL)) {
                return false;
            } else {
                for(int j = 0; j <= height; ++j) {
                    int k = this.getCapSize(-1, -1, config.foliageRadius, j);

                    for(int l = -k; l <= k; ++l) {
                        for(int m = -k; m <= k; ++m) {
                            BlockState blockState2 = world.getBlockState(mutablePos.set(pos, l, j, m));
                            if (!blockState2.isAir() && !blockState2.isIn(BlockTags.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    protected int getCapSize(int i, int j, int capSize, int y) {
        int k = 0;
        if (y < j && y >= j - 3) {
            k = capSize;
        } else if (y == j) {
            k = capSize;
        }

        return k;
    }
}
