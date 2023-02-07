package com.brand.netherthings;


import com.brand.netherthings.content.NetherBlocks;
import com.brand.netherthings.content.NetherItems;
import com.brand.netherthings.content.NetherPotions;
import com.brand.netherthings.content.potions.NetherPotionsRecipes;
import com.brand.netherthings.entities.NetherEntities;
import com.brand.netherthings.features.NetherFeatures;
import com.brand.netherthings.worldgen.features.NetherWorldgenFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.HoeItem;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetherThings implements ModInitializer {

    public static final String MOD_ID = "netherthings";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
//    public static final ItemGroup NETHER_THINGS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "nether_things_group"), () -> new ItemStack(Ores.NETHER_DIAMOND_ORE));

    @Override
    public void onInitialize() {

        new NetherBlocks();
        new NetherItems();
        new NetherFeatures();
        new NetherPotions();
        new NetherEntities();

        NetherPotionsRecipes.registerRecipes();
        NetherWorldgenFeatures.registerConfiguredFeature();

        addTillables();

    }

    public static void addTillables() {
        TillableBlockRegistry.register(Blocks.SOUL_SAND, HoeItem::canTillFarmland, NetherBlocks.TILLED_SOUL_SAND.getDefaultState());


        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.DEAD_GRASS, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.CONDEMNED_LEAVES, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(NetherItems.BLAZING_BERRIES, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_SEEDS, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(NetherItems.QUARTZ_SEEDS, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(NetherItems.WITHER_SEEDS, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.NETHER_CACTUS, 0.50f);
        CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_WHEAT, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.GLOWING_REEDS, 0.65f);
//        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.BLUE_GLOWING_MUSHROOM, 0.65f);
//        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.GREEN_GLOWING_MUSHROOM, 0.65f);
//        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.PURPLE_GLOWING_MUSHROOM, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.BLUE_GLOWSHROOM_BLOCK, 0.85f);
        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.GREEN_GLOWSHROOM_BLOCK, 0.85f);
        CompostingChanceRegistry.INSTANCE.add(NetherBlocks.PURPLE_GLOWSHROOM_BLOCK, 0.85f);
        CompostingChanceRegistry.INSTANCE.add(NetherItems.GHOST_BREAD, 0.85f);
    }

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }
}

