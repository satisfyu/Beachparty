package net.satisfyu.beachparty.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.option.GameOptions;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.beachparty.block.RadioBlock;
import net.satisfyu.beachparty.networking.BeachpartyMessages;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void MouseScrollOnRadio(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (window == MinecraftClient.getInstance().getWindow().getHandle()) {
            MinecraftClient client = MinecraftClient.getInstance();

            BlockHitResult blockHitResult = (BlockHitResult) client.crosshairTarget;
            if (blockHitResult.getType() != HitResult.Type.BLOCK) {
                return;
            }

            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = client.world.getBlockState(blockPos);
            if (blockState.getBlock() != ObjectRegistry.RADIO || !blockState.get(RadioBlock.ON)) {
                return;
            }

            int scrollValue = (int)calculateScrollValue(vertical, client.options);
            handleScrollEvent(blockPos, scrollValue);
            ci.cancel();
        }
    }

    private double calculateScrollValue(double vertical, GameOptions options) {
        return options.getDiscreteMouseScroll().getValue() ? Math.signum(vertical) : vertical * options.getMouseWheelSensitivity().getValue();
    }

    private void handleScrollEvent(BlockPos blockPos, int scrollValue) {
        if (scrollValue == 0) {
            return;
        }
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeBlockPos(blockPos);
        buffer.writeInt(scrollValue);
        ClientPlayNetworking.send(BeachpartyMessages.MOUSE_SCROLL_C2S, buffer);
    }
}
