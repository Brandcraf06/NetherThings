package com.brand.netherthings.stuff.basevibranium;

import com.brand.netherthings.NetherThings;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BaseVibraniumSword extends SwordItem
{
    protected BaseVibraniumSword(ToolMaterial toolMaterial_1)
    {
        super(toolMaterial_1, 1, -2.0f, new Item.Settings().group(NetherThings.NETHER_THINGS_GROUP));
    }
}
