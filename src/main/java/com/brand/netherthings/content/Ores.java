package com.brand.netherthings.content;

import com.brand.netherthings.blocks.BlockGlowOre;
import com.brand.netherthings.blocks.BlockOre;
import com.brand.netherthings.blocks.BlockRedstoneOre;

public class Ores {

	public static BlockOre NETHER_COAL_ORE;
	public static BlockOre NETHER_IRON_ORE;
	public static BlockOre NETHER_GOLD_ORE;
	public static BlockRedstoneOre NETHER_REDSTONE_ORE;
	public static BlockOre NETHER_LAPIS_ORE;
	public static BlockOre NETHER_DIAMOND_ORE;
	public static BlockOre NETHER_EMERALD_ORE;
	public static BlockGlowOre GLOWSTONE_ORE;
	
	public static void init() {
		
	NETHER_COAL_ORE = new BlockOre("nether_coal_ore", 3.0f, 15.0f);	
    NETHER_IRON_ORE = new BlockOre("nether_iron_ore", 3.0f, 15.0f);
    NETHER_GOLD_ORE = new BlockOre("nether_gold_ore", 3.0f, 15.0f);
    NETHER_REDSTONE_ORE = new BlockRedstoneOre("nether_redstone_ore", 3.0f, 15.0f);
    NETHER_LAPIS_ORE = new BlockOre("nether_lapis_ore", 3.0f, 15.0f);
    NETHER_DIAMOND_ORE = new BlockOre("nether_diamond_ore", 3.0f, 15.0f);
    NETHER_EMERALD_ORE = new BlockOre("nether_emerald_ore", 3.0f, 15.0f);
    GLOWSTONE_ORE = new BlockGlowOre("glowstone_ore", 3.0f, 15.0f);
    
   } 
}
