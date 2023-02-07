package com.brand.netherthings.blocks.Crops;

import com.brand.netherthings.content.NetherItems;
import net.minecraft.item.ItemConvertible;

public class QuartzCrop extends NetherCropBlock {

    public QuartzCrop(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return NetherItems.QUARTZ_SEEDS;
    }
}