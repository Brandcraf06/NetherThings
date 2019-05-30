package com.brand.netherthings.entities.MobEntity.WitherPigman;

import java.util.UUID;

import com.brand.netherthings.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class WitherPigmanEntity extends ZombieEntity {
	   private static final UUID ATTACKING_SPEED_BOOST_UUID = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
	   private static final EntityAttributeModifier ATTACKING_SPEED_BOOST;
	   private int anger;
	   private int angrySoundDelay;
	   private UUID angerTarget;

	   public WitherPigmanEntity(EntityType<? extends WitherPigmanEntity> entityType_1, World world_1) {
	      super(entityType_1, world_1);
	      this.setPathNodeTypeWeight(PathNodeType.LAVA, 8.0F);
	   }

	   public void setAttacker(@Nullable LivingEntity livingEntity_1) {
	      super.setAttacker(livingEntity_1);
	      if (livingEntity_1 != null) {
	         this.angerTarget = livingEntity_1.getUuid();
	      }

	   }

	   protected void initCustomGoals() {
	      this.goalSelector.add(2, new ZombieAttackGoal(this, 1.0D, false));
	      this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
	      this.targetSelector.add(1, new WitherPigmanEntity.AvoidZombiesGoal(this));
	      this.targetSelector.add(2, new WitherPigmanEntity.FollowPlayerIfAngryGoal(this));
	   }

	   protected void initAttributes() {
	      super.initAttributes();
	      this.getAttributeInstance(SPAWN_REINFORCEMENTS).setBaseValue(0.0D);
	      this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
	      this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	   }

	   protected boolean canConvertInWater() {
	      return false;
	   }

	   protected void mobTick() {
	      EntityAttributeInstance entityAttributeInstance_1 = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
	      if (this.isAngry()) {
	         if (!this.isBaby() && !entityAttributeInstance_1.hasModifier(ATTACKING_SPEED_BOOST)) {
	            entityAttributeInstance_1.addModifier(ATTACKING_SPEED_BOOST);
	         }

	         --this.anger;
	      } else if (entityAttributeInstance_1.hasModifier(ATTACKING_SPEED_BOOST)) {
	         entityAttributeInstance_1.removeModifier(ATTACKING_SPEED_BOOST);
	      }

	      if (this.angrySoundDelay > 0 && --this.angrySoundDelay == 0) {
	         this.playSound(SoundEvents.ENTITY_ZOMBIE_PIGMAN_ANGRY, this.getSoundVolume() * 2.0F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
	      }

	      if (this.anger > 0 && this.angerTarget != null && this.getAttacker() == null) {
	         PlayerEntity playerEntity_1 = this.world.getPlayerByUuid(this.angerTarget);
	         this.setAttacker(playerEntity_1);
	         this.attackingPlayer = playerEntity_1;
	         this.playerHitTimer = this.getLastAttackedTime();
	      }

	      super.mobTick();
	   }

	   public boolean canSpawn(IWorld iWorld_1, SpawnType spawnType_1) {
	      return iWorld_1.getDifficulty() != Difficulty.PEACEFUL;
	   }

	   public boolean canSpawn(ViewableWorld viewableWorld_1) {
	      return viewableWorld_1.intersectsEntities(this) && !viewableWorld_1.intersectsFluid(this.getBoundingBox());
	   }
	   
	   public boolean tryAttack(Entity entity_1) {
		      if (!super.tryAttack(entity_1)) {
		         return false;
		      } else {
		         if (entity_1 instanceof LivingEntity) {
		            ((LivingEntity)entity_1).addPotionEffect(new StatusEffectInstance(StatusEffects.WITHER, 200));
		         }

		         return true;
		      }
		   }

		   public boolean isPotionEffective(StatusEffectInstance statusEffectInstance_1) {
		      return statusEffectInstance_1.getEffectType() == StatusEffects.WITHER ? false : super.isPotionEffective(statusEffectInstance_1);
		   }

	   public void writeCustomDataToTag(CompoundTag compoundTag_1) {
	      super.writeCustomDataToTag(compoundTag_1);
	      compoundTag_1.putShort("Anger", (short)this.anger);
	      if (this.angerTarget != null) {
	         compoundTag_1.putString("HurtBy", this.angerTarget.toString());
	      } else {
	         compoundTag_1.putString("HurtBy", "");
	      }

	   }

	   public void readCustomDataFromTag(CompoundTag compoundTag_1) {
	      super.readCustomDataFromTag(compoundTag_1);
	      this.anger = compoundTag_1.getShort("Anger");
	      String string_1 = compoundTag_1.getString("HurtBy");
	      if (!string_1.isEmpty()) {
	         this.angerTarget = UUID.fromString(string_1);
	         PlayerEntity playerEntity_1 = this.world.getPlayerByUuid(this.angerTarget);
	         this.setAttacker(playerEntity_1);
	         if (playerEntity_1 != null) {
	            this.attackingPlayer = playerEntity_1;
	            this.playerHitTimer = this.getLastAttackedTime();
	         }
	      }

	   }

	   public boolean damage(DamageSource damageSource_1, float float_1) {
	      if (this.isInvulnerableTo(damageSource_1)) {
	         return false;
	      } else {
	         Entity entity_1 = damageSource_1.getAttacker();
	         if (entity_1 instanceof PlayerEntity && !((PlayerEntity)entity_1).isCreative()) {
	            this.copyEntityData(entity_1);
	         }

	         return super.damage(damageSource_1, float_1);
	      }
	   }

	   private void copyEntityData(Entity entity_1) {
	      this.anger = 400 + this.random.nextInt(400);
	      this.angrySoundDelay = this.random.nextInt(40);
	      if (entity_1 instanceof LivingEntity) {
	         this.setAttacker((LivingEntity)entity_1);
	      }

	   }

	   public boolean isAngry() {
	      return this.anger > 0;
	   }

	   protected SoundEvent getAmbientSound() {
	      return SoundEvents.ENTITY_ZOMBIE_PIGMAN_AMBIENT;
	   }

	   protected SoundEvent getHurtSound(DamageSource damageSource_1) {
	      return SoundEvents.ENTITY_ZOMBIE_PIGMAN_HURT;
	   }

	   protected SoundEvent getDeathSound() {
	      return SoundEvents.ENTITY_ZOMBIE_PIGMAN_DEATH;
	   }

	   public boolean interactMob(PlayerEntity playerEntity_1, Hand hand_1) {
	      return false;
	   }

	   protected void initEquipment(LocalDifficulty localDifficulty_1) {
	      this.setEquippedStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
	   }

	   protected ItemStack getSkull() {
	      return ItemStack.EMPTY;
	   }

	   public boolean isAngryAt(PlayerEntity playerEntity_1) {
	      return this.isAngry();
	   }

	   static {
	      ATTACKING_SPEED_BOOST = (new EntityAttributeModifier(ATTACKING_SPEED_BOOST_UUID, "Attacking speed boost", 0.05D, EntityAttributeModifier.Operation.ADDITION)).setSerialize(false);
	   }

	   static class FollowPlayerIfAngryGoal extends FollowTargetGoal<PlayerEntity> {
	      public FollowPlayerIfAngryGoal(WitherPigmanEntity witherPigmanEntity_1) {
	         super(witherPigmanEntity_1, PlayerEntity.class, true);
	      }

	      public boolean canStart() {
	         return ((WitherPigmanEntity)this.mob).isAngry() && super.canStart();
	      }
	   }

	   static class AvoidZombiesGoal extends RevengeGoal {
	      public AvoidZombiesGoal(WitherPigmanEntity witherPigmanEntity_1) {
	         super(witherPigmanEntity_1);
	         this.setGroupRevenge(new Class[]{ZombieEntity.class});
	      }

	      protected void setMobEntityTarget(MobEntity mobEntity_1, LivingEntity livingEntity_1) {
	         super.setMobEntityTarget(mobEntity_1, livingEntity_1);
	         if (mobEntity_1 instanceof WitherPigmanEntity) {
	            ((WitherPigmanEntity)mobEntity_1).copyEntityData(livingEntity_1);  
	         }
	      }     
	   }   
     }
