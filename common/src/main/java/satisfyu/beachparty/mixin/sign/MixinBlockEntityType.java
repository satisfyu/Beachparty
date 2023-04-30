package satisfyu.beachparty.mixin.sign;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import satisfyu.beachparty.util.sign.TerraformSign;

@Mixin(BlockEntityType.class)
public class MixinBlockEntityType {
	@Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
	private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
		//noinspection EqualsBetweenInconvertibleTypes
		if (BlockEntityType.SIGN.equals(this) && state.getBlock() instanceof TerraformSign) {
			info.setReturnValue(true);
		}
	}
}
