package satisfyu.beachparty.entity;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import satisfyu.beachparty.registry.EntityRegistry;
import satisfyu.beachparty.registry.ObjectRegistry;

public class CoconutEntity extends ThrowableItemProjectile {
    public CoconutEntity(EntityType<? extends CoconutEntity> entityType, Level world) {
        super(entityType, world);
    }

    public CoconutEntity(Level world, LivingEntity owner) {
        super(EntityRegistry.COCONUT, owner, world);
    }

    protected Item getDefaultItem() {
        return ObjectRegistry.COCONUT;
    }

    private ParticleOptions getParticleParameters() {
        ItemStack itemStack = this.getItemRaw();
        return (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL: new ItemParticleOption(ParticleTypes.ITEM, itemStack));
    }

    public void handleEntityEvent(byte status) {
        if (status == 3) {
            ParticleOptions particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int damage = 2;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)damage);
    }
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.playSound(SoundEvents.WOOD_FALL, 1.0F, 1.0F);
            this.spawnAtLocation(ObjectRegistry.COCONUT_OPEN);
            this.discard();
        }
    }
 }

