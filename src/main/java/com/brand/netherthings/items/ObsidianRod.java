package com.brand.netherthings.items;

import com.brand.netherthings.NetherThings;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ObsidianRod {

	public static final Item OBSIDIAN_ROD = register("obsidian_rod", new Item(new Item.Settings().stackSize(64).itemGroup(ItemGroup.MISC)));

	public static Item register(String name, Item item) {
	    return Registry.register(Registry.ITEM, new Identifier(NetherThings.MOD_ID, name), item);
	}
}
