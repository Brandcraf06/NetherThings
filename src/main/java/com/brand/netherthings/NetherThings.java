package com.brand.netherthings;


import com.brand.netherthings.content.NetherBiomes;
import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.content.Ores;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.contentNew.NetherBlocks;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetherThings implements ModInitializer {

	public static final String MOD_ID = "netherthings";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ItemGroup NETHER_THINGS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "nether_things_group"), () -> new ItemStack(Ores.NETHER_DIAMOND_ORE));

	@Override
	public void onInitialize() {
		Ores.init();
		OtherBlocks.init();
		
		NetherOres.addNetherMineables();
		NetherVegetation.addNetherVegetation();
		
		new NetherItems();
		new NetherPotions();
		new NetherEntities();
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
		CompostingChanceRegistry.INSTANCE.add(NetherBlocks.NETHER_CACTUS, 0.50f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_WHEAT, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GLOWING_REEDS, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(NetherBlocks.BLUE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(NetherBlocks.GREEN_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(NetherBlocks.PURPLE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(NetherBlocks.BLUE_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(NetherBlocks.GREEN_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(NetherBlocks.PURPLE_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_BREAD, 0.85f);
	}

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
}

