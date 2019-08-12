package com.brand.netherthings.content;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.world.surfacebuilder.CondemnedBarrensSurfaceBuilder;
import com.brand.netherthings.world.surfacebuilder.MeadowSurfaceBuilder;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceConfig;
import com.brand.netherthings.world.surfacebuilder.NetherThingsSurfaceBuilder;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetherSurfaces {
	public static final NetherThingsSurfaceBuilder NETHER_THINGS = new NetherThingsSurfaceBuilder(NetherThingsSurfaceConfig::deserialize);
	public static final CondemnedBarrensSurfaceBuilder CONDEMNED_BARRENS = new CondemnedBarrensSurfaceBuilder(NetherThingsSurfaceConfig::deserialize);
	public static final MeadowSurfaceBuilder NETHER_MEADOW = new MeadowSurfaceBuilder(NetherThingsSurfaceConfig::deserialize);
	
	public static final NetherThingsSurfaceConfig DEFAULT_CONFIG = new NetherThingsSurfaceConfig(Blocks.NETHERRACK.getDefaultState());
	public static final NetherThingsSurfaceConfig BLAZING_CONFIG = new NetherThingsSurfaceConfig(OtherBlocks.BLAZING_NETHERRACK.getDefaultState());
	public static final NetherThingsSurfaceConfig MEADOW_CONFIG = new NetherThingsSurfaceConfig(Blocks.GRASS_BLOCK.getDefaultState());
	public static final NetherThingsSurfaceConfig BURNED_MEADOW_CONFIG = new NetherThingsSurfaceConfig(OtherBlocks.BURNT_GRASS_BLOCK.getDefaultState());
	
	public static void init() {
		// Surface Builder
		Registry.register(Registry.SURFACE_BUILDER, new Identifier(NetherThings.MOD_ID, "nether_things"), NETHER_THINGS);
		Registry.register(Registry.SURFACE_BUILDER, new Identifier(NetherThings.MOD_ID, "condemned_barrens"), CONDEMNED_BARRENS);
		Registry.register(Registry.SURFACE_BUILDER, new Identifier(NetherThings.MOD_ID, "nether_meadow"), NETHER_MEADOW);
	}
}
