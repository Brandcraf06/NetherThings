package com.brand.netherthings.stuff.vibranium;

import com.brand.netherthings.items.NetherItems;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MaterialVibraniumTool implements ToolMaterial
{
    @Override
    public int getDurability()
    {
        return 21850;
    }

    @Override
    public float getBlockBreakingSpeed()
    {
        return 22;
    }

    @Override
    public float getAttackDamage()
    {
        return 10.0f;
    }

    @Override
    public int getMiningLevel()
    {
        return 3;
    }

    @Override
    public int getEnchantability()
    {
        return 7;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(NetherItems.VIBRANIUM);
    }
}
