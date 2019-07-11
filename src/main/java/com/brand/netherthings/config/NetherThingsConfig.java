package com.brand.netherthings.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;

@Config(name = "netherthings")
public class NetherThingsConfig implements ConfigData {
    boolean enableNetherMushrooms = true;
    boolean enableNetherCactus = true;
    boolean enableNetherOresInOverworld = true;
    boolean enableOverworldOresInNether = true;
}
