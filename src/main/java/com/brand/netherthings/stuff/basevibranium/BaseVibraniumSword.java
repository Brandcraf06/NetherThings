package com.brand.netherthings.stuff.basevibranium;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BaseVibraniumSword extends SwordItem
{
    protected BaseVibraniumSword(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, 1, -1.0f, new Item.Settings().group(ItemGroup.COMBAT));
    }
}
