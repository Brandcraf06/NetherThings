package com.brand.netherthings;


import com.brand.netherthings.content.Crops;
import com.brand.netherthings.content.NetherBiomes;
import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.content.Ores;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.entities.NetherEntities;
import com.brand.netherthings.items.NetherItems;
import com.brand.netherthings.items.NetherPotions;
import com.brand.netherthings.items.NetherSets;
import com.brand.netherthings.items.potions.NetherPotionsRecipes;
import com.brand.netherthings.stuff.NetherStuff;
import com.brand.netherthings.world.NetherOres;
import com.brand.netherthings.world.NetherVegetation;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class NetherThings implements ModInitializer {
	
	public static final String MOD_ID = "netherthings";
	public static final String VERSION = "2.0.0";
	public static final String NAME = "NetherThings";
	public static final ItemGroup NETHER_THINGS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "nether_things_group"), () -> new ItemStack(Ores.NETHER_DIAMOND_ORE));
	
	@Override
	public void onInitialize() {
		Ores.init();
		Crops.init();
		OtherBlocks.init();
		
		NetherOres.addNetherMineables();
		NetherVegetation.addNetherVegetation();
		
		new NetherItems();
		new NetherPotions();
		new NetherEntities();
		new NetherStuff();
		NetherPotionsRecipes.registerRecipes();
		NetherSets.registerItems();
		
		NetherSurfaces.init();
		NetherBiomes.init();
		
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.DEAD_GRASS, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.CONDEMNED_LEAVES, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.BLAZING_BERRIES, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_SEEDS, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.QUARTZ_SEEDS, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.WITHER_SEEDS, 0.30f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.NETHER_CACTUS, 0.50f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_WHEAT, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GLOWING_REEDS, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.BLUE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GREEN_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.PURPLE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.BLUE_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GREEN_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.PURPLE_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_BREAD, 0.85f);
	}
}

