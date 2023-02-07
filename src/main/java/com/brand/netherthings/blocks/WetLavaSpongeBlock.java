package com.brand.netherthings.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class WetLavaSpongeBlock extends Block {

    public WetLavaSpongeBlock(Settings settings) {
        super(settings);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        Direction direction = Direction.random(random);
        if (direction != Direction.UP) {
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
                double d = (double)pos.getX();
                double e = (double)pos.getY();
                double f = (double)pos.getZ();
                if (direction == Direction.DOWN) {
                    e -= 0.05D;
                    d += random.nextDouble();
                    f += random.nextDouble();
                } else {
                    e += random.nextDouble() * 0.8D;
                    if (direction.getAxis() == Direction.Axis.X) {
                        f += random.nextDouble();
                        if (direction == Direction.EAST) {
                            ++d;
                        } else {
                            d += 0.05D;
                        }
                    } else {
                        d += random.nextDouble();
                        if (direction == Direction.SOUTH) {
                            ++f;
                        } else {
                            f += 0.05D;
                        }
                    }
                }

                world.addParticle(ParticleTypes.DRIPPING_LAVA, d, e, f, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
