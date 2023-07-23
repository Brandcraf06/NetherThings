package com.brand.netherthings.content;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.blocks.Crops.BlazingBerryBushBlock;
import com.brand.netherthings.blocks.Crops.NetherCropBlock;
import com.brand.netherthings.blocks.Crops.QuartzCrop;
import com.brand.netherthings.blocks.Crops.WitherCropBlock;
import com.brand.netherthings.blocks.*;
import com.brand.netherthings.worldgen.features.NetherWorldgenFeatures;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

public class NetherBlocks {


    public static final Block GHOST_WHEAT = registerNoItem("ghost_wheat", new NetherCropBlock(FabricBlockSettings.of().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block QUARTZ_CROP = registerNoItem("quartz_crop", new QuartzCrop(FabricBlockSettings.of().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block WITHER_CROP = registerNoItem("wither_crop", new WitherCropBlock(FabricBlockSettings.of().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block BLAZING_BERRY_BUSH = registerNoItem("blazing_berry_bush", new BlazingBerryBushBlock(FabricBlockSettings.of().ticksRandomly().noCollision().luminance(5).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block NETHER_CACTUS = register("nether_cactus", new NetherCactusBlock(FabricBlockSettings.of().mapColor(MapColor.DARK_RED).ticksRandomly().strength(0.4F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block GREEN_GLOWSHROOM_BLOCK = register("green_glowshroom_block", new StainedGlassBlock(DyeColor.LIME, FabricBlockSettings.of().mapColor(MapColor.LIME).instrument(Instrument.BASS).strength(0.2F).luminance(15).sounds(BlockSoundGroup.SLIME)));
    public static final Block BLUE_GLOWSHROOM_BLOCK = register("blue_glowshroom_block", new StainedGlassBlock(DyeColor.BLUE, FabricBlockSettings.of().mapColor(MapColor.BLUE).instrument(Instrument.BASS).strength(0.2F).luminance(15).sounds(BlockSoundGroup.SLIME)));
    public static final Block PURPLE_GLOWSHROOM_BLOCK = register("purple_glowshroom_block", new StainedGlassBlock(DyeColor.PURPLE, FabricBlockSettings.of().mapColor(MapColor.PURPLE).instrument(Instrument.BASS).strength(0.2F).luminance(15).sounds(BlockSoundGroup.SLIME)));

    public static final Block GREEN_GLOWSHROOM = register("green_glowshroom", new GlowshroomPlantBlock(FabricBlockSettings.of().mapColor(MapColor.LIME).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).postProcess(Blocks::always).pistonBehavior(PistonBehavior.DESTROY), NetherWorldgenFeatures.HUGE_GREEN_GLOWSHROOM));
    public static final Block BLUE_GLOWSHROOM = register("blue_glowshroom", new GlowshroomPlantBlock(FabricBlockSettings.of().mapColor(MapColor.BLUE).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).postProcess(Blocks::always).pistonBehavior(PistonBehavior.DESTROY), NetherWorldgenFeatures.HUGE_BLUE_GLOWSHROOM));
    public static final Block PURPLE_GLOWSHROOM = register("purple_glowshroom", new GlowshroomPlantBlock(FabricBlockSettings.of().mapColor(MapColor.PURPLE).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).postProcess(Blocks::always).pistonBehavior(PistonBehavior.DESTROY), NetherWorldgenFeatures.HUGE_PURPLE_GLOWSHROOM));

    public static final Block LAVA_SPONGE = register("lava_sponge", new LavaSpongeBlock(FabricBlockSettings.of().mapColor(MapColor.ORANGE).strength(0.6F).sounds(BlockSoundGroup.GRASS)));
    public static final Block WET_LAVA_SPONGE = register("wet_lava_sponge", new WetLavaSpongeBlock(FabricBlockSettings.of().mapColor(MapColor.ORANGE).strength(0.6F).sounds(BlockSoundGroup.GRASS)));
    public static final Block BLAZING_NETHERRACK = register("blazing_netherrack", new Block(FabricBlockSettings.copy(Blocks.NETHERRACK)));
    public static final Block BURNT_GRASS_BLOCK = register("burnt_grass_block", new Block(FabricBlockSettings.copy(Blocks.GRASS_BLOCK)));
    public static final Block DEAD_GRASS = register("dead_grass", new Block(FabricBlockSettings.copy(Blocks.GRASS)));
    public static final Block CONDEMNED_LEAVES = register("condemned_leaves", new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)));
    public static final Block BONE_LOG = register("bone_log", new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK)));
    public static final Block WITHERED_BONE_BLOCK = register("withered_bone_block", new PillarBlock(FabricBlockSettings.copy(Blocks.BONE_BLOCK)));
    public static final Block TILLED_SOUL_SAND = register("tilled_soul_sand", new TilledSoulSandBlock(FabricBlockSettings.copyOf(Blocks.SOUL_SAND).strength(0.5F).ticksRandomly()));

    public static final Block GLOWING_REEDS = register("glowing_reeds", new GlowingReedsBlock(FabricBlockSettings.of().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().luminance(7).sounds(BlockSoundGroup.GLASS).pistonBehavior(PistonBehavior.DESTROY)));


    public static Block register(String id, Block block, BlockItem item) {
        Registry.register(Registries.BLOCK, NetherThings.id(id), block);
        Registry.register(Registries.ITEM, NetherThings.id(id), item);

        return block;
    }

    public static Block register(String id, Block block) {
        return register(id, block, new BlockItem(block, new Item.Settings()));
    }

    public static Block registerNoItem(String id, Block block) {
        return Registry.register(Registries.BLOCK, NetherThings.id(id), block);
    }

    private static boolean always(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}
