package net.satisfyu.beachparty.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.MinecraftClient;
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


public class BeachpartyArmorItem extends ArmorItem {
	private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
			(new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
					.put(MaterialsRegistry.BIKINI, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 1))
					.put(MaterialsRegistry.TRUNKS, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 1))
					.put(MaterialsRegistry.SWIM_WINGS, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 0))
					.put(MaterialsRegistry.RING, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 1)).build();

	public BeachpartyArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient()) {
			if(entity instanceof PlayerEntity player) {
				if (hasSwimwearSet(player)) {
					addStatusEffectForMaterial(player, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 14*20, 2));
					addStatusEffectForMaterial(player, new StatusEffectInstance(StatusEffects.WATER_BREATHING, 14*20, 0));
				}
				hasSwimwear(player);
			}
		}
		super.inventoryTick(stack, world, entity, slot, selected);
	}

	private boolean hasSwimwearSet(PlayerEntity player) {
		return hasSwimearBoots(player) && hasSwimearLeggings(player) && hasSwimwearBreastplate(player) && hasSwimearHelmet(player);
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
		if (material.equals(MaterialsRegistry.BIKINI) || material.equals(MaterialsRegistry.TRUNKS)) {
			int slot = 1;
			if(!player.getInventory().getArmorStack(slot).isEmpty()) {
				ArmorItem armor = (ArmorItem) player.getInventory().getArmorStack(slot).getItem();
				return armor.getMaterial() == material;
			}
			return false;
		}
		if (material.equals(MaterialsRegistry.SWIM_WINGS) || material.equals(MaterialsRegistry.RING)) {
			int slot = 2;
			if(!player.getInventory().getArmorStack(slot).isEmpty()) {
				ArmorItem armor = (ArmorItem) player.getInventory().getArmorStack(slot).getItem();
				return armor.getMaterial() == material;
			}
			return false;
		}
		return false;
	}

	private void addStatusEffectForMaterial(PlayerEntity player, StatusEffectInstance mapStatusEffect) {
		boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

		if (!hasPlayerEffect || Objects.requireNonNull(player.getStatusEffect(mapStatusEffect.getEffectType())).getDuration() < 11 * 20) {
			player.addStatusEffect(new StatusEffectInstance(mapStatusEffect.getEffectType(),
					mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
		}
	}

	private boolean hasSwimearBoots(PlayerEntity player) {
		return !player.getInventory().getArmorStack(0).isEmpty() && isSwimwearBoots((ArmorItem)player.getInventory().getArmorStack(0).getItem());
	}
	private boolean isSwimwearBoots(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.CROCS;
	}

	private boolean hasSwimearLeggings(PlayerEntity player) {
		return !player.getInventory().getArmorStack(1).isEmpty() && isSwimwearLeggings((ArmorItem)player.getInventory().getArmorStack(1).getItem());
	}
	private boolean isSwimwearLeggings(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.TRUNKS || armorItem.getMaterial() == MaterialsRegistry.BIKINI;
	}

	private boolean hasSwimwearBreastplate(PlayerEntity player) {
		return !player.getInventory().getArmorStack(2).isEmpty() && isSwimwearBreastplate((ArmorItem)player.getInventory().getArmorStack(2).getItem());
	}
	private boolean isSwimwearBreastplate(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.RING || armorItem.getMaterial() == MaterialsRegistry.SWIM_WINGS;
	}

	private boolean hasSwimearHelmet(PlayerEntity player) {
		return !player.getInventory().getArmorStack(3).isEmpty() && isSwimwearHelmet((ArmorItem)player.getInventory().getArmorStack(3).getItem());
	}
	private boolean isSwimwearHelmet(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.BEACH_HAT || armorItem.getMaterial() == MaterialsRegistry.SUNGLASSES;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		tooltip.add(Text.translatable(  "tooltip.beachparty.swimwearline1").formatted(Formatting.DARK_PURPLE));
		tooltip.add(Text.translatable(  "tooltip.beachparty.swimwearline2").formatted(Formatting.BLUE));

		PlayerEntity player = MinecraftClient.getInstance().player;

		boolean helmet = hasSwimearHelmet(player);
		boolean breastplate = hasSwimwearBreastplate(player);
		boolean leggings = hasSwimearLeggings(player);
		boolean boots = hasSwimearBoots(player);

		tooltip.add(Text.of(""));
		tooltip.add(Text.translatable("tooltip.beachparty.swimwear_set").formatted(Formatting.DARK_GREEN));
		tooltip.add(helmet ? Text.translatable("tooltip.beachparty.swimwearhelmet").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearhelmet").formatted(Formatting.GRAY));
		tooltip.add(breastplate ? Text.translatable("tooltip.beachparty.swimwearbreastplate").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearbreastplate").formatted(Formatting.GRAY));
		tooltip.add(leggings ? Text.translatable("tooltip.beachparty.swimwearleggings").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearleggings").formatted(Formatting.GRAY));
		tooltip.add(boots ? Text.translatable("tooltip.beachparty.swimwearboots").formatted(Formatting.GREEN) : Text.translatable("tooltip.beachparty.swimwearboots").formatted(Formatting.GRAY));
		tooltip.add(Text.of(""));
		tooltip.add(Text.translatable("tooltip.beachparty.swimwear_seteffect").formatted(Formatting.GRAY));
		tooltip.add(helmet && breastplate && leggings && boots ? Text.translatable("tooltip.beachparty.swimwear_effect").formatted(Formatting.DARK_GREEN) : Text.translatable("tooltip.beachparty.swimwear_effect").formatted(Formatting.GRAY));
	}
}