package com.brand.netherthings.features.structure.BlazeTemple;

import java.util.Random;

import com.brand.netherthings.features.structure.NetherThingsStructureFeature;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class BlazeTempleFeature extends StructureFeature<DefaultFeatureConfig>
	{
	    public BlazeTempleFeature()
	    {
	        super(DefaultFeatureConfig::deserialize);
	    }
	
	public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator_1, Random random_1, int int_1, int int_2) {
	      int int_3 = int_1 >> 4;
	      int int_4 = int_2 >> 4;
	      random_1.setSeed((long)(int_3 ^ int_4 << 4) ^ chunkGenerator_1.getSeed());
	      random_1.nextInt();
	      if (random_1.nextInt(3) != 0) {
	         return false;
	      } else if (int_1 != (int_3 << 4) + 4 + random_1.nextInt(8)) {
	         return false;
	      } else if (int_2 != (int_4 << 4) + 4 + random_1.nextInt(8)) {
	         return false;
	      } else {
	         Biome biome_1 = chunkGenerator_1.getBiomeSource().getBiome(new BlockPos((int_1 << 4) + 9, 0, (int_2 << 4) + 9));
	         return chunkGenerator_1.hasStructure(biome_1, NetherThingsStructureFeature.blazetempleFeature);
	      }
	   }

	@Override
	public StructureStartFactory getStructureStartFactory() {
		return BlazeTempleStart::new;
	}

	@Override
	public String getName() {
		return "Blaze_temple";
	}

	@Override
	public int getRadius() {
		return 5;
	}

   public static class BlazeTempleStart extends StructureStart {
        public BlazeTempleStart (StructureFeature<?> structureFeature_1, int int_1, int int_2, Biome biome_1, MutableIntBoundingBox mutableIntBoundingBox_1, int int_3, long long_1) {
           super(structureFeature_1, int_1, int_2, biome_1, mutableIntBoundingBox_1, int_3, long_1);
    }
    @Override
    public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome) 
    {
    	
        DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, NetherThingsStructureFeature.blazetempleFeature);
        
        int x = chunkX * 16;
        int z = chunkZ * 16;
        
        BlockPos startingPos = new BlockPos(x, 0, z);
        
        BlockRotation rotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
        
        BlazeTempleGenerator.addParts(structureManager, startingPos, rotation, this.children, this.random, defaultFeatureConfig);
        this.setBoundingBoxFromChildren();
    }
  }
}
