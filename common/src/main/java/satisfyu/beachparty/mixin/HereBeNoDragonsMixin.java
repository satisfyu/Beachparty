package satisfyu.beachparty.mixin;
import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public class HereBeNoDragonsMixin
{

    @Shadow @Final private Lifecycle worldGenSettingsLifecycle;
    @Unique
    private static final Logger log = LogManager.getLogger("net.satisfyu.beachparty.mixin.HereBeNoDragonsMixin");

    @Inject(method = "worldGenSettingsLifecycle", at = @At("HEAD"), cancellable = true)
    private void getLifecycle(CallbackInfoReturnable<Lifecycle> cir)
    {
        if (worldGenSettingsLifecycle == Lifecycle.experimental())
        {
            log.warn("Suppressing EXPERIMENTAL level lifecycle");
            cir.setReturnValue(Lifecycle.stable());
            cir.cancel();
        }
    }
}
