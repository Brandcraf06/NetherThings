package com.brand.netherthings.items;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.content.Crops;
import com.brand.netherthings.entities.NetherEntities;
import com.brand.netherthings.items.type.WitheredBoneMealItem;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.MushroomStewItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetherItems {

	public static final Item GLOWSTONE_INGOT = register("glowstone_ingot", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item OBSIDIAN_ROD = register("obsidian_rod", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item VIBRANIUM = register("vibranium", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item VIBRANIUM_DUST = register("vibranium_dust", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item WITHER_FRAGMENT = register("wither_fragment", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item WITHERED_BONE = register("withered_bone", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item WITHERED_BONE_MEAL = register("withered_bone_meal", new WitheredBoneMealItem(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item WITHER_PIGMAN_SPAWN_EGG = register("wither_pigman_spawn_egg", new SpawnEggItem(NetherEntities.WITHER_PIGMAN, 2823460, 5972771, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item BLUE_GLOWMOO_SPAWN_EGG = register("blue_glowmoo_spawn_egg", new SpawnEggItem(NetherEntities.BLUE_GLOWMOO, 1752531, 14473411, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item GREEN_GLOWMOO_SPAWN_EGG = register("green_glowmoo_spawn_egg", new SpawnEggItem(NetherEntities.GREEN_GLOWMOO, 4969242, 14473411, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item PURPLE_GLOWMOO_SPAWN_EGG = register("purple_glowmoo_spawn_egg", new SpawnEggItem(NetherEntities.PURPLE_GLOWMOO, 9518512, 14473411, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	
	// crops
	public static final Item WITHER_SEEDS = register("wither_seeds", new AliasedBlockItem(Crops.WITHER_CROP, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item GHOST_SEEDS = register("ghost_seeds", new AliasedBlockItem(Crops.GHOST_WHEAT, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item QUARTZ_SEEDS = register("quartz_seeds", new AliasedBlockItem(Crops.QUARTZ_CROP, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	public static final Item GHOST_WHEAT = register("ghost_wheat", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	
	// foods
	public static final Item GLOWING_STEW = register("glowing_stew", new MushroomStewItem(new Item.Settings().maxCount(1).group(NetherThings.NETHER_THINGS_GROUP).food(new FoodComponent.Builder().hunger(6).saturationModifier(0.6F)
			.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0, true, false, true), 1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 0, true, false, true), 1.0F).alwaysEdible().build())));
	public static final Item GHOST_BREAD = register("ghost_bread", new Item(new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP).food(new FoodComponent.Builder().hunger(8).saturationModifier(1.0F)
			.statusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 500, 0, true, false, true), 1.0F).alwaysEdible().build())));
	public static final Item BLAZING_BERRIES = register("blazing_berries", new AliasedBlockItem(Crops.BLAZING_BERRY_BUSH, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP).food(new FoodComponent.Builder().hunger(3)
			.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 250, 0, true, false, true), 1.0F).alwaysEdible().build())));
	

	public static Item register(String name, Item item) {
	    return Registry.register(Registry.ITEM, new Identifier(NetherThings.MOD_ID, name), item);
	}
}
