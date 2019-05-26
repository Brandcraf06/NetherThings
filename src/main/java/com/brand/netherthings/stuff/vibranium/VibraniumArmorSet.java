package com.brand.netherthings.stuff.vibranium;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.stuff.NetherStuff;
import com.brand.netherthings.stuff.base.BaseArmor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class VibraniumArmorSet
{
    public static void registerArmor()
    {
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_helmet", new BaseArmor(NetherStuff.materialVibraniumArmor, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_chestplate", new BaseArmor(NetherStuff.materialVibraniumArmor, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_leggings", new BaseArmor(NetherStuff.materialVibraniumArmor, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":vibranium_boots", new BaseArmor(NetherStuff.materialVibraniumArmor, EquipmentSlot.FEET));
    }
}
