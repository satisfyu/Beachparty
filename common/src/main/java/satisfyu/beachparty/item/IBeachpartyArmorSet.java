package satisfyu.beachparty.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import satisfyu.beachparty.registry.MaterialsRegistry;

import java.util.Map;
import java.util.Objects;

public interface IBeachpartyArmorSet {

    Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(MaterialsRegistry.BIKINI, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14 * 20, 1))
                    .put(MaterialsRegistry.TRUNKS, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14 * 20, 1))
                    .put(MaterialsRegistry.SWIM_WINGS, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14 * 20, 0))
                    .put(MaterialsRegistry.RING, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14 * 20, 1)).build();

    default boolean hasSwimwearSet(Player player) {
        return hasSwimearBoots(player) && hasSwimearLeggings(player) && hasSwimwearBreastplate(player) && hasSwimearHelmet(player);
    }

    default void checkForSet(Player player) {
        if (hasSwimwearSet(player)) {
            addStatusEffectForMaterial(player, new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 14 * 20, 2));
            addStatusEffectForMaterial(player, new MobEffectInstance(MobEffects.WATER_BREATHING, 14 * 20, 0));
        }
        hasSwimwear(player);
    }

    default void hasSwimwear(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if (hasCorrectSwimWear(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapStatusEffect);
            }
        }
    }

    private boolean hasCorrectSwimWear(ArmorMaterial material, Player player) {
        if (material.equals(MaterialsRegistry.BIKINI) || material.equals(MaterialsRegistry.TRUNKS)) {
            int slot = 1;
            if (!player.getInventory().getArmor(slot).isEmpty()) {
                ArmorItem armor = (ArmorItem) player.getInventory().getArmor(slot).getItem();
                return armor.getMaterial() == material;
            }
            return false;
        }
        if (material.equals(MaterialsRegistry.SWIM_WINGS) || material.equals(MaterialsRegistry.RING)) {
            int slot = 2;
            if (!player.getInventory().getArmor(slot).isEmpty()) {
                if (player.getInventory().getArmor(slot).getItem() instanceof ArmorItem armor) {
                    return armor.getMaterial() == material;
                }
            }
            return false;
        }
        return false;
    }

    default void addStatusEffectForMaterial(Player player, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if (!hasPlayerEffect || Objects.requireNonNull(player.getEffect(mapStatusEffect.getEffect())).getDuration() < 11 * 20) {
            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier(), true, false, true));
        }
    }

    static boolean hasSwimearBoots(Player player) {
        if (player.getInventory().getArmor(0).isEmpty()) return false;
        Item item = player.getInventory().getArmor(0).getItem();
        if (item instanceof ArmorItem armorItem) {
            return isSwimwearBoots(armorItem);
        }
        return false;
    }

    private static boolean isSwimwearBoots(ArmorItem armorItem) {
        return armorItem.getMaterial() == MaterialsRegistry.CROCS;
    }

    static boolean hasSwimearLeggings(Player player) {
        if (player.getInventory().getArmor(1).isEmpty()) return false;
        Item item = player.getInventory().getArmor(1).getItem();
        if (item instanceof ArmorItem armorItem) {
            return isSwimwearLeggings(armorItem);
        }
        return false;
    }

    private static boolean isSwimwearLeggings(ArmorItem armorItem) {
        return armorItem.getMaterial() == MaterialsRegistry.TRUNKS || armorItem.getMaterial() == MaterialsRegistry.BIKINI;
    }

    static boolean hasSwimwearBreastplate(Player player) {
        if (player.getInventory().getArmor(2).isEmpty()) return false;
        Item item = player.getInventory().getArmor(2).getItem();
        if (item instanceof ArmorItem armorItem) {
            return isSwimwearBreastplate(armorItem);
        }
        return false;
    }

    private static boolean isSwimwearBreastplate(ArmorItem armorItem) {
        return armorItem.getMaterial() == MaterialsRegistry.RING || armorItem.getMaterial() == MaterialsRegistry.SWIM_WINGS;
    }

    static boolean hasSwimearHelmet(Player player) {
        if (player.getInventory().getArmor(3).isEmpty()) return false;
        Item item = player.getInventory().getArmor(3).getItem();
        if (item instanceof ArmorItem armorItem) {
            return isSwimwearHelmet(armorItem);
        }
        return false;
    }


    private static boolean isSwimwearHelmet(ArmorItem armorItem) {
        return armorItem.getMaterial() == MaterialsRegistry.BEACH_HAT || armorItem.getMaterial() == MaterialsRegistry.SUNGLASSES;
    }
}
