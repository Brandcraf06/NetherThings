package com.brand.netherthings.items;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.stuff.glowstone.GlowstoneArmorSet;
import com.brand.netherthings.stuff.glowstone.GlowstoneAxe;
import com.brand.netherthings.stuff.glowstone.GlowstoneHoe;
import com.brand.netherthings.stuff.glowstone.GlowstonePickaxe;
import com.brand.netherthings.stuff.glowstone.GlowstoneShovel;
import com.brand.netherthings.stuff.glowstone.GlowstoneSword;
import com.brand.netherthings.stuff.nether.NetherArmorSet;
import com.brand.netherthings.stuff.nether.NetherAxe;
import com.brand.netherthings.stuff.nether.NetherHoe;
import com.brand.netherthings.stuff.nether.NetherPickaxe;
import com.brand.netherthings.stuff.nether.NetherShovel;
import com.brand.netherthings.stuff.nether.NetherSword;
import com.brand.netherthings.stuff.vibranium.VibraniumArmorSet;
import com.brand.netherthings.stuff.vibranium.VibraniumAxe;
import com.brand.netherthings.stuff.vibranium.VibraniumHoe;
import com.brand.netherthings.stuff.vibranium.VibraniumPickaxe;
import com.brand.netherthings.stuff.vibranium.VibraniumShovel;
import com.brand.netherthings.stuff.vibranium.VibraniumSword;

import net.minecraft.util.registry.Registry;

public class NetherSets
{
	 public static void registerItems()
	    {
	        // nether
		    NetherArmorSet.registerArmor();
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_pickaxe", new NetherPickaxe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_axe", new NetherAxe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_shovel", new NetherShovel());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_hoe", new NetherHoe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_sword", new NetherSword());
	        
	        // glowstone
		    GlowstoneArmorSet.registerArmor();
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_pickaxe", new GlowstonePickaxe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_axe", new GlowstoneAxe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_shovel", new GlowstoneShovel());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_hoe", new GlowstoneHoe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_sword", new GlowstoneSword());
	        
	        // vibranium
		    VibraniumArmorSet.registerArmor();
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_pickaxe", new VibraniumPickaxe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_axe", new VibraniumAxe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_shovel", new VibraniumShovel());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_hoe", new VibraniumHoe());
	        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_sword", new VibraniumSword());
	    }
	}
