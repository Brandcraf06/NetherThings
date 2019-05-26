package com.brand.netherthings.stuff.nether;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.stuff.NetherStuff;
import com.brand.netherthings.stuff.base.BaseArmor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class NetherArmorSet
{
    public static void registerArmor()
    {
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_helmet", new BaseArmor(NetherStuff.materialNetherArmor, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_chestplate", new BaseArmor(NetherStuff.materialNetherArmor, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_leggings", new BaseArmor(NetherStuff.materialNetherArmor, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":nether_boots", new BaseArmor(NetherStuff.materialNetherArmor, EquipmentSlot.FEET));
    }
}
