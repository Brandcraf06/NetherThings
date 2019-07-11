package com.brand.netherthings.blocks;

import com.brand.netherthings.NetherThings;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockBase extends Block {
	
public BlockBase(String name, float hardness, float resistance) {
	super(FabricBlockSettings.of(Material.STONE).strength(hardness, resistance).build());
	Registry.register(Registry.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
	Registry.register(Registry.ITEM,new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64).group(NetherThings.GROUP)));

    }
}
