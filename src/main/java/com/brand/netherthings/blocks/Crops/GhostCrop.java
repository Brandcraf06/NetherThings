package com.brand.netherthings.blocks.Crops;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;

public class GhostCrop extends NetherCropBlock {
	   
	   public GhostCrop(String name, float hardness, float resistance) {
		super(name, hardness, resistance);
	}

	@Environment(EnvType.CLIENT)
	    public BlockRenderLayer getRenderLayer() {
	    return BlockRenderLayer.TRANSLUCENT;
	   }
	}