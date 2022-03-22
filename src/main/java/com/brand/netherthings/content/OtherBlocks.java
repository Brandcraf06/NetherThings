package com.brand.netherthings.content;

import com.brand.netherthings.blocks.CondemnedLeavesBlock;
import com.brand.netherthings.blocks.DeadGrassBlock;
import com.brand.netherthings.blocks.GlowingReedsBlock;
import com.brand.netherthings.blocks.PillarBase;
import com.brand.netherthings.blocks.TilledSoulSandBlock;

public class OtherBlocks {

	public static DeadGrassBlock DEAD_GRASS;
	public static GlowingReedsBlock GLOWING_REEDS;
	public static CondemnedLeavesBlock CONDEMNED_LEAVES;
	public static PillarBase BONE_LOG;
	public static TilledSoulSandBlock TILLED_SOUL_SAND;
	public static PillarBase WITHERED_BONE_BLOCK;


	public static void init() {

		DEAD_GRASS = new DeadGrassBlock("dead_grass", 0.0f, 0.0f);
		GLOWING_REEDS = new GlowingReedsBlock("glowing_reeds", 0.0f, 0.0f);
		CONDEMNED_LEAVES = new CondemnedLeavesBlock("condemned_leaves", 0.2f, 0.2f);
		BONE_LOG = new PillarBase("bone_log", 2.0f, 2.0f);
		TILLED_SOUL_SAND = new TilledSoulSandBlock("tilled_soul_sand", 2.0f, 2.0f);
		WITHERED_BONE_BLOCK = new PillarBase("withered_bone_block", 2.0f, 2.0f);
		
	}
}
