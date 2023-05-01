package satisfyu.beachparty.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import satisfyu.beachparty.client.BeachPartyClient;
import satisfyu.beachparty.client.ClientUtil;
import satisfyu.beachparty.registry.MaterialsRegistry;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BeachpartyArmorItem extends ArmorItem {
	private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
			(new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
					.put(MaterialsRegistry.BIKINI, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14*20, 1))
					.put(MaterialsRegistry.TRUNKS, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14*20, 1))
					.put(MaterialsRegistry.SWIM_WINGS, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14*20, 0))
					.put(MaterialsRegistry.RING, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14*20, 1)).build();

	public BeachpartyArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
		super(material, slot, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
		if(!world.isClientSide()) {
			if(entity instanceof Player player) {
				if (hasSwimwearSet(player)) {
					addStatusEffectForMaterial(player, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14*20, 2));
					addStatusEffectForMaterial(player, new MobEffectInstance(MobEffects.WATER_BREATHING, 14*20, 0));
				}
				hasSwimwear(player);
			}
		}
		super.inventoryTick(stack, world, entity, slot, selected);
	}

	private boolean hasSwimwearSet(Player player) {
		return hasSwimearBoots(player) && hasSwimearLeggings(player) && hasSwimwearBreastplate(player) && hasSwimearHelmet(player);
	}

	private void hasSwimwear(Player player) {
		for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
			ArmorMaterial mapArmorMaterial = entry.getKey();
			MobEffectInstance mapStatusEffect = entry.getValue();

			if(hasCorrectSwimWear(mapArmorMaterial, player)) {
				addStatusEffectForMaterial(player, mapStatusEffect);
			}
		}
	}

	private boolean hasCorrectSwimWear(ArmorMaterial material, Player player) {
		if (material.equals(MaterialsRegistry.BIKINI) || material.equals(MaterialsRegistry.TRUNKS)) {
			int slot = 1;
			if(!player.getInventory().getArmor(slot).isEmpty()) {
				ArmorItem armor = (ArmorItem) player.getInventory().getArmor(slot).getItem();
				return armor.getMaterial() == material;
			}
			return false;
		}
		if (material.equals(MaterialsRegistry.SWIM_WINGS) || material.equals(MaterialsRegistry.RING)) {
			int slot = 2;
			if(!player.getInventory().getArmor(slot).isEmpty()) {
				ArmorItem armor = (ArmorItem) player.getInventory().getArmor(slot).getItem();
				return armor.getMaterial() == material;
			}
			return false;
		}
		return false;
	}

	private void addStatusEffectForMaterial(Player player, MobEffectInstance mapStatusEffect) {
		boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

		if (!hasPlayerEffect || Objects.requireNonNull(player.getEffect(mapStatusEffect.getEffect())).getDuration() < 11 * 20) {
			player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
					mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
		}
	}

	public static boolean hasSwimearBoots(Player player) {
		return !player.getInventory().getArmor(0).isEmpty() && isSwimwearBoots((ArmorItem)player.getInventory().getArmor(0).getItem());
	}
	private static boolean isSwimwearBoots(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.CROCS;
	}

	public static boolean hasSwimearLeggings(Player player) {
		return !player.getInventory().getArmor(1).isEmpty() && isSwimwearLeggings((ArmorItem)player.getInventory().getArmor(1).getItem());
	}
	private static boolean isSwimwearLeggings(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.TRUNKS || armorItem.getMaterial() == MaterialsRegistry.BIKINI;
	}

	public static boolean hasSwimwearBreastplate(Player player) {
		return !player.getInventory().getArmor(2).isEmpty() && isSwimwearBreastplate((ArmorItem)player.getInventory().getArmor(2).getItem());
	}
	private static boolean isSwimwearBreastplate(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.RING || armorItem.getMaterial() == MaterialsRegistry.SWIM_WINGS;
	}

	public static boolean hasSwimearHelmet(Player player) {
		return !player.getInventory().getArmor(3).isEmpty() && isSwimwearHelmet((ArmorItem)player.getInventory().getArmor(3).getItem());
	}
	private static boolean isSwimwearHelmet(ArmorItem armorItem) {
		return armorItem.getMaterial() == MaterialsRegistry.BEACH_HAT || armorItem.getMaterial() == MaterialsRegistry.SUNGLASSES;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
		if (world != null && world.isClientSide()){
			ClientUtil.appendTooltip(tooltip);
		}
	}
}
