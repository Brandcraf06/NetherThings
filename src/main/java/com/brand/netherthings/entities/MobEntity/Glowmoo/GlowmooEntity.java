package com.brand.netherthings.entities.MobEntity.Glowmoo;

import com.brand.netherthings.content.NetherBlocks;
import com.brand.netherthings.content.NetherItems;
import com.brand.netherthings.entities.NetherEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SuspiciousStewIngredient;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class GlowmooEntity extends CowEntity implements Shearable, VariantHolder<GlowmooEntity.Type> {
    private static final TrackedData<String> TYPE;
    private static final int MUTATION_CHANCE = 1024;
    @Nullable
    private StatusEffect stewEffect;
    private int stewEffectDuration;
    @Nullable
    private UUID lightningId;

    public GlowmooEntity(EntityType<? extends GlowmooEntity> entityType, World world) {
        super(entityType, world);
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos.down()).isOf(Blocks.MYCELIUM) ? 10.0F : world.getPhototaxisFavor(pos);
    }

    public static boolean canSpawn(EntityType<GlowmooEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(BlockTags.MOOSHROOMS_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TYPE, GlowmooEntity.Type.BLUE.name);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BOWL) && !this.isBaby()) {
            boolean bl = false;
            ItemStack itemStack2;
            {
                itemStack2 = new ItemStack(NetherItems.GLOWING_STEW);
            }

            ItemStack itemStack3 = ItemUsage.exchangeStack(itemStack, player, itemStack2, false);
            player.setStackInHand(hand, itemStack3);
            SoundEvent soundEvent;
            {
                soundEvent = SoundEvents.ENTITY_MOOSHROOM_MILK;
            }

            this.playSound(soundEvent, 1.0F, 1.0F);
            return ActionResult.success(this.getWorld().isClient);
        } else if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.getWorld().isClient) {
                itemStack.damage(1, player, (playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                });
            }

            return ActionResult.success(this.getWorld().isClient);
        }
        {
            Optional<Pair<StatusEffect, Integer>> optional = this.getStewEffectFrom(itemStack);
            if (!optional.isPresent()) {
                return ActionResult.PASS;
            }

            Pair<StatusEffect, Integer> pair = optional.get();
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            for (int j = 0; j < 4; ++j) {
                this.getWorld().addParticle(ParticleTypes.EFFECT, this.getX() + this.random.nextDouble() / 2.0, this.getBodyY(0.5), this.getZ() + this.random.nextDouble() / 2.0, 0.0, this.random.nextDouble() / 5.0, 0.0);
            }

            this.stewEffect = pair.getLeft();
            this.stewEffectDuration = pair.getRight();
            this.playSound(SoundEvents.ENTITY_MOOSHROOM_EAT, 2.0F, 1.0F);
        }

        return ActionResult.success(this.getWorld().isClient);
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        if (!this.getWorld().isClient()) {
            CowEntity cowEntity = EntityType.COW.create(this.getWorld());
            if (cowEntity != null) {
                ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                this.discard();
                cowEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
                cowEntity.setHealth(this.getHealth());
                cowEntity.bodyYaw = this.bodyYaw;
                if (this.hasCustomName()) {
                    cowEntity.setCustomName(this.getCustomName());
                    cowEntity.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistent()) {
                    cowEntity.setPersistent();
                }

                cowEntity.setInvulnerable(this.isInvulnerable());
                this.getWorld().spawnEntity(cowEntity);

                for (int i = 0; i < 5; ++i) {
                    this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getBodyY(1.0), this.getZ(), new ItemStack(this.getVariant().mushroom.getBlock())));
                }
            }
        }

    }

    public boolean isShearable() {
        return this.isAlive() && !this.isBaby();
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getVariant().asString());
        if (this.stewEffect != null) {
            nbt.putInt("EffectId", StatusEffect.getRawId(this.stewEffect));
            nbt.putInt("EffectDuration", this.stewEffectDuration);
        }

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(GlowmooEntity.Type.fromName(nbt.getString("Type")));
        if (nbt.contains("EffectId", 99)) {
            this.stewEffect = StatusEffect.byRawId(nbt.getInt("EffectId"));
        }

        if (nbt.contains("EffectDuration", 99)) {
            this.stewEffectDuration = nbt.getInt("EffectDuration");
        }

    }

    private Optional<Pair<StatusEffect, Integer>> getStewEffectFrom(ItemStack flower) {
        SuspiciousStewIngredient suspiciousStewIngredient = SuspiciousStewIngredient.of(flower.getItem());
        return suspiciousStewIngredient != null ? Optional.of(Pair.of(suspiciousStewIngredient.getEffectInStew(), suspiciousStewIngredient.getEffectInStewDuration())) : Optional.empty();
    }

    public void setVariant(GlowmooEntity.Type type) {
        this.dataTracker.set(TYPE, type.name);
    }

    public GlowmooEntity.Type getVariant() {
        return GlowmooEntity.Type.fromName(this.dataTracker.get(TYPE));
    }

    @Nullable
    public GlowmooEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        GlowmooEntity mooshroomEntity = NetherEntities.GLOWMOO.create(serverWorld);
        return mooshroomEntity;
    }

    static {
        TYPE = DataTracker.registerData(GlowmooEntity.class, TrackedDataHandlerRegistry.STRING);
    }

    public enum Type implements StringIdentifiable {
        BLUE("blue", NetherBlocks.BLUE_GLOWSHROOM_BLOCK.getDefaultState()),
        GREEN("green", NetherBlocks.GREEN_GLOWSHROOM_BLOCK.getDefaultState()),
        PURPLE("purple", NetherBlocks.PURPLE_GLOWSHROOM_BLOCK.getDefaultState());

        public static final StringIdentifiable.Codec<GlowmooEntity.Type> CODEC = StringIdentifiable.createCodec(GlowmooEntity.Type::values);
        final String name;
        final BlockState mushroom;

        Type(String name, BlockState mushroom) {
            this.name = name;
            this.mushroom = mushroom;
        }

        public BlockState getMushroomState() {
            return this.mushroom;
        }

        public String asString() {
            return this.name;
        }

        static GlowmooEntity.Type fromName(String name) {
            return CODEC.byId(name, BLUE);
        }
    }
}
