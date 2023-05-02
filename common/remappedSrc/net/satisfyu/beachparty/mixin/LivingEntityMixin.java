package net.satisfyu.beachparty.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.satisfyu.beachparty.block.HammockBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow public abstract Optional<BlockPos> getSleepingPosition();

    @Inject(method = "isSleepingInBed", at = @At("HEAD"), cancellable = true)
    private void isSleepingInHammock(CallbackInfoReturnable<Boolean> cir) {
        boolean isHammock = this.getSleepingPosition().map((pos) -> level.getBlockState(pos).getBlock() instanceof HammockBlock).orElse(false);
        if (isHammock) cir.setReturnValue(true);
    }
}
