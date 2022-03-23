package com.brand.netherthings.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

public class NetherPotions {
    public static final Potion DECAY;
    public static final Potion LONG_DECAY;
    public static final Potion STRONG_DECAY;

    private static Potion register(String string_1, Potion potion_1) {
        return Registry.register(Registry.POTION, string_1, potion_1);
    }

    static {
        DECAY = register("decay", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 1200)));
        LONG_DECAY = register("long_decay", new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, 2200)));
        STRONG_DECAY = register("strong_decay", new Potion("decay", new StatusEffectInstance(StatusEffects.WITHER, 800, 1)));
    }
}

