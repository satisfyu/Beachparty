package satisfyu.beachparty.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class DeepBreathEffect extends MobEffect {
    public DeepBreathEffect(MobEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, true, false));
        entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 20, 0, true, false));
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
