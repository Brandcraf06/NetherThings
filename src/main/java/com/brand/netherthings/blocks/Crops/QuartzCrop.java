package com.brand.netherthings.blocks.Crops;

import com.brand.netherthings.items.NetherItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemConvertible;

public class QuartzCrop extends NetherCropBlock {
	   
	   public QuartzCrop(String name, float hardness, float resistance) {
		super(name, hardness, resistance);
	}

	   @Environment(EnvType.CLIENT)
	   protected ItemConvertible getSeedsItem() {
	      return NetherItems.QUARTZ_SEEDS;
	  }
    }