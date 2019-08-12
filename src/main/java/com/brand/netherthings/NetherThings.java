package com.brand.netherthings;


import com.brand.netherthings.config.NetherThingsConfig;
import com.brand.netherthings.content.NetherBiomes;
import com.brand.netherthings.content.NetherSurfaces;
import com.brand.netherthings.content.Ores;
import com.brand.netherthings.content.OtherBlocks;
import com.brand.netherthings.entities.NetherEntities;
import com.brand.netherthings.features.structure.NetherThingsStructureFeature;
import com.brand.netherthings.items.NetherItems;
import com.brand.netherthings.items.NetherSets;
import com.brand.netherthings.stuff.NetherStuff;
import com.brand.netherthings.world.NetherOres;
import com.brand.netherthings.world.NetherVegetation;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class NetherThings implements ModInitializer {
	
	public static final String MOD_ID = "netherthings";
	public static final String VERSION = "1.1.3.1";
	public static final String NAME = "NetherThings";
	public static final NetherThingsConfig CONFIG = AutoConfig.register(NetherThingsConfig.class, GsonConfigSerializer::new).getConfig();
	public static final ItemGroup NETHER_THINGS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "nether_things_group"), () -> new ItemStack(Ores.NETHER_VIBRANIUM_ORE));
	
	@Override
	public void onInitialize() {
		Ores.init();
		OtherBlocks.init();
		
		NetherOres.addNetherOres();
		NetherOres.addVibraniumOres();
		NetherOres.addOverworldOres();
		NetherOres.addNetherMineables();
		NetherVegetation.addNetherVegetation();
		
		new NetherItems();
		new NetherEntities();
		new NetherStuff();
		NetherSets.registerItems();
		
		NetherSurfaces.init();
		NetherBiomes.init();
		NetherThingsStructureFeature.init();

		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.NETHER_CACTUS, 0.50f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.BLUE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GREEN_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.PURPLE_GLOWING_MUSHROOM, 0.65f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.BLUE_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.GREEN_GLOWING_MUSHROOM_BLOCK, 0.85f);
		CompostingChanceRegistry.INSTANCE.add(OtherBlocks.PURPLE_GLOWING_MUSHROOM_BLOCK, 0.85f);
	}
}

