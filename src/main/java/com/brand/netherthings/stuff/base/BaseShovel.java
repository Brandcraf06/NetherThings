package com.brand.netherthings.stuff.base;

import com.brand.netherthings.NetherThings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class BaseShovel extends ShovelItem
{
    public BaseShovel(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, -0.5f, -3.0F, new Item.Settings().group(NetherThings.GROUP));
    }
}
