package com.brand.netherthings.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

public class NetherPotions {
	   public static final Potion DECAY;
	   public static final Potion LONG_DECAY;
	   public static final Potion STRONG_DECAY;
	   public static final Potion TOUGHNESS;
	   public static final Potion LONG_TOUGHNESS;
	   public static final Potion STRONG_TOUGHNESS;
	   public static final Potion INVINCIBILITY;
	   public static final Potion LONG_INVINCIBILITY;

	   private static Potion register(String string_1, Potion potion_1) {
	      return (Potion)Registry.register(Registry.POTION, (String)string_1, potion_1);
	   }

	   static {
	      DECAY = register("decay", new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.WITHER, 1200)}));
	      LONG_DECAY = register("long_decay", new Potion("decay", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.WITHER, 2200)}));
	      STRONG_DECAY = register("strong_decay", new Potion("decay", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.WITHER, 800, 1)}));
	      TOUGHNESS = register("toughness", new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.RESISTANCE, 1100, 1)}));
	      LONG_TOUGHNESS = register("long_toughness", new Potion("toughness", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.RESISTANCE, 1900, 1)}));
	      STRONG_TOUGHNESS = register("strong_toughness", new Potion("toughness", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 2)}));
	      INVINCIBILITY = register("invincibility", new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.RESISTANCE, 3600, 4), new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3600)}));
	      LONG_INVINCIBILITY = register("long_invincibility", new Potion("invincibility", new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 4), new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000)}));
	   }
	}

