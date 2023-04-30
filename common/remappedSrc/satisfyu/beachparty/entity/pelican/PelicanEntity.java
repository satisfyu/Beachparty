package satisfyu.beachparty.entity.pelican;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import satisfyu.beachparty.registry.EntityRegistry;
import satisfyu.beachparty.registry.SoundEventRegistry;

public class PelicanEntity extends Chicken {
    private static final Ingredient BREEDING_INGREDIENT;

    public PelicanEntity(EntityType<? extends Chicken> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEventRegistry.PELICAN_IDLE;
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEventRegistry.PELICAN_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        super.getHurtSound(source);
        return SoundEventRegistry.PELICAN_HURT;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0).add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    public PelicanEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return (PelicanEntity) EntityRegistry.PELICAN.create(serverWorld);
    }

    public boolean isFood(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    static {
        BREEDING_INGREDIENT = Ingredient.of(new ItemLike[]{Items.COD, Items.SALMON, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON});
    }
}
