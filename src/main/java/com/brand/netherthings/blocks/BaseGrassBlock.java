package com.brand.netherthings.blocks;


import com.brand.netherthings.NetherThings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BaseGrassBlock extends Block {

    public BaseGrassBlock(String name, float hardness, float resistance) {
        super(FabricBlockSettings.of(Material.LEAVES).sounds(BlockSoundGroup.GRASS).strength(hardness, resistance));
        Registry.register(Registries.BLOCK, new Identifier(NetherThings.MOD_ID, name), this);
        Registry.register(Registries.ITEM, new Identifier(NetherThings.MOD_ID, name), new BlockItem(this, new Item.Settings().maxCount(64)));
    }
}
