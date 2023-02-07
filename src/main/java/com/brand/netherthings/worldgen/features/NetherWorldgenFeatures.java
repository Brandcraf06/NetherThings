package com.brand.netherthings.worldgen.features;

import com.brand.netherthings.NetherThings;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class NetherWorldgenFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_CACTUS = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, NetherThings.id("nether_cactus"));
    public static final RegistryKey<PlacedFeature> PLACED_NETHER_CACTUS = RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("nether_cactus"));

    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_GREEN_GLOWSHROOM = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, NetherThings.id("huge_green_glowshroom"));
    public static final RegistryKey<PlacedFeature> PLACED_HUGE_GREEN_GLOWSHROOM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("huge_green_glowshroom"));

    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_BLUE_GLOWSHROOM = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, NetherThings.id("huge_blue_glowshroom"));
    public static final RegistryKey<PlacedFeature> PLACED_HUGE_BLUE_GLOWSHROOM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("huge_blue_glowshroom"));

    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_PURPLE_GLOWSHROOM = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, NetherThings.id("huge_purple_glowshroom"));
    public static final RegistryKey<PlacedFeature> PLACED_HUGE_PURPLE_GLOWSHROOM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("huge_purple_glowshroom"));

    public static void registerConfiguredFeature() {

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("nether_cactus")));

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("huge_green_glowshroom")));

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("huge_blue_glowshroom")));

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(RegistryKeys.PLACED_FEATURE, NetherThings.id("huge_purple_glowshroom")));

    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
