package net.satisfyu.beachparty.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.satisfyu.beachparty.registry.EntityRegistry;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class CoconutEntity extends ThrownItemEntity {
    public CoconutEntity(EntityType<? extends CoconutEntity> entityType, World world) {
        super(entityType, world);
    }

    public CoconutEntity(World world, LivingEntity owner) {
        super(EntityRegistry.COCONUT, owner, world);
    }

    protected Item getDefaultItem() {
        return ObjectRegistry.COCONUT;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL: new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int damage = 2;
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)damage);
    }
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.playSound(SoundEvents.BLOCK_WOOD_FALL, 1.0F, 1.0F);
            this.dropItem(ObjectRegistry.COCONUT_OPEN);
            this.discard();
        }
    }
 }

