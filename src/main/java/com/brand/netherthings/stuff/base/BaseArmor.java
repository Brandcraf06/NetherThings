package com.brand.netherthings.stuff.base;

import com.brand.netherthings.NetherThings;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class BaseArmor extends ArmorItem
{
    public BaseArmor(ArmorMaterial armorMaterial_1, EquipmentSlot equipmentSlot_1)
    {
        super(armorMaterial_1, equipmentSlot_1, new Item.Settings().group(NetherThings.NETHER_THINGS_GROUP));
    }
}
