package com.brand.netherthings.entities.MobEntity.Glowmoo;

import com.brand.netherthings.contentNew.NetherBlocks;
import com.brand.netherthings.items.NetherItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.client.render.entity.MooshroomEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class GlowmooEntity extends CowEntity implements Shearable {
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
        return world.getBlockState(pos.down()).isOf(Blocks.MYCELIUM) ? 10.0F : world.getBrightness(pos) - 0.5F;
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
            return ActionResult.success(this.world.isClient);
        } else if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.world.isClient) {
                itemStack.damage(1, player, (playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                });
            }

            return ActionResult.success(this.world.isClient);
        }     {
                Optional<Pair<StatusEffect, Integer>> optional = this.getStewEffectFrom(itemStack);
                if (!optional.isPresent()) {
                    return ActionResult.PASS;
                }

                Pair<StatusEffect, Integer> pair = (Pair)optional.get();
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                for(int j = 0; j < 4; ++j) {
                    this.world.addParticle(ParticleTypes.EFFECT, this.getX() + this.random.nextDouble() / 2.0D, this.getBodyY(0.5D), this.getZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
                }

                this.stewEffect = (StatusEffect)pair.getLeft();
                this.stewEffectDuration = (Integer)pair.getRight();
                this.playSound(SoundEvents.ENTITY_MOOSHROOM_EAT, 2.0F, 1.0F);
            }

            return ActionResult.success(this.world.isClient);
        }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.world.playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        if (!this.world.isClient()) {
            ((ServerWorld)this.world).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            this.discard();
            CowEntity cowEntity = (CowEntity)EntityType.COW.create(this.world);
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
            this.world.spawnEntity(cowEntity);

            for(int i = 0; i < 5; ++i) {
                this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getBodyY(1.0D), this.getZ(), new ItemStack(this.getMooshroomType().mushroom.getBlock())));
            }
        }

    }

    public boolean isShearable() {
        return this.isAlive() && !this.isBaby();
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getMooshroomType().name);
        if (this.stewEffect != null) {
            nbt.putByte("EffectId", (byte)StatusEffect.getRawId(this.stewEffect));
            nbt.putInt("EffectDuration", this.stewEffectDuration);
        }

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setType(GlowmooEntity.Type.fromName(nbt.getString("Type")));
        if (nbt.contains("EffectId", 1)) {
            this.stewEffect = StatusEffect.byRawId(nbt.getByte("EffectId"));
        }

        if (nbt.contains("EffectDuration", 3)) {
            this.stewEffectDuration = nbt.getInt("EffectDuration");
        }

    }

    private Optional<Pair<StatusEffect, Integer>> getStewEffectFrom(ItemStack flower) {
        Item item = flower.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem)item).getBlock();
            if (block instanceof FlowerBlock) {
                FlowerBlock flowerBlock = (FlowerBlock)block;
                return Optional.of(Pair.of(flowerBlock.getEffectInStew(), flowerBlock.getEffectInStewDuration()));
            }
        }

        return Optional.empty();
    }

    private void setType(GlowmooEntity.Type type) {
        this.dataTracker.set(TYPE, type.name);
    }

    public GlowmooEntity.Type getMooshroomType() {
        return GlowmooEntity.Type.fromName(this.dataTracker.get(TYPE));
    }

    static {
        TYPE = DataTracker.registerData(GlowmooEntity.class, TrackedDataHandlerRegistry.STRING);
    }

    public static enum Type {
        BLUE("blue", NetherBlocks.BLUE_GLOWING_MUSHROOM.getDefaultState()),
        GREEN("green", NetherBlocks.GREEN_GLOWING_MUSHROOM.getDefaultState()),
        PURPLE("purple", NetherBlocks.PURPLE_GLOWING_MUSHROOM.getDefaultState());

        final String name;
        final BlockState mushroom;

        private Type(String name, BlockState mushroom) {
            this.name = name;
            this.mushroom = mushroom;
        }

        public BlockState getMushroomState() {
            return this.mushroom;
        }

        static GlowmooEntity.Type fromName(String name) {
            GlowmooEntity.Type[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                GlowmooEntity.Type type = var1[var3];
                if (type.name.equals(name)) {
                    return type;
                }
            }

            return BLUE;
        }
    }
}
