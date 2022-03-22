package com.brand.netherthings.blocks.Crops;

import com.brand.netherthings.items.NetherItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;

public class QuartzCrop extends NetherCropBlock {
	   
	   public QuartzCrop(Settings settings) {
		   super(settings);
	}

	@Override
	protected ItemConvertible getSeedsItem() {
		return NetherItems.QUARTZ_SEEDS;
	}
}