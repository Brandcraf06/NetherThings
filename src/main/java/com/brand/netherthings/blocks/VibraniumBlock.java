package com.brand.netherthings.blocks;

import com.brand.netherthings.NetherThings;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("deprecation")
public class VibraniumBlock extends Block {
	
public VibraniumBlock(String name, float hardness, float resistance) {
	super(FabricBlockSettings.of(Material.METAL).breakByTool(FabricToolTags.PICKAXES, 2).strength(hardness, resistance).build());
	Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
	Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.NETHER_THINGS_GROUP)));

    }
}
