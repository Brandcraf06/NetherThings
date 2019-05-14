package com.brand.netherthings.features;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.*;

public abstract class NetherThingsFeatures<FC extends FeatureConfig> {
	
	   public static final Feature<DefaultFeatureConfig> NETHER_CACTUS;
	   public static final Feature<PlantedFeatureConfig> HUGE_GREEN_GLOWING_MUSHROOM;
	   public static final Feature<PlantedFeatureConfig> HUGE_BLUE_GLOWING_MUSHROOM;
	   public static final Feature<PlantedFeatureConfig> HUGE_PURPLE_GLOWING_MUSHROOM;
	   private final Function<Dynamic<?>, ? extends FC> configDeserializer;
	   protected final boolean emitNeighborBlockUpdates;

	   private static <C extends FeatureConfig, F extends Feature<C>> F register(String string_1, F feature_1) {
	      return (F)Registry.register(Registry.FEATURE, (String)string_1, feature_1);
	   }

	   public NetherThingsFeatures(Function<Dynamic<?>, ? extends FC> function_1) {
	      this.configDeserializer = function_1;
	      this.emitNeighborBlockUpdates = false;
	   }

	   public NetherThingsFeatures(Function<Dynamic<?>, ? extends FC> function_1, boolean boolean_1) {
	      this.configDeserializer = function_1;
	      this.emitNeighborBlockUpdates = boolean_1;
	   }

	   public FC deserializeConfig(Dynamic<?> dynamic_1) {
	      return (FC)this.configDeserializer.apply(dynamic_1);
	   }

	   protected void setBlockState(ModifiableWorld modifiableWorld_1, BlockPos blockPos_1, BlockState blockState_1) {
	      if (this.emitNeighborBlockUpdates) {
	         modifiableWorld_1.setBlockState(blockPos_1, blockState_1, 3);
	      } else {
	         modifiableWorld_1.setBlockState(blockPos_1, blockState_1, 2);
	      }

	   }

	   public abstract boolean generate(IWorld var1, ChunkGenerator<? extends ChunkGeneratorConfig> var2, Random var3, BlockPos var4, FC var5);

	   public List<Biome.SpawnEntry> getMonsterSpawns() {
	      return Collections.emptyList();
	   }

	   public List<Biome.SpawnEntry> getCreatureSpawns() {
	      return Collections.emptyList();
	   }

	   static {
		  NETHER_CACTUS = register("nether_cactus", new NetherCactusFeature(DefaultFeatureConfig::deserialize));
		  HUGE_GREEN_GLOWING_MUSHROOM = register("huge_green_glowing_mushroom", new HugeGreenGlowingMushroomFeature(PlantedFeatureConfig::deserialize));
		  HUGE_BLUE_GLOWING_MUSHROOM = register("huge_blue_glowing_mushroom", new HugeBlueGlowingMushroomFeature(PlantedFeatureConfig::deserialize));
		  HUGE_PURPLE_GLOWING_MUSHROOM = register("huge_purple_glowing_mushroom", new HugePurpleGlowingMushroomFeature(PlantedFeatureConfig::deserialize));
		  }
	   }
