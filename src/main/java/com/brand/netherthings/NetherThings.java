package com.brand.netherthings;


import com.brand.netherthings.content.Ores;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.items.FullSets;
import com.brand.netherthings.items.GlowingStew;
import com.brand.netherthings.items.GlowstoneIngot;
import com.brand.netherthings.items.ObsidianRod;
import com.brand.netherthings.items.Vibranium;
import com.brand.netherthings.stuff.NetherStuff;
import com.brand.netherthings.world.NetherOres;
import com.brand.netherthings.world.NetherVegetation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class NetherThings implements ModInitializer {
	
	public static final String MOD_ID = "netherthings";
	public static final String VERSION = "1.1.0";
	public static final String NAME = "NetherThings";
	
	@Override
	public void onInitialize() {
		Ores.init();
		OtherBlocks.init();
		
		NetherOres.addNetherOres();
		NetherOres.addOverworldOres();
		NetherOres.addNetherMineables();
		NetherVegetation.addNetherVegetation();
		
		new GlowingStew();
		new GlowstoneIngot();
		new ObsidianRod();
		new Vibranium();
		new NetherStuff();
		FullSets.registerItems();

		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.NETHER_CACTUS, 0.50f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.BLUE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GREEN_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.PURPLE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.BLUE_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GREEN_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.PURPLE_GLOWING_MUSHROOM_BLOCK, 0.85f);
	}
}

