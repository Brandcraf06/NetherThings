package com.brand.netherthings.stuff.base;

import com.brand.netherthings.NetherThings;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BaseAxe extends AxeItem
{
    public BaseAxe(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, 3, -3.0f, new Item.Settings().group(NetherThings.NETHER_THINGS_GROUP));
    }
}
