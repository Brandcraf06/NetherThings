package com.brand.netherthings.items;

import com.brand.netherthings.NetherThings;

import net.minecraft.util.registry.Registry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodItemSetting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MushroomStewItem;
import net.minecraft.util.Identifier;

public class GlowingStew {

	public static final Item GLOWING_STEW = register("glowing_stew", new MushroomStewItem(new Item.Settings().stackSize(1).itemGroup(ItemGroup.FOOD).food(new FoodItemSetting.Builder().hunger(6).saturationModifier(0.6F)
			.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0, true, false, true), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 0, true, false, true), 1.0F).build())));

	public static Item register(String name, Item item) {
	    return Registry.register(Registry.ITEM, new Identifier(NetherThings.MOD_ID, name), item);
	}
}
