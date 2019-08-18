package com.brand.netherthings.content;

import com.brand.netherthings.blocks.BaseGrassBlock;
import com.brand.netherthings.blocks.BaseSpongeBlock;
import com.brand.netherthings.blocks.BaseWetSpongeBlock;
import com.brand.netherthings.blocks.BlockBase;
import com.brand.netherthings.blocks.CondemnedLeavesBlock;
import com.brand.netherthings.blocks.CustomNetherrackBlock;
import com.brand.netherthings.blocks.DeadGrass;
import com.brand.netherthings.blocks.GlowingMushroom;
import com.brand.netherthings.blocks.GlowingMushroomBlock;
import com.brand.netherthings.blocks.GlowingReedsBlock;
import com.brand.netherthings.blocks.NetherCactusBlock;
import com.brand.netherthings.blocks.PillarBase;
import com.brand.netherthings.blocks.PlowedNetherrackBlock;
import com.brand.netherthings.blocks.SlabBlockBase;
import com.brand.netherthings.blocks.StairsBlockBase;
import com.brand.netherthings.blocks.VibraniumBlock;

import net.minecraft.util.DyeColor;

public class OtherBlocks {

	public static NetherCactusBlock NETHER_CACTUS;
	public static GlowingMushroomBlock GREEN_GLOWING_MUSHROOM_BLOCK;
	public static GlowingMushroomBlock BLUE_GLOWING_MUSHROOM_BLOCK;
	public static GlowingMushroomBlock PURPLE_GLOWING_MUSHROOM_BLOCK;
	public static GlowingMushroom GREEN_GLOWING_MUSHROOM;
	public static GlowingMushroom BLUE_GLOWING_MUSHROOM;
	public static GlowingMushroom PURPLE_GLOWING_MUSHROOM;
	public static BlockBase BASALT;
	public static BlockBase BASALT_BRICKS;
	public static BlockBase SMOOTH_BASALT;
	public static StairsBlockBase BASALT_BRICKS_STAIRS;
	public static SlabBlockBase BASALT_BRICKS_SLAB;
	public static BaseSpongeBlock LAVA_SPONGE;
	public static BaseWetSpongeBlock WET_LAVA_SPONGE;
	public static VibraniumBlock VIBRANIUM_BLOCK;
	public static CustomNetherrackBlock BLAZING_NETHERRACK;
	public static BaseGrassBlock BURNT_GRASS_BLOCK;
	public static DeadGrass DEAD_GRASS;
	public static GlowingReedsBlock GLOWING_REEDS;
	public static CondemnedLeavesBlock CONDEMNED_LEAVES;
	public static PillarBase BONE_LOG;
	public static PlowedNetherrackBlock PLOWED_NETHERRACK;


	public static void init() {

		NETHER_CACTUS = new NetherCactusBlock("nether_cactus", 0.4f, 0.4f);
		GREEN_GLOWING_MUSHROOM_BLOCK = new GlowingMushroomBlock("green_glowing_mushroom_block", 0.2f, 0.2f, DyeColor.LIME);
		BLUE_GLOWING_MUSHROOM_BLOCK = new GlowingMushroomBlock("blue_glowing_mushroom_block", 0.2f, 0.2f, DyeColor.BLUE);
		PURPLE_GLOWING_MUSHROOM_BLOCK = new GlowingMushroomBlock("purple_glowing_mushroom_block", 0.2f, 0.2f, DyeColor.PURPLE);
		GREEN_GLOWING_MUSHROOM = new GlowingMushroom("green_glowing_mushroom", 0.0f, 0.0f);
		BLUE_GLOWING_MUSHROOM = new GlowingMushroom("blue_glowing_mushroom", 0.0f, 0.0f);
		PURPLE_GLOWING_MUSHROOM = new GlowingMushroom("purple_glowing_mushroom", 0.0f, 0.0f);
		BASALT = new BlockBase("basalt", 1.5f, 6.0f);
		BASALT_BRICKS = new BlockBase("basalt_bricks", 1.5f, 6.0f);
		BASALT_BRICKS_STAIRS = new StairsBlockBase(BASALT_BRICKS.getDefaultState(), "basalt_bricks_stairs", 1.5f, 6.0f);
		BASALT_BRICKS_SLAB = new SlabBlockBase("basalt_bricks_slab", 1.5f, 6.0f);
		SMOOTH_BASALT = new BlockBase("smooth_basalt", 1.5f, 6.0f);
		LAVA_SPONGE = new BaseSpongeBlock("lava_sponge", 0.6f, 0.6f);
		WET_LAVA_SPONGE = new BaseWetSpongeBlock("wet_lava_sponge", 0.6f, 0.6f);
		VIBRANIUM_BLOCK = new VibraniumBlock("vibranium_block", 4.0f, 12.0f);
		
		BLAZING_NETHERRACK = new CustomNetherrackBlock("blazing_netherrack");
		BURNT_GRASS_BLOCK= new BaseGrassBlock("burnt_grass_block", 0.6f, 0.6f);
		DEAD_GRASS = new DeadGrass("dead_grass", 0.0f, 0.0f);
		GLOWING_REEDS = new GlowingReedsBlock("glowing_reeds", 0.0f, 0.0f);
		CONDEMNED_LEAVES = new CondemnedLeavesBlock("condemned_leaves", 0.2f, 0.2f);
		BONE_LOG = new PillarBase("bone_log", 2.0f, 2.0f);
		PLOWED_NETHERRACK = new PlowedNetherrackBlock("plowed_netherrack", 2.0f, 2.0f);
	}
}
