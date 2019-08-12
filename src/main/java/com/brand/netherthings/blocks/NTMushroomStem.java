package com.brand.netherthings.blocks;


import com.brand.netherthings.NetherThings;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.MushroomBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;

public class NTMushroomStem extends MushroomBlock {
	
	public NTMushroomStem(String name, float hardness, float resistance) {
		super(FabricBlockSettings.of(Material.ORGANIC).sounds(BlockSoundGroup.SLIME).lightLevel(15).breakByTool(FabricToolTags.AXES, 0).strength(hardness, resistance).build());
		Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
		Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	}
  }
