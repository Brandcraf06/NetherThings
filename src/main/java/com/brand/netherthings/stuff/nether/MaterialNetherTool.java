package com.brand.netherthings.stuff.nether;

import com.brand.netherthings.NetherThings;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MaterialNetherTool implements ToolMaterial
{
    @Override
    public int getDurability()
    {
        return NetherThings.CONFIG.netherToolDurability;
    }

    @Override
    public float getMiningSpeed()
    {
        return NetherThings.CONFIG.netherToolSpeed;
    }

    @Override
    public float getAttackDamage()
    {
        return NetherThings.CONFIG.netherToolDamage;
    }

    @Override
    public int getMiningLevel()
    {
        return 2;
    }

    @Override
    public int getEnchantability()
    {
        return NetherThings.CONFIG.netherToolEnchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return Ingredient.ofItems(Items.NETHER_BRICK);
    }
}
