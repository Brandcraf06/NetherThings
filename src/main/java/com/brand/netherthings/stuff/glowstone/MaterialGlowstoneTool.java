package com.brand.netherthings.stuff.glowstone;

import com.brand.netherthings.items.GlowstoneIngot;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MaterialGlowstoneTool implements ToolMaterial
{
    @Override
    public int getDurability()
    {
        return 442;
    }

    @Override
    public float getBlockBreakingSpeed()
    {
        return 5;
    }

    @Override
    public float getAttackDamage()
    {
        return 4.7f;
    }

    @Override
    public int getMiningLevel()
    {
        return 3;
    }

    @Override
    public int getEnchantability()
    {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(GlowstoneIngot.GLOWSTONE_INGOT);
    }
}
