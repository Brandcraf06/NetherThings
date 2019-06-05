package com.brand.netherthings.content;

import com.brand.netherthings.blocks.BlockGlowOre;
import com.brand.netherthings.blocks.BlockOreDiamondP;
import com.brand.netherthings.blocks.BlockOreIronP;
import com.brand.netherthings.blocks.BlockOreStoneP;
import com.brand.netherthings.blocks.BlockOreWoodP;
import com.brand.netherthings.blocks.BlockRedstoneOre;

public class Ores {

	public static BlockOreDiamondP NETHER_VIBRANIUM_ORE;
	public static BlockOreWoodP NETHER_COAL_ORE;
	public static BlockOreStoneP NETHER_IRON_ORE;
	public static BlockOreIronP NETHER_GOLD_ORE;
	public static BlockRedstoneOre NETHER_REDSTONE_ORE;
	public static BlockOreStoneP NETHER_LAPIS_ORE;
	public static BlockOreIronP NETHER_DIAMOND_ORE;
	public static BlockOreIronP NETHER_EMERALD_ORE;
	public static BlockGlowOre GLOWSTONE_ORE;
	public static BlockOreWoodP QUARTZ_ORE;
	
	public static void init() {
		
	NETHER_COAL_ORE = new BlockOreWoodP("nether_coal_ore", 3.0f, 3.0f);	
    NETHER_IRON_ORE = new BlockOreStoneP("nether_iron_ore", 3.0f, 3.0f);
    NETHER_GOLD_ORE = new BlockOreIronP("nether_gold_ore", 3.0f, 3.0f);
    NETHER_REDSTONE_ORE = new BlockRedstoneOre("nether_redstone_ore", 3.0f, 3.0f);
    NETHER_LAPIS_ORE = new BlockOreStoneP("nether_lapis_ore", 3.0f, 3.0f);
    NETHER_DIAMOND_ORE = new BlockOreIronP("nether_diamond_ore", 3.0f, 3.0f);
    NETHER_EMERALD_ORE = new BlockOreIronP("nether_emerald_ore", 3.0f, 3.0f);
    GLOWSTONE_ORE = new BlockGlowOre("glowstone_ore", 3.0f, 3.0f);
    QUARTZ_ORE = new BlockOreWoodP("quartz_ore", 3.0f, 3.0f);
    NETHER_VIBRANIUM_ORE = new BlockOreDiamondP("nether_vibranium_ore", 6.0f, 6.0f);	
    
   } 
}
