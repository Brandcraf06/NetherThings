package com.brand.netherthings.stuff.glowstone;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.items.NetherItems;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class MaterialGlowstoneArmor implements ArmorMaterial
{
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNTS = NetherThings.CONFIG.glowstoneArmorProtectionAmounts;

    @Override
    public int getDurability(EquipmentSlot equipmentSlot)
    {
        return BASE_DURABILITY[equipmentSlot.getEntitySlotId()] * NetherThings.CONFIG.glowstoneArmorDurabilityModifier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot equipmentSlot)
    {
        return PROTECTION_AMOUNTS[equipmentSlot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability()
    {
        return NetherThings.CONFIG.glowstoneArmorEnchantability;
    }

    @Override
    public SoundEvent getEquipSound()
    {
        return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(NetherItems.GLOWSTONE_INGOT);
    }

    @Override
    public String getName()
    {
        return "glowstone";
    }

    @Override
    public float getToughness()
    {
        return 0;
    }
}
