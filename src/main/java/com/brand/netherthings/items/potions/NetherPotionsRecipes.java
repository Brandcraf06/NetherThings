package com.brand.netherthings.items.potions;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.items.NetherItems;
import com.brand.netherthings.items.NetherPotions;
import com.brand.netherthings.mixin.BrewingRecipeRegistryAccessor;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;

public class NetherPotionsRecipes {

    public static void registerRecipes() {
        register("decay", Potions.AWKWARD, NetherItems.WITHER_FRAGMENT, NetherPotions.DECAY);
        register("decay", NetherPotions.DECAY, Items.GLOWSTONE_DUST, NetherPotions.STRONG_DECAY);
        register("decay", NetherPotions.DECAY, Items.REDSTONE, NetherPotions.LONG_DECAY);
        register("toughness", Potions.AWKWARD, NetherItems.VIBRANIUM, NetherPotions.TOUGHNESS);
        register("toughness", NetherPotions.TOUGHNESS, Items.GLOWSTONE_DUST, NetherPotions.STRONG_TOUGHNESS);
        register("toughness", NetherPotions.TOUGHNESS, Items.REDSTONE, NetherPotions.LONG_TOUGHNESS);
        if (NetherThings.CONFIG.enableInvincibilityPotionRecipe) {
        register("invincibility", NetherPotions.TOUGHNESS, NetherItems.VIBRANIUM_DUST, NetherPotions.INVINCIBILITY);
        register("invincibility", NetherPotions.INVINCIBILITY, NetherItems.VIBRANIUM, NetherPotions.LONG_INVINCIBILITY);
        }
    }

    private static void register(String configKey, Potion base, Item ingredient, Potion result) {
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, ingredient, result);
    }
}