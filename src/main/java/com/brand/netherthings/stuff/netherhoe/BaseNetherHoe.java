package com.brand.netherthings.stuff.netherhoe;

import com.brand.netherthings.NetherThings;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BaseNetherHoe extends NetherHoeItem
{
    protected BaseNetherHoe(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, -1.0F, new Item.Settings().group(NetherThings.NETHER_THINGS_GROUP));
    }
}
