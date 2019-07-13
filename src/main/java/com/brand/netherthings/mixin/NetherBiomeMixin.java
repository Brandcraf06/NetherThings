package com.brand.netherthings.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.brand.netherthings.entities.NetherEntities;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.EntityCategory;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.NetherBiome;

@Mixin(NetherBiome.class)
	public class NetherBiomeMixin extends Biome {

	    protected NetherBiomeMixin(Settings settings) {
	        super(settings);
	        throw new IllegalStateException();
	    }

	    @Inject(method = "<init>*", at = @At("RETURN"))
	    private void onConstruct(CallbackInfo info) {
	    	super.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(NetherEntities.WITHER_PIGMAN, 0, 1, 4));
	    }
	}
