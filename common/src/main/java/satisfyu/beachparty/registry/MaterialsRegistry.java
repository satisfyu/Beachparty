package satisfyu.beachparty.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class MaterialsRegistry {
    public static final ArmorMaterial TRUNKS = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.LEATHER.getEnchantmentValue();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.STRING);
        }

        @Override
        public String getName() {
            return "trunks";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.LEATHER.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.LEATHER.getKnockbackResistance();
        }
    };

    public static final ArmorMaterial BIKINI = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.LEATHER.getEnchantmentValue();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.STRING);
        }

        @Override
        public String getName() {
            return "bikini";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.LEATHER.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.LEATHER.getKnockbackResistance();
        }
    };

    public static final ArmorMaterial RING = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.LEATHER.getEnchantmentValue();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.DRIED_KELP);
        }

        @Override
        public String getName() {
            return "ring";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.LEATHER.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.LEATHER.getKnockbackResistance();
        }
    };

    public static final ArmorMaterial BEACH_HAT = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.LEATHER.getEnchantmentValue();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.WHEAT);
        }

        @Override
        public String getName() {
            return "beach_hat";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.LEATHER.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.LEATHER.getKnockbackResistance();
        }
    };

    public static final ArmorMaterial SUNGLASSES = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.LEATHER.getEnchantmentValue();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Blocks.BLACK_STAINED_GLASS_PANE.asItem());
        }

        @Override
        public String getName() {
            return "sunglasses";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.LEATHER.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.LEATHER.getKnockbackResistance();
        }
    };

    public static final ArmorMaterial SWIM_WINGS = new ArmorMaterial() {

        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.LEATHER.getEnchantmentValue();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.DRIED_KELP);
        }

        @Override
        public String getName() {
            return "swim_wings";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.LEATHER.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.LEATHER.getKnockbackResistance();
        }
    };

    public static final ArmorMaterial CROCS = new ArmorMaterial() {
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDurabilityForType(type);
        }

        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return ArmorMaterials.LEATHER.getDefenseForType(type);
        }

        @Override
        public int getEnchantmentValue() {
            return ArmorMaterials.LEATHER.getEnchantmentValue();
        }

        @Override
        public SoundEvent getEquipSound() {
            return ArmorMaterials.LEATHER.getEquipSound();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.DRIED_KELP);
        }

        @Override
        public String getName() {
            return "crocs";
        }

        @Override
        public float getToughness() {
            return ArmorMaterials.LEATHER.getToughness();
        }

        @Override
        public float getKnockbackResistance() {
            return ArmorMaterials.LEATHER.getKnockbackResistance();
        }
    };
}

