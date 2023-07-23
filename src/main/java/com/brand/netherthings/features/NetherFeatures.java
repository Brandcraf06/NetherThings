package com.brand.netherthings.features;

import com.brand.netherthings.NetherThings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public class NetherFeatures {

    public static final Feature HUGE_GLOWSHROOM = register("huge_glowshroom", new HugeGlowshroomFeature(HugeMushroomFeatureConfig.CODEC));
    public static final Feature HUGE_PURPLE_GLOWSHROOM = register("huge_purple_glowshroom", new HugePurpleGlowshroomFeature(HugeMushroomFeatureConfig.CODEC));


    public static Feature register(String id, Feature<?> feature) {
        return Registry.register(Registries.FEATURE, NetherThings.id(id), feature);
    }
}
