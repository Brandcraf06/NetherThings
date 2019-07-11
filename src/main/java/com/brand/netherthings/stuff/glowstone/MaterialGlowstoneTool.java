package com.brand.netherthings.stuff.glowstone;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.items.NetherItems;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MaterialGlowstoneTool implements ToolMaterial
{
    @Override
    public int getDurability()
    {
        return NetherThings.CONFIG.glowstoneToolDurability;
    }

    @Override
    public float getMiningSpeed()
    {
        return NetherThings.CONFIG.glowstoneToolSpeed;
    }

    @Override
    public float getAttackDamage()
    {
        return NetherThings.CONFIG.glowstoneToolDamage;
    }

    @Override
    public int getMiningLevel()
    {
        return 2;
    }

    @Override
    public int getEnchantability()
    {
        return NetherThings.CONFIG.glowstoneToolEnchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(NetherItems.GLOWSTONE_INGOT);
    }
}
