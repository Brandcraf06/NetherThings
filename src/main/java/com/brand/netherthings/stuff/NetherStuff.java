package com.brand.netherthings.stuff;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.stuff.glowstone.MaterialGlowstoneArmor;
import com.brand.netherthings.stuff.glowstone.MaterialGlowstoneTool;
import com.brand.netherthings.stuff.nether.MaterialNetherArmor;
import com.brand.netherthings.stuff.nether.MaterialNetherTool;
import com.brand.netherthings.stuff.vibranium.MaterialVibraniumArmor;
import com.brand.netherthings.stuff.vibranium.MaterialVibraniumTool;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NetherStuff {

		public static final MaterialNetherArmor materialNetherArmor = new MaterialNetherArmor();
		public static final MaterialNetherTool materialNetherTool = new MaterialNetherTool();
		public static final MaterialGlowstoneArmor materialGlowstoneArmor = new MaterialGlowstoneArmor();
		public static final MaterialGlowstoneTool materialGlowstoneTool = new MaterialGlowstoneTool();
		public static final MaterialVibraniumArmor materialVibraniumArmor = new MaterialVibraniumArmor();
		public static final MaterialVibraniumTool materialVibraniumTool = new MaterialVibraniumTool();		
		
		public static Item register(String name, Item item) {
		    return Registry.register(Registry.ITEM, new Identifier(NetherThings.MOD_ID, name), item);
		}
	}

   
