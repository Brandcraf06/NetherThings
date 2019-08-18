package com.brand.netherthings.content;

import com.brand.netherthings.blocks.Crops.GhostCrop;

public class Crops {
	
	public static GhostCrop GHOST_WHEAT;

	public static void init() {
		
		GHOST_WHEAT = new GhostCrop("ghost_wheat", 0.0f, 0.0f);	
	   
	   } 
	}
