package com.brand.netherthings.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Iterator;
import java.util.Random;

public class GlowingReedsBlock extends Block {
    public static final IntProperty AGE;
    protected static final float field_31258 = 6.0F;
    protected static final VoxelShape SHAPE;


    public GlowingReedsBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }

    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isAir(pos.up())) {
            int i;
            for (i = 1; world.getBlockState(pos.down(i)).isOf(this); ++i) {
            }

            if (i < 3) {
                int j = state.get(AGE);
                if (j == 15) {
                    world.setBlockState(pos.up(), this.getDefaultState());
                    world.setBlockState(pos, state.with(AGE, 0), 4);
                } else {
                    world.setBlockState(pos, state.with(AGE, j + 1), 4);
                }
            }
        }

    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.scheduleBlockTick(pos, this, 1);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.isOf(this)) {
            return true;
        } else {
            if (blockState.isOf(Blocks.NETHERRACK) || blockState.isOf(Blocks.WARPED_NYLIUM) || blockState.isOf(Blocks.CRIMSON_NYLIUM) || blockState.isOf(Blocks.SOUL_SAND) || blockState.isOf(Blocks.SOUL_SOIL) || blockState.isOf(Blocks.MAGMA_BLOCK)) {
                BlockPos blockPos = pos.down();
                Iterator var6 = Direction.Type.HORIZONTAL.iterator();

                while (var6.hasNext()) {
                    Direction direction = (Direction) var6.next();
                    BlockState blockState2 = world.getBlockState(blockPos.offset(direction));
                    FluidState fluidState = world.getFluidState(blockPos.offset(direction));
                    if (fluidState.isIn(FluidTags.LAVA)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    static {
        AGE = Properties.AGE_15;
        SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    }
}

