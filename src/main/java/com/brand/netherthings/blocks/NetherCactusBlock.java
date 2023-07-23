package com.brand.netherthings.blocks;

import com.brand.netherthings.content.NetherBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CactusBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Iterator;

public class NetherCactusBlock extends CactusBlock {

    public NetherCactusBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Iterator var4 = Direction.Type.HORIZONTAL.iterator();

        Direction direction;
        BlockState blockState;
        do {
            if (!var4.hasNext()) {
                BlockState blockState2 = world.getBlockState(pos.down());
                return (blockState2.isOf(NetherBlocks.NETHER_CACTUS) || blockState2.isOf(Blocks.NETHERRACK) || blockState2.isOf(Blocks.SOUL_SAND) || blockState2.isOf(Blocks.SOUL_SOIL) || blockState2.isOf(Blocks.MAGMA_BLOCK)) && !world.getBlockState(pos.up()).isLiquid();
            }

            direction = (Direction)var4.next();
            blockState = world.getBlockState(pos.offset(direction));
        } while(!blockState.isSolid() && !world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA));

        return false;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!entity.isFireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            entity.damage(world.getDamageSources().onFire(), 2.0F);
        }
    }
}
