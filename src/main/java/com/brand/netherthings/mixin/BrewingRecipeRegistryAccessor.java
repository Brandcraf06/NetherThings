package com.brand.netherthings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;

@Mixin(BrewingRecipeRegistry.class)
public interface BrewingRecipeRegistryAccessor{

    @Invoker
    public static void invokeRegisterPotionRecipe(Potion potion, Item item, Potion potion2){
    }

}


	

