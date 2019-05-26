package com.brand.netherthings.stuff.glowstone;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.stuff.NetherStuff;
import com.brand.netherthings.stuff.base.BaseArmor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class GlowstoneArmorSet
{
    public static void registerArmor()
    {
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_helmet", new BaseArmor(NetherStuff.materialGlowstoneArmor, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_chestplate", new BaseArmor(NetherStuff.materialGlowstoneArmor, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_leggings", new BaseArmor(NetherStuff.materialGlowstoneArmor, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, NetherThings.MOD_ID + ":glowstone_boots", new BaseArmor(NetherStuff.materialGlowstoneArmor, EquipmentSlot.FEET));
    }
}
