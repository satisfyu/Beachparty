package net.satisfyu.beachparty.entity.pelican;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;

public class PelicanEntity extends ChickenEntity {
    public PelicanEntity(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
    }


}
