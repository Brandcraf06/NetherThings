package com.brand.netherthings.stuff.netherhoe;

import java.util.Map;
import java.util.function.Consumer;

import com.brand.netherthings.content.OtherBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NetherHoeItem extends ToolItem {
	   private final float attackSpeed;
	   protected static final Map<Block, BlockState> MOD_TILLED_BLOCKS;

	   public NetherHoeItem(ToolMaterial toolMaterial_1, float float_1, Item.Settings item$Settings_1) {
	      super(toolMaterial_1, item$Settings_1);
	      this.attackSpeed = float_1;
	   }

	public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {
	      World world_1 = itemUsageContext_1.getWorld();
	      BlockPos blockPos_1 = itemUsageContext_1.getBlockPos();
	      if (itemUsageContext_1.getSide() != Direction.DOWN && world_1.getBlockState(blockPos_1.up()).isAir()) {
	         BlockState blockState_1 = (BlockState)MOD_TILLED_BLOCKS.get(world_1.getBlockState(blockPos_1).getBlock());
	         if (blockState_1 != null) {
	            PlayerEntity playerEntity_1 = itemUsageContext_1.getPlayer();
	            world_1.playSound(playerEntity_1, blockPos_1, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
	            if (!world_1.isClient) {
	               world_1.setBlockState(blockPos_1, blockState_1, 11);
	               if (playerEntity_1 != null) {
	                  itemUsageContext_1.getStack().damage(1, (LivingEntity)playerEntity_1, (Consumer)((playerEntity_1x) -> {
	                     ((LivingEntity) playerEntity_1x).sendToolBreakStatus(itemUsageContext_1.getHand());
	                  }));
	               }
	            }

	            return ActionResult.SUCCESS;
	         }
	      }

	      return ActionResult.PASS;
	   }

	   public boolean postHit(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2) {
	      itemStack_1.damage(1, (LivingEntity)livingEntity_2, (Consumer)((livingEntity_1x) -> {
	         ((LivingEntity) livingEntity_1x).sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
	      }));
	      return true;
	   }

	   public Multimap<String, EntityAttributeModifier> getModifiers(EquipmentSlot equipmentSlot_1) {
	      Multimap<String, EntityAttributeModifier> multimap_1 = super.getModifiers(equipmentSlot_1);
	      if (equipmentSlot_1 == EquipmentSlot.MAINHAND) {
	         multimap_1.put(EntityAttributes.ATTACK_DAMAGE.getId(), new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "Weapon modifier", 0.0D, EntityAttributeModifier.Operation.ADDITION));
	         multimap_1.put(EntityAttributes.ATTACK_SPEED.getId(), new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "Weapon modifier", (double)this.attackSpeed, EntityAttributeModifier.Operation.ADDITION));
	      }

	      return multimap_1;
	   }

	   static {
		   MOD_TILLED_BLOCKS = Maps.newHashMap(ImmutableMap.of(Blocks.SOUL_SAND, OtherBlocks.TILLED_SOUL_SAND.getDefaultState(), Blocks.GRASS_BLOCK, Blocks.FARMLAND.getDefaultState(), Blocks.GRASS_PATH, Blocks.FARMLAND.getDefaultState(), Blocks.DIRT, Blocks.FARMLAND.getDefaultState(), Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState()));
	   }
	}
