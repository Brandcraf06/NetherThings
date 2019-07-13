package com.brand.netherthings.config;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;

@Config(name = "netherthings")
public class NetherThingsConfig implements ConfigData {
    public boolean enableNetherMushrooms = true;
    public boolean enableNetherCactus = true;
    public boolean enableBasaltGeneration = true;
    public boolean enableNetherVibraniumOre = true;
    public boolean enableNetherOresInOverworld = true;
    public boolean enableOverworldOresInNether = true;
    public float netherCactusDamage = 5.0F;
    
    // vibranium config
    public int vibraniumToolDurability = 21850;
    public float vibraniumToolSpeed = 22f;
    public float vibraniumToolDamage = 10f;
    public int vibraniumToolEnchantability = 7;
    public int vibraniumArmorDurabilityModifier = 1050;
    public int vibraniumArmorEnchantability = 10;
    public int[] vibraniumArmorProtectionAmounts = {
            5,
            9,
            10,
            5
    };

    // glowstone config
    public int glowstoneToolDurability = 442;
    public float glowstoneToolSpeed = 5f;
    public float glowstoneToolDamage = 4.7f;
    public int glowstoneToolEnchantability = 15;
    public int glowstoneArmorDurabilityModifier = 27;
    public int glowstoneArmorEnchantability = 10;
    public int[] glowstoneArmorProtectionAmounts = {
            2,
            5,
            6,
            3
    };
    
    // nether config
    public int netherToolDurability = 280;
    public float netherToolSpeed = 5f;
    public float netherToolDamage = 3.5f;
    public int netherToolEnchantability = 60;
    public int netherArmorDurabilityModifier = 16;
    public int netherArmorEnchantability = 65;
    public int[] netherArmorProtectionAmounts = {
            2,
            5,
            5,
            2
    };
    
}
