package com.brand.netherthings.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;

@Config(name = "netherthings")
public class NetherThingsConfig implements ConfigData {
    public boolean enableNetherMushrooms = true;
    public boolean enableNetherCactus = true;
    public boolean enableBasaltGeneration = true;
    public boolean enableNetherOresInOverworld = true;
    public boolean enableOverworldOresInNether = true;
}
