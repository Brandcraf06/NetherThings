package com.brand.netherthings.features;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.IWorld;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

@SuppressWarnings({"unchecked", "rawtypes", "unused"})

public abstract class AbstractNetherTreeFeature<T extends FeatureConfig> extends Feature<T> {
    public AbstractNetherTreeFeature(Function<Dynamic<?>, ? extends T> function_1, boolean boolean_1) {
        super(function_1, boolean_1);
    }

    protected static boolean canTreeReplace(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            Block block_1 = blockState_1.getBlock();
            return blockState_1.isAir() || blockState_1.matches(BlockTags.LEAVES) || block_1 == Blocks.GRASS_BLOCK || Block.isNaturalDirt(block_1) || block_1.matches(BlockTags.LOGS) || block_1.matches(BlockTags.SAPLINGS) || block_1 == Blocks.VINE;
        });
    }

    protected static boolean isAir(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, BlockState::isAir);
    }

    protected static boolean isNaturalDirt(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            return Block.isNaturalDirt(blockState_1.getBlock());
        });
    }

    protected static boolean isWater(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            return blockState_1.getBlock() == Blocks.WATER;
        });
    }

    protected static boolean isLeaves(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            return blockState_1.matches(BlockTags.LEAVES);
        });
    }

    protected static boolean isAirOrLeaves(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            return blockState_1.isAir() || blockState_1.matches(BlockTags.LEAVES);
        });
    }

    protected static boolean isNetherrackOrSoulsand(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            Block block_1 = blockState_1.getBlock();
            return Block.isNaturalDirt(block_1) || block_1 == Blocks.NETHERRACK || block_1 == Blocks.SOUL_SAND;
        });
    }

    protected static boolean isReplaceablePlant(TestableWorld testableWorld_1, BlockPos blockPos_1) {
        return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
            Material material_1 = blockState_1.getMaterial();
            return material_1 == Material.REPLACEABLE_PLANT;
        });
    }

    protected void setToDirt(ModifiableTestableWorld modifiableTestableWorld_1, BlockPos blockPos_1) {
        if (!isNaturalDirt(modifiableTestableWorld_1, blockPos_1)) {
            this.setBlockState(modifiableTestableWorld_1, blockPos_1, Blocks.SOUL_SAND.getDefaultState());
        }

    }

    protected void setBlockState(ModifiableWorld modifiableWorld_1, BlockPos blockPos_1, BlockState blockState_1) {
        this.setBlockStateWithoutUpdatingNeighbors(modifiableWorld_1, blockPos_1, blockState_1);
    }

    protected final void setBlockState(Set<BlockPos> set_1, ModifiableWorld modifiableWorld_1, BlockPos blockPos_1, BlockState blockState_1, MutableIntBoundingBox mutableIntBoundingBox_1) {
        this.setBlockStateWithoutUpdatingNeighbors(modifiableWorld_1, blockPos_1, blockState_1);
        mutableIntBoundingBox_1.setFrom(new MutableIntBoundingBox(blockPos_1, blockPos_1));
        if (BlockTags.LOGS.contains(blockState_1.getBlock())) {
            set_1.add(blockPos_1.toImmutable());
        }

    }

    private void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld modifiableWorld_1, BlockPos blockPos_1, BlockState blockState_1) {
        if (this.emitNeighborBlockUpdates) {
            modifiableWorld_1.setBlockState(blockPos_1, blockState_1, 19);
        } else {
            modifiableWorld_1.setBlockState(blockPos_1, blockState_1, 18);
        }

    }

    public final boolean generate(IWorld iWorld_1, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator_1, Random random_1, BlockPos blockPos_1, T featureConfig_1) {
        Set<BlockPos> set_1 = Sets.newHashSet();
        MutableIntBoundingBox mutableIntBoundingBox_1 = MutableIntBoundingBox.empty();
        boolean boolean_1 = this.generate(set_1, (ModifiableTestableWorld) iWorld_1, random_1, blockPos_1, mutableIntBoundingBox_1);
        if (mutableIntBoundingBox_1.minX > mutableIntBoundingBox_1.maxX) {
            return false;
        } else {
            List<Set<BlockPos>> list_1 = Lists.newArrayList();
            boolean int_1 = true;

            for (int int_2 = 0; int_2 < 6; ++int_2) {
                list_1.add(Sets.newHashSet());
            }

            VoxelSet voxelSet_1 = new BitSetVoxelSet(mutableIntBoundingBox_1.getBlockCountX(), mutableIntBoundingBox_1.getBlockCountY(), mutableIntBoundingBox_1.getBlockCountZ());
            BlockPos.PooledMutable blockPos$PooledMutable_1 = BlockPos.PooledMutable.get();
            Throwable var13 = null;

            try {
                if (boolean_1 && !set_1.isEmpty()) {
                    Iterator var14 = Lists.newArrayList(set_1).iterator();

                    while (var14.hasNext()) {
                        BlockPos blockPos_2 = (BlockPos) var14.next();
                        if (mutableIntBoundingBox_1.contains(blockPos_2)) {
                            voxelSet_1.set(blockPos_2.getX() - mutableIntBoundingBox_1.minX, blockPos_2.getY() - mutableIntBoundingBox_1.minY, blockPos_2.getZ() - mutableIntBoundingBox_1.minZ, true, true);
                        }

                        Direction[] var16 = Direction.values();
                        int var17 = var16.length;

                        for (int var18 = 0; var18 < var17; ++var18) {
                            Direction direction_1 = var16[var18];
                            blockPos$PooledMutable_1.method_10114(blockPos_2).method_10118(direction_1);
                            if (!set_1.contains(blockPos$PooledMutable_1)) {
                                BlockState blockState_1 = iWorld_1.getBlockState(blockPos$PooledMutable_1);
                                if (blockState_1.contains(Properties.DISTANCE_1_7)) {
                                    ((Set) list_1.get(0)).add(blockPos$PooledMutable_1.toImmutable());
                                    this.setBlockStateWithoutUpdatingNeighbors(iWorld_1, blockPos$PooledMutable_1, (BlockState) blockState_1.with(Properties.DISTANCE_1_7, 1));
                                    if (mutableIntBoundingBox_1.contains(blockPos$PooledMutable_1)) {
                                        voxelSet_1.set(blockPos$PooledMutable_1.getX() - mutableIntBoundingBox_1.minX, blockPos$PooledMutable_1.getY() - mutableIntBoundingBox_1.minY, blockPos$PooledMutable_1.getZ() - mutableIntBoundingBox_1.minZ, true, true);
                                    }
                                }
                            }
                        }
                    }
                }

                for (int int_3 = 1; int_3 < 6; ++int_3) {
                    Set<BlockPos> set_2 = list_1.get(int_3 - 1);
                    Set<BlockPos> set_3 = list_1.get(int_3);
                    Iterator var39 = set_2.iterator();

                    while (var39.hasNext()) {
                        BlockPos blockPos_3 = (BlockPos) var39.next();
                        if (mutableIntBoundingBox_1.contains(blockPos_3)) {
                            voxelSet_1.set(blockPos_3.getX() - mutableIntBoundingBox_1.minX, blockPos_3.getY() - mutableIntBoundingBox_1.minY, blockPos_3.getZ() - mutableIntBoundingBox_1.minZ, true, true);
                        }

                        Direction[] var41 = Direction.values();
                        int var42 = var41.length;

                        for (int var21 = 0; var21 < var42; ++var21) {
                            Direction direction_2 = var41[var21];
                            blockPos$PooledMutable_1.method_10114(blockPos_3).method_10118(direction_2);
                            if (!set_2.contains(blockPos$PooledMutable_1) && !set_3.contains(blockPos$PooledMutable_1)) {
                                BlockState blockState_2 = iWorld_1.getBlockState(blockPos$PooledMutable_1);
                                if (blockState_2.contains(Properties.DISTANCE_1_7)) {
                                    int int_4 = blockState_2.get(Properties.DISTANCE_1_7);
                                    if (int_4 > int_3 + 1) {
                                        BlockState blockState_3 = blockState_2.with(Properties.DISTANCE_1_7, int_3 + 1);
                                        this.setBlockStateWithoutUpdatingNeighbors(iWorld_1, blockPos$PooledMutable_1, blockState_3);
                                        if (mutableIntBoundingBox_1.contains(blockPos$PooledMutable_1)) {
                                            voxelSet_1.set(blockPos$PooledMutable_1.getX() - mutableIntBoundingBox_1.minX, blockPos$PooledMutable_1.getY() - mutableIntBoundingBox_1.minY, blockPos$PooledMutable_1.getZ() - mutableIntBoundingBox_1.minZ, true, true);
                                        }

                                        set_3.add(blockPos$PooledMutable_1.toImmutable());
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Throwable var33) {
                var13 = var33;
                throw var33;
            } finally {
                if (blockPos$PooledMutable_1 != null) {
                    if (var13 != null) {
                        try {
                            blockPos$PooledMutable_1.close();
                        } catch (Throwable var32) {
                            var13.addSuppressed(var32);
                        }
                    } else {
                        blockPos$PooledMutable_1.close();
                    }
                }

            }

            Structure.method_20532(iWorld_1, 3, voxelSet_1, mutableIntBoundingBox_1.minX, mutableIntBoundingBox_1.minY, mutableIntBoundingBox_1.minZ);
            return boolean_1;
        }
    }

    protected abstract boolean generate(Set<BlockPos> var1, ModifiableTestableWorld var2, Random var3, BlockPos var4, MutableIntBoundingBox var5);
}
