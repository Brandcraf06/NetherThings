package com.brand.netherthings.stuff.nether;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MaterialNetherTool implements ToolMaterial
{
    @Override
    public int getDurability()
    {
        return 280;
    }

    @Override
    public float getBlockBreakingSpeed()
    {
        return 5;
    }

    @Override
    public float getAttackDamage()
    {
        return 3.5f;
    }

    @Override
    public int getMiningLevel()
    {
        return 3;
    }

    @Override
    public int getEnchantability()
    {
        return 77;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(Items.NETHER_BRICK);
    }
}
