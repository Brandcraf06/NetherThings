package com.brand.netherthings.features.structure.BlazeTemple;

import java.util.List;
import java.util.Random;

import com.brand.netherthings.features.structure.NetherThingsStructureFeature;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class BlazeTempleGenerator {
	
	private static final Identifier BLAZE_TEMPLE = new Identifier("igloo/top");
	
	static void addParts(StructureManager structureManager, BlockPos blockPos, BlockRotation rotation, List<StructurePiece> pieceList, Random random, DefaultFeatureConfig featureConfig)
    {
        pieceList.add(new BlazeTempleGenerator.Piece(structureManager, BLAZE_TEMPLE, blockPos, rotation));
	}

		
		public static class Piece extends SimpleStructurePiece {
		    private BlockRotation rotation;
		    private Identifier template;
		 
		 public Piece(StructureManager structureManager, Identifier identifier, BlockPos blockPos, BlockRotation rotation) {
		        super(NetherThingsStructureFeature.BLAZE_TEMPLE_PIECE, 0);
		 
		        this.pos = blockPos;
		        this.rotation = rotation;
		        this.template = identifier;
		 
		        this.setStructureData(structureManager);
		    }
		 
		    public Piece(StructureManager structureManager, CompoundTag compoundTag) {
		        super(NetherThingsStructureFeature.BLAZE_TEMPLE_PIECE, compoundTag);
		        this.template = new Identifier(compoundTag.getString("Template"));
		        this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));
		        this.setStructureData(structureManager);
		    }
		 
		    @Override
		    protected void toNbt(CompoundTag compoundTag) {
		        super.toNbt(compoundTag);
		        compoundTag.putString("Template", this.template.toString());
		        compoundTag.putString("Rot", this.rotation.name());
		    }
		 
		    public void setStructureData(StructureManager structureManager) {
		        Structure structure = structureManager.getStructureOrBlank(this.template);
		        StructurePlacementData structurePlacementData = (new StructurePlacementData()).setRotation(this.rotation).setMirrored(BlockMirror.NONE).setPosition(pos).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
		        this.setStructureData(structure, this.pos, structurePlacementData);
		    }
		 
		    @Override
		    protected void handleMetadata(String string, BlockPos blockPos, IWorld iWorld, Random random, MutableIntBoundingBox mutableIntBoundingBox) {
		 
		    }
		 
		    @Override
		    public boolean generate(IWorld iWorld, Random random, MutableIntBoundingBox mutableIntBoundingBox, ChunkPos chunkPos) {
		        this.pos = this.pos.add(0, - 1, 0);
		        return super.generate(iWorld, random, mutableIntBoundingBox, chunkPos);
		    }
		 
		    }
		}	 


