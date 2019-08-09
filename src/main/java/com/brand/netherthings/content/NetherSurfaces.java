package com.brand.netherthings.content;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.world.surfacebuilder.CondemnedBarrensSurfaceBuilder;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceBuilder;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceConfig;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetherSurfaces {
	public static final NetherThingsSurfaceBuilder NETHER_THINGS = new NetherThingsSurfaceBuilder(NetherThingsSurfaceConfig::deserialize);
	public static final CondemnedBarrensSurfaceBuilder CONDEMNED_BARRENS = new CondemnedBarrensSurfaceBuilder(NetherThingsSurfaceConfig::deserialize);
	
	public static final NetherThingsSurfaceConfig DEFAULT_CONFIG = new NetherThingsSurfaceConfig(Blocks.NETHERRACK.getDefaultState());
	public static final NetherThingsSurfaceConfig BLAZING_CONFIG = new NetherThingsSurfaceConfig(OtherBlocks.BLAZING_NETHERRACK.getDefaultState());
	
	public static void init() {
		// Surface Builder
		Registry.register(Registry.SURFACE_BUILDER, new Identifier(NetherThings.MOD_ID, "nether_things"), NETHER_THINGS);
		Registry.register(Registry.SURFACE_BUILDER, new Identifier(NetherThings.MOD_ID, "condemned_barrens"), CONDEMNED_BARRENS);
	}
}
