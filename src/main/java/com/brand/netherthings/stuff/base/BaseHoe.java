package com.brand.netherthings.stuff.base;

import com.brand.netherthings.NetherThings;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class BaseHoe extends HoeItem
{
    protected BaseHoe(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, -1.0F, new Item.Settings().group(NetherThings.GROUP));
    }
}
