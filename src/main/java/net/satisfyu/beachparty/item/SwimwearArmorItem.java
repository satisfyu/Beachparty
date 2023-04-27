package net.satisfyu.beachparty.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.satisfyu.beachparty.registry.MaterialsRegistry;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class SwimwearArmorItem extends ArmorItem {
	private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
			(new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
					.put(MaterialsRegistry.BIKINI, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 1))
					.put(MaterialsRegistry.TRUNKS, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 1))
					.put(MaterialsRegistry.SWIM_WINGS, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 0))
					.put(MaterialsRegistry.RING, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 1)).build();

	public SwimwearArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient()) {
			if(entity instanceof PlayerEntity player) {
				if (hasSwimwearSet(player)) {
					addStatusEffectForMaterial(player, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 2));
					addStatusEffectForMaterial(player, new StatusEffectInstance(StatusEffects.WATER_BREATHING, 14*20, 0));
				} else {
					hasSwimwear(player);
				}
			}
		}
		super.inventoryTick(stack, world, entity, slot, selected);
	}

	private boolean hasSwimwearSet(PlayerEntity player) {
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack breastplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return (!helmet.isEmpty() && !breastplate.isEmpty() && !leggings.isEmpty()) &&
				hasCorrectSwimSet(player);
	}

	private void hasSwimwear(PlayerEntity player) {
		for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
			ArmorMaterial mapArmorMaterial = entry.getKey();
			StatusEffectInstance mapStatusEffect = entry.getValue();

			if(hasCorrectSwimWear(mapArmorMaterial, player)) {
				addStatusEffectForMaterial(player, mapStatusEffect);
			}
		}
	}

	private boolean hasCorrectSwimWear(ArmorMaterial material, PlayerEntity player) {
		if (material.equals(MaterialsRegistry.BIKINI) || material.equals(MaterialsRegistry.TRUNKS) || material.equals(MaterialsRegistry.RING)) {
			int slot = 1;
			if(!player.getInventory().getArmorStack(slot).isEmpty()) {
				ArmorItem armor = (ArmorItem) player.getInventory().getArmorStack(slot).getItem();
				return armor.getMaterial() == material;
			}
			return false;
		}
		if (material.equals(MaterialsRegistry.SWIM_WINGS)) {
			int slot = 2;
			if(!player.getInventory().getArmorStack(slot).isEmpty()) {
				ArmorItem armor = (ArmorItem) player.getInventory().getArmorStack(slot).getItem();
				return armor.getMaterial() == material;
			}
			return false;
		}
		return false;
	}

	private boolean hasCorrectSwimSet(PlayerEntity player) {
		//ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
		ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

		return (leggings.getMaterial() == MaterialsRegistry.TRUNKS || leggings.getMaterial() == MaterialsRegistry.TRUNKS) &&
				(breastplate.getMaterial() == MaterialsRegistry.RING || breastplate.getMaterial() == MaterialsRegistry.SWIM_WINGS) &&
				(helmet.getMaterial() == MaterialsRegistry.SUNGLASSES || helmet.getMaterial() == MaterialsRegistry.BEACH_HAT);
	}

	private void addStatusEffectForMaterial(PlayerEntity player, StatusEffectInstance mapStatusEffect) {
		boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

		if (!hasPlayerEffect || Objects.requireNonNull(player.getStatusEffect(mapStatusEffect.getEffectType())).getDuration() < 11 * 20) {
			player.addStatusEffect(new StatusEffectInstance(mapStatusEffect.getEffectType(),
					mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		tooltip.add(Text.translatable(  "tooltip.beachparty.swimwearline1").formatted(Formatting.DARK_PURPLE));
		tooltip.add(Text.translatable(  "tooltip.beachparty.swimwearline2").formatted(Formatting.BLUE));

	}
}
