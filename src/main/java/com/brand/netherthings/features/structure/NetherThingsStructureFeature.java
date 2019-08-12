package com.brand.netherthings.features.structure;

import java.util.Locale;

import com.brand.netherthings.features.structure.BlazeTemple.BlazeTempleFeature;
import com.brand.netherthings.features.structure.BlazeTemple.BlazeTempleGenerator;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class NetherThingsStructureFeature
	{
	    public static final StructurePieceType BLAZE_TEMPLE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "blaze_temple_piece", BlazeTempleGenerator.Piece::new);
	    public static StructureFeature<DefaultFeatureConfig> blazetempleFeature = registerFeature("blaze_temple", new BlazeTempleFeature());
	    public static StructureFeature<?> BLAZE_TEMPLE = registerStructureFeature("blaze_temple", blazetempleFeature);

	    public static void init()
	    {
	        // add our structure to the structure list
	        Feature.STRUCTURES.put("Blaze Temple", blazetempleFeature);

	        // register our structure in n
	        for(Biome biome : Registry.BIOME)
	        {
	            if(biome.getCategory() == Biome.Category.NETHER)
	            {
	                biome.addStructureFeature(blazetempleFeature, new DefaultFeatureConfig());
	                biome.addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, Biome.configureFeature(blazetempleFeature, new DefaultFeatureConfig(), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(300)));
	            }
	        }
	    }

	    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String string_1, F feature_1) {
	        return Registry.register(Registry.FEATURE, string_1, feature_1);
	    }

	    private static StructureFeature<?> registerStructureFeature(String string_1, StructureFeature<?> structureFeature_1) {
	        return Registry.register(Registry.STRUCTURE_FEATURE, string_1.toLowerCase(Locale.ROOT), structureFeature_1);
	    }
	}