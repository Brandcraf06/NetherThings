package com.brand.netherthings.content;

import com.brand.netherthings.blocks.BaseGrassBlock;
import com.brand.netherthings.blocks.BaseSpongeBlock;
import com.brand.netherthings.blocks.BaseWetSpongeBlock;
import com.brand.netherthings.blocks.CondemnedLeavesBlock;
import com.brand.netherthings.blocks.CustomNetherrackBlock;
import com.brand.netherthings.blocks.DeadGrass;
import com.brand.netherthings.blocks.GlowingMushroom;
import com.brand.netherthings.blocks.GlowingMushroomBlock;
import com.brand.netherthings.blocks.GlowingReedsBlock;
import com.brand.netherthings.blocks.NetherCactusBlock;
import com.brand.netherthings.blocks.PillarBase;
import com.brand.netherthings.blocks.TilledSoulSandBlock;
import net.minecraft.util.DyeColor;

public class OtherBlocks {

	public static NetherCactusBlock NETHER_CACTUS;
	public static GlowingMushroomBlock GREEN_GLOWING_MUSHROOM_BLOCK;
	public static GlowingMushroomBlock BLUE_GLOWING_MUSHROOM_BLOCK;
	public static GlowingMushroomBlock PURPLE_GLOWING_MUSHROOM_BLOCK;
	public static GlowingMushroom GREEN_GLOWING_MUSHROOM;
	public static GlowingMushroom BLUE_GLOWING_MUSHROOM;
	public static GlowingMushroom PURPLE_GLOWING_MUSHROOM;
	public static BaseSpongeBlock LAVA_SPONGE;
	public static BaseWetSpongeBlock WET_LAVA_SPONGE;
	public static CustomNetherrackBlock BLAZING_NETHERRACK;
	public static BaseGrassBlock BURNT_GRASS_BLOCK;
	public static DeadGrass DEAD_GRASS;
	public static GlowingReedsBlock GLOWING_REEDS;
	public static CondemnedLeavesBlock CONDEMNED_LEAVES;
	public static PillarBase BONE_LOG;
	public static TilledSoulSandBlock TILLED_SOUL_SAND;
	public static PillarBase WITHERED_BONE_BLOCK;


	public static void init() {

		NETHER_CACTUS = new NetherCactusBlock("nether_cactus", 0.4f, 0.4f);
		GREEN_GLOWING_MUSHROOM_BLOCK = new GlowingMushroomBlock("green_glowing_mushroom_block", 0.2f, 0.2f, DyeColor.LIME);
		BLUE_GLOWING_MUSHROOM_BLOCK = new GlowingMushroomBlock("blue_glowing_mushroom_block", 0.2f, 0.2f, DyeColor.BLUE);
		PURPLE_GLOWING_MUSHROOM_BLOCK = new GlowingMushroomBlock("purple_glowing_mushroom_block", 0.2f, 0.2f, DyeColor.PURPLE);
		GREEN_GLOWING_MUSHROOM = new GlowingMushroom("green_glowing_mushroom", 0.0f, 0.0f);
		BLUE_GLOWING_MUSHROOM = new GlowingMushroom("blue_glowing_mushroom", 0.0f, 0.0f);
		PURPLE_GLOWING_MUSHROOM = new GlowingMushroom("purple_glowing_mushroom", 0.0f, 0.0f);
		LAVA_SPONGE = new BaseSpongeBlock("lava_sponge", 0.6f, 0.6f);
		WET_LAVA_SPONGE = new BaseWetSpongeBlock("wet_lava_sponge", 0.6f, 0.6f);
		
		BLAZING_NETHERRACK = new CustomNetherrackBlock("blazing_netherrack");
		BURNT_GRASS_BLOCK= new BaseGrassBlock("burnt_grass_block", 0.6f, 0.6f);
		DEAD_GRASS = new DeadGrass("dead_grass", 0.0f, 0.0f);
		GLOWING_REEDS = new GlowingReedsBlock("glowing_reeds", 0.0f, 0.0f);
		CONDEMNED_LEAVES = new CondemnedLeavesBlock("condemned_leaves", 0.2f, 0.2f);
		BONE_LOG = new PillarBase("bone_log", 2.0f, 2.0f);
		TILLED_SOUL_SAND = new TilledSoulSandBlock("tilled_soul_sand", 2.0f, 2.0f);
		WITHERED_BONE_BLOCK = new PillarBase("withered_bone_block", 2.0f, 2.0f);
		
	}
}
