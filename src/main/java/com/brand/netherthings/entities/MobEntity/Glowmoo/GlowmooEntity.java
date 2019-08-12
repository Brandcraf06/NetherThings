package com.brand.netherthings.entities.MobEntity.Glowmoo;

import java.util.Random;
import com.brand.netherthings.NetherThings;
import com.brand.netherthings.items.NetherItems;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class GlowmooEntity extends CowEntity {
    public final String id;
   
    public GlowmooEntity(EntityType<? extends GlowmooEntity> entityType, World world) {
        super(entityType, world);
        this.id = Registry.ENTITY_TYPE.getId(entityType).getPath();
    }
    
    public float getPathfindingFavor(BlockPos blockPos_1, ViewableWorld viewableWorld_1) {
        return viewableWorld_1.getBlockState(blockPos_1.down()).getBlock() == Blocks.GRASS_BLOCK ? 10.0F : viewableWorld_1.getBrightness(blockPos_1) - 0.1F;
     }

     public static boolean method_20665(EntityType<MooshroomEntity> entityType_1, IWorld iWorld_1, SpawnType spawnType_1, BlockPos blockPos_1, Random random_1) {
        return iWorld_1.getBlockState(blockPos_1.down()).getBlock() == Blocks.GRASS_BLOCK && iWorld_1.getLightLevel(blockPos_1, 0) > 8;
        
     }
     
   
    public boolean interactMob(PlayerEntity player, Hand hand) {
    	ItemStack itemStack_1 = player.getStackInHand(hand);
        if (itemStack_1.getItem() == Items.BOWL && this.getBreedingAge() >= 0 && !player.abilities.creativeMode) {
           itemStack_1.decrement(1);
           ItemStack itemStack_3;
           itemStack_3 = new ItemStack(NetherItems.GLOWING_STEW);
           if (itemStack_1.isEmpty()) {
              player.setStackInHand(hand, itemStack_3);
           } else if (!player.inventory.insertStack(itemStack_3)) {
              player.dropItem(itemStack_3, false);
           }

           SoundEvent soundEvent_2;
              soundEvent_2 = SoundEvents.ENTITY_MOOSHROOM_MILK;

           this.playSound(soundEvent_2, 1.0F, 1.0F);
           return true;
        } else {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() == Items.SHEARS && this.getBreedingAge() >= 0) {
            this.world.addParticle(ParticleTypes.EXPLOSION, this.x, this.y + this.getHeight() / 2.0F, this.z, 0.0D, 0.0D, 0.0D);
            if (!this.world.isClient) {
                this.remove();
                CowEntity cow = EntityType.COW.create(this.world);
                cow.setPositionAndAngles(this.x, this.y, this.z, this.yaw, this.pitch);
                cow.setHealth(this.getHealth());
                cow.field_6283 = this.field_6283;
                if (this.hasCustomName()) {
                    cow.setCustomName(this.getCustomName());
                }
                this.world.spawnEntity(cow);
                for(int i = 0; i < 5; ++i) {
                    this.world.spawnEntity(new ItemEntity(this.world, this.x, this.y + this.getHeight(), this.z, new ItemStack(this.getMushroomType().getBlock())));
                }
                stack.damage(1, player, ((playerEntity) -> {
                    playerEntity.sendToolBreakStatus(hand);
                }));
                this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
            }
            return true;
        } else {
            return super.interactMob(player, hand);
        }
      }
    }
   
    @Override
    public GlowmooEntity createChild(PassiveEntity entity) {
        return (GlowmooEntity) Registry.ENTITY_TYPE.get(new Identifier(NetherThings.MOD_ID, this.id)).create(this.world);
    }
   
    public BlockState getMushroomType() {
        String mushroom = this.id.replace("glowmoo", "glowing_mushroom");
        BlockState state = Registry.BLOCK.get(new Identifier(NetherThings.MOD_ID, mushroom)).getDefaultState();
        return state;
    }
}
