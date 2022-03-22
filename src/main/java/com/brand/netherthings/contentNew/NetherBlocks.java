package com.brand.netherthings.contentNew;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.blocks.Crops.BlazingBerryBushBlock;
import com.brand.netherthings.blocks.Crops.NetherCropBlock;
import com.brand.netherthings.blocks.Crops.QuartzCrop;
import com.brand.netherthings.blocks.Crops.WitherCropBlock;
import com.brand.netherthings.blocks.GlowingMushroom;
import com.brand.netherthings.blocks.NetherCactusBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;

public class NetherBlocks {


    public static final Block GHOST_WHEAT = registerNoItem("ghost_wheat", new NetherCropBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).ticksRandomly().noCollision().strength(0)));
    public static final Block QUARTZ_CROP = registerNoItem("quartz_crop", new QuartzCrop(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).ticksRandomly().noCollision().strength(0)));
    public static final Block WITHER_CROP = registerNoItem("wither_crop", new WitherCropBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).ticksRandomly().noCollision().strength(0)));
    public static final Block BLAZING_BERRY_BUSH = registerNoItem("blazing_berry_bush", new BlazingBerryBushBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).ticksRandomly().luminance(5).noCollision().strength(0)));

    public static final Block NETHER_CACTUS = register("nether_cactus", new NetherCactusBlock(FabricBlockSettings.of(Material.CACTUS).sounds(BlockSoundGroup.WOOL).ticksRandomly().strength(0.4f)));
    public static final Block GREEN_GLOWING_MUSHROOM_BLOCK = register("green_glowing_mushroom_block", new StainedGlassBlock(DyeColor.LIME, FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.SLIME).luminance(15).strength(0.2f)));
    public static final Block BLUE_GLOWING_MUSHROOM_BLOCK = register("blue_glowing_mushroom_block", new StainedGlassBlock(DyeColor.BLUE, FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.SLIME).luminance(15).strength(0.2f )));
    public static final Block PURPLE_GLOWING_MUSHROOM_BLOCK = register("purple_glowing_mushroom_block", new StainedGlassBlock(DyeColor.PURPLE, FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.SLIME).luminance(15).strength(0.2f)));

    public static final Block GREEN_GLOWING_MUSHROOM = register("green_glowing_mushroom", new GlowingMushroom(FabricBlockSettings.of(Material.PLANT, MapColor.GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).postProcess(NetherBlocks::always), () -> {
        return TreeConfiguredFeatures.HUGE_RED_MUSHROOM;
    }));

    public static final Block BLUE_GLOWING_MUSHROOM = register("blue_glowing_mushroom", new GlowingMushroom(FabricBlockSettings.of(Material.PLANT, MapColor.BLUE).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).postProcess(NetherBlocks::always), () -> {
        return TreeConfiguredFeatures.HUGE_RED_MUSHROOM;
    }));

    public static final Block PURPLE_GLOWING_MUSHROOM = register("purple_glowing_mushroom", new GlowingMushroom(FabricBlockSettings.of(Material.PLANT, MapColor.PURPLE).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).postProcess(NetherBlocks::always), () -> {
        return TreeConfiguredFeatures.HUGE_RED_MUSHROOM;
    }));

    public static final Block LAVA_SPONGE = register("lava_sponge", new SpongeBlock(FabricBlockSettings.of(Material.SPONGE).strength(0.6F).sounds(BlockSoundGroup.GRASS)));
    public static final Block WET_LAVA_SPONGE = register("wet_lava_sponge", new WetSpongeBlock(FabricBlockSettings.of(Material.SPONGE).strength(0.6F).sounds(BlockSoundGroup.GRASS)));
    public static final Block BLAZING_NETHERRACK = register("blazing_netherrack", new Block(FabricBlockSettings.copy(Blocks.NETHERRACK)));
    public static final Block BURNT_GRASS_BLOCK = register("burnt_grass_block", new Block(FabricBlockSettings.copy(Blocks.GRASS_BLOCK)));
    public static final Block DEAD_GRASS = register("dead_grass", new Block(FabricBlockSettings.copy(Blocks.GRASS)));




    public static Block register(String id, Block block, BlockItem item) {
        Registry.register(Registry.BLOCK, NetherThings.id(id), block);
        Registry.register(Registry.ITEM, NetherThings.id(id), item);

        return block;
    }

    public static Block register(String id, Block block, ItemGroup itemGroup) {
        return register(id, block, new BlockItem(block, new Item.Settings().group(itemGroup)));
    }


    public static Block registerNoItem(String id, Block block) {
        return Registry.register(Registry.BLOCK, NetherThings.id(id), block);
    }

    public static Block register(String id, Block block) {
        return register(id, block, NetherThings.NETHER_THINGS_GROUP);
    }

    private static boolean always(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}
