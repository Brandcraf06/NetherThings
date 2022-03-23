package com.brand.netherthings.world.surfacebuilder;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

public class NetherThingsSurfaceConfig implements SurfaceConfig {
    public final BlockState netherBlock;

    public NetherThingsSurfaceConfig(BlockState netherrackBlock) {
        this.netherBlock = netherrackBlock;
    }

    public BlockState getTopMaterial() {
        return this.netherBlock;
    }

    public BlockState getUnderMaterial() {
        return this.netherBlock;
    }

    public BlockState getUnderwaterMaterial() {
        return this.netherBlock;
    }

    public static NetherThingsSurfaceConfig deserialize(Dynamic<?> dynamic_1) {
        BlockState blockState_1 = (BlockState) dynamic_1.get("nether_material").map(BlockState::deserialize).orElse(Blocks.NETHERRACK.getDefaultState());
        return new NetherThingsSurfaceConfig(blockState_1);
    }
}
