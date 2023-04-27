package net.satisfyu.beachparty.mixin;
import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.LevelProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelProperties.class)
public class HereBeNoDragonsMixin
{
    @Shadow
    @Final
    private Lifecycle lifecycle;

    @Unique
    private static final Logger log = LogManager.getLogger("net.satisfyu.beachparty.mixin.HereBeNoDragonsMixin");

    @Inject(method = "getLifecycle()Lcom/mojang/serialization/Lifecycle;", at = @At("HEAD"), cancellable = true)
    private void getLifecycle(CallbackInfoReturnable<Lifecycle> cir)
    {
        if (lifecycle == Lifecycle.experimental())
        {
            log.warn("Suppressing EXPERIMENTAL level lifecycle");
            cir.setReturnValue(Lifecycle.stable());
            cir.cancel();
        }
    }
}
