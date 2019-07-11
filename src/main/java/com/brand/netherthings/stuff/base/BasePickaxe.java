package com.brand.netherthings.stuff.base;

import com.brand.netherthings.NetherThings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class BasePickaxe extends PickaxeItem
{
    public BasePickaxe(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, -1, -2.8f, new Item.Settings().group(NetherThings.GROUP));
    }
}
