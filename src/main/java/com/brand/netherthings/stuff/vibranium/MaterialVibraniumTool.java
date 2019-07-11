package com.brand.netherthings.stuff.vibranium;

import com.brand.netherthings.NetherThings;
import com.brand.netherthings.items.NetherItems;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MaterialVibraniumTool implements ToolMaterial
{
    @Override
    public int getDurability()
    {
        return NetherThings.CONFIG.vibraniumToolDurability;
    }

    @Override
    public float getMiningSpeed()
    {
        return NetherThings.CONFIG.vibraniumToolSpeed;
    }

    @Override
    public float getAttackDamage()
    {
        return NetherThings.CONFIG.vibraniumToolDamage;
    }

    @Override
    public int getMiningLevel()
    {
        return 3;
    }

    @Override
    public int getEnchantability()
    {
        return NetherThings.CONFIG.vibraniumToolEnchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(NetherItems.VIBRANIUM);
    }
}
