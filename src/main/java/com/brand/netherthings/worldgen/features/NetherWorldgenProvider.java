package com.brand.netherthings.worldgen.features;

import com.brand.netherthings.content.NetherBlocks;
import com.brand.netherthings.features.NetherFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NetherWorldgenProvider extends FabricDynamicRegistryProvider {
    public NetherWorldgenProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {

        // Nether Cactus
        ConfiguredFeature<?, ?> NETHER_CACTUS = new ConfiguredFeature<>(Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(10, PlacedFeatures.createEntry(Feature.BLOCK_COLUMN, BlockColumnFeatureConfig.create(BiasedToBottomIntProvider.create(1, 3), BlockStateProvider.of(NetherBlocks.NETHER_CACTUS)), new PlacementModifier[]{BlockFilterPlacementModifier.of(BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.wouldSurvive(NetherBlocks.NETHER_CACTUS.getDefaultState(), BlockPos.ORIGIN)))})));
        RegistryEntry<ConfiguredFeature<?, ?>> netherCactus = entries.add(NetherWorldgenFeatures.NETHER_CACTUS, NETHER_CACTUS);

        PlacedFeature PLACED_NETHER_CACTUS = new PlacedFeature(netherCactus, List.of(new PlacementModifier[]{RarityFilterPlacementModifier.of(6), SquarePlacementModifier.of(), CountMultilayerPlacementModifier.of(4), BiomePlacementModifier.of()}));
        entries.add(NetherWorldgenFeatures.PLACED_NETHER_CACTUS, PLACED_NETHER_CACTUS);

        // Huge Green Glowshroom
        ConfiguredFeature<?, ?> HUGE_GREEN_GLOWSHROOM = new ConfiguredFeature<>(NetherFeatures.HUGE_GLOWSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of((BlockState) NetherBlocks.GREEN_GLOWSHROOM_BLOCK.getDefaultState()), BlockStateProvider.of((BlockState)((BlockState)Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false)).with(MushroomBlock.DOWN, false)), 3));
        RegistryEntry<ConfiguredFeature<?, ?>> hugeGreenGlowshroom = entries.add(NetherWorldgenFeatures.HUGE_GREEN_GLOWSHROOM, HUGE_GREEN_GLOWSHROOM);

        PlacedFeature PLACED_HUGE_GREEN_GLOWSHROOM = new PlacedFeature(hugeGreenGlowshroom, List.of(new PlacementModifier[]{RarityFilterPlacementModifier.of(10), CountMultilayerPlacementModifier.of(2), BiomePlacementModifier.of()}));
        entries.add(NetherWorldgenFeatures.PLACED_HUGE_GREEN_GLOWSHROOM, PLACED_HUGE_GREEN_GLOWSHROOM);

        // Huge Blue Glowshroom
        ConfiguredFeature<?, ?> HUGE_BLUE_GLOWSHROOM = new ConfiguredFeature<>(NetherFeatures.HUGE_GLOWSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of((BlockState) NetherBlocks.BLUE_GLOWSHROOM_BLOCK.getDefaultState()), BlockStateProvider.of((BlockState)((BlockState)Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false)).with(MushroomBlock.DOWN, false)), 3));
        RegistryEntry<ConfiguredFeature<?, ?>> hugeBlueGlowshroom = entries.add(NetherWorldgenFeatures.HUGE_BLUE_GLOWSHROOM, HUGE_BLUE_GLOWSHROOM);

        PlacedFeature PLACED_HUGE_BLUE_GLOWSHROOM = new PlacedFeature(hugeBlueGlowshroom, List.of(new PlacementModifier[]{RarityFilterPlacementModifier.of(10), CountMultilayerPlacementModifier.of(2), BiomePlacementModifier.of()}));
        entries.add(NetherWorldgenFeatures.PLACED_HUGE_BLUE_GLOWSHROOM, PLACED_HUGE_BLUE_GLOWSHROOM);

        // Huge Purple Glowshroom
        ConfiguredFeature<?, ?> HUGE_PURPLE_GLOWSHROOM = new ConfiguredFeature<>(NetherFeatures.HUGE_PURPLE_GLOWSHROOM, new HugeMushroomFeatureConfig(BlockStateProvider.of((BlockState) NetherBlocks.PURPLE_GLOWSHROOM_BLOCK.getDefaultState()), BlockStateProvider.of((BlockState)((BlockState)Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false)).with(MushroomBlock.DOWN, false)), 3));
        RegistryEntry<ConfiguredFeature<?, ?>> hugePurpleGlowshroom = entries.add(NetherWorldgenFeatures.HUGE_PURPLE_GLOWSHROOM, HUGE_PURPLE_GLOWSHROOM);

        PlacedFeature PLACED_HUGE_PURPLE_GLOWSHROOM = new PlacedFeature(hugePurpleGlowshroom, List.of(new PlacementModifier[]{RarityFilterPlacementModifier.of(10), CountMultilayerPlacementModifier.of(2), BiomePlacementModifier.of()}));
        entries.add(NetherWorldgenFeatures.PLACED_HUGE_PURPLE_GLOWSHROOM, PLACED_HUGE_PURPLE_GLOWSHROOM);
    }

    @Override
    public String getName() {
        return "Nether Things World Gen";
    }
}
