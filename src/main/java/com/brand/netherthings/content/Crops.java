package com.brand.netherthings.content;

import com.brand.netherthings.blocks.Crops.BlazingBerryBushBlock;
import com.brand.netherthings.blocks.Crops.GhostCrop;
import com.brand.netherthings.blocks.Crops.QuartzCrop;
import com.brand.netherthings.blocks.Crops.WitherCropBlock;

public class Crops {
	
	public static GhostCrop GHOST_WHEAT;
	public static QuartzCrop QUARTZ_CROP;
	public static BlazingBerryBushBlock BLAZING_BERRY_BUSH;
	public static WitherCropBlock WITHER_CROP;

	public static void init() {
		
		GHOST_WHEAT = new GhostCrop("ghost_wheat", 0.0f, 0.0f);
		WITHER_CROP = new WitherCropBlock("wither_crop", 0.0f, 0.0f);
		QUARTZ_CROP = new QuartzCrop("quartz_crop", 0.0f, 0.0f);
		BLAZING_BERRY_BUSH = new BlazingBerryBushBlock("blazing_berry_bush", 0.0f, 0.0f);	
	   
	   } 
	}
