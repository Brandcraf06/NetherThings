package com.brand.netherthings.stuff.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BaseSword extends SwordItem
{
    protected BaseSword(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, 1, -2.4f, new Item.Settings().itemGroup(ItemGroup.COMBAT));
    }
}
