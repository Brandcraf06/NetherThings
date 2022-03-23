package com.brand.netherthings.world.surfacebuilder;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;

import java.util.Random;
import java.util.function.Function;

public class CondemnedBarrensSurfaceBuilder extends NetherThingsSurfaceBuilder {

    public CondemnedBarrensSurfaceBuilder(Function<Dynamic<?>, ? extends NetherThingsSurfaceConfig> function_1) {
        super(function_1);
    }

    public static final BlockState SOULSAND;

    @Override
    public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int int_3, double double_1, BlockState blockState_1, BlockState blockState_2, int int_4, long seed, NetherThingsSurfaceConfig config) {
        int int_5 = int_4 + 1;
        int localX = x & 15;
        int localZ = z & 15;

        boolean boolean_1 = this.noise.sample((double) x * 0.03125D, (double) z * 0.03125D, 0.0D) + rand.nextDouble() * 0.2D > 0.0D;
        boolean boolean_2 = this.noise.sample((double) x * 0.03125D, 109.0D, (double) z * 0.03125D) + rand.nextDouble() * 0.2D > 0.0D;
        int int_8 = (int) (double_1 / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        BlockPos.Mutable pos = new BlockPos.Mutable();
        int int_9 = -1;
        BlockState blockState_3 = config.netherBlock;
        BlockState blockState_4 = config.netherBlock;

        for (int y = 127; y >= 0; --y) {
            pos.set(localX, y, localZ);
            BlockState blockState_5 = chunk.getBlockState(pos);
            if (blockState_5.getBlock() != null && !blockState_5.isAir()) {
                if (blockState_5.getBlock() == blockState_1.getBlock()) {
                    if (int_9 == -1) {
                        if (int_8 <= 0) {
                            blockState_3 = CAVE_AIR;
                            blockState_4 = config.netherBlock;
                        } else if (y >= int_5 - 1 && y <= int_5 + 4) {
                            blockState_3 = config.netherBlock;
                            blockState_4 = config.netherBlock;
                            if (boolean_2) {
                                blockState_3 = GRAVEL;
                                blockState_4 = config.netherBlock;
                            }

                            if (boolean_1) {
                                blockState_3 = GLOWSTONE;
                                blockState_4 = GLOWSTONE;
                            }
                        }

                        if (y < int_5 && (blockState_3 == null || blockState_3.isAir())) {
                            blockState_3 = blockState_2;
                        }

                        int_9 = int_8;
                        if (y >= int_5 - 1) {
                            setBlockState(chunk, pos, blockState_3);
                        } else {
                            setBlockState(chunk, pos, blockState_4);
                        }
                    } else if (int_9 > 0) {
                        --int_9;
                        setBlockState(chunk, pos, blockState_4);
                    }
                }
            } else {
                int_9 = -1;
            }
        }

        BlockPos.Mutable pos2 = new BlockPos.Mutable(localX, 127, localZ);

        // Create soul sand cover
        int run = -1;
        int offset = 2 + rand.nextInt(3);
        for (int y = 127; y >= 0; --y) {
            pos2.setY(y);
            ++run;

            BlockState state2 = chunk.getBlockState(pos2);
            if (state2 == NETHERRACK) {
                if (run < offset) {
                    setBlockState(chunk, pos2, SOULSAND);
                }
            } else {
                run = -1;
            }
        }

    }

    private BlockState setBlockState(Chunk chunk, Mutable pos, final BlockState state) {
        BlockState setState = state;

        chunk.setBlockState(pos, setState, false);

        return setState;
    }

    public void initSeed(long long_1) {
        if (this.seed != long_1 || this.noise == null) {
            this.noise = new OctavePerlinNoiseSampler(new ChunkRandom(long_1), 4);
        }

        this.seed = long_1;
    }

    static {
        SOULSAND = Blocks.SOUL_SAND.getDefaultState();
    }
}
