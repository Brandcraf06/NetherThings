package com.brand.netherthings.blocks;


import com.brand.netherthings.NetherThings;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BaseGrassBlock extends Block {

	public BaseGrassBlock(String name, float hardness, float resistance) {
		super(FabricBlockSettings.of(Material.LEAVES).sounds(BlockSoundGroup.GRASS).breakByTool(FabricToolTags.SHOVELS, 0).strength(hardness, resistance).build());
		Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
		Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));
	}
}
