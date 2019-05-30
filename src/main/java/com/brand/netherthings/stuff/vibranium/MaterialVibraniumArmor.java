package com.brand.netherthings.stuff.vibranium;

import com.brand.netherthings.items.NetherItems;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class MaterialVibraniumArmor implements ArmorMaterial
{
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNTS = new int[]{5, 9, 10, 5};

    @Override
    public int getDurability(EquipmentSlot equipmentSlot)
    {
        return BASE_DURABILITY[equipmentSlot.getEntitySlotId()] * 1050;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot equipmentSlot)
    {
        return PROTECTION_AMOUNTS[equipmentSlot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability()
    {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound()
    {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(NetherItems.VIBRANIUM);
    }

    @Override
    public String getName()
    {
        return "vibranium";
    }

    @Override
    public float getToughness()
    {
        return 4;
    }
}
