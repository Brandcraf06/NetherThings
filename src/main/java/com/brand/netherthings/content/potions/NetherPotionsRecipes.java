package com.brand.netherthings.content.potions;

import com.brand.netherthings.content.NetherItems;
import com.brand.netherthings.content.NetherPotions;
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
    }


    private static void register(String configKey, Potion base, Item ingredient, Potion result) {
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, ingredient, result);
    }
}