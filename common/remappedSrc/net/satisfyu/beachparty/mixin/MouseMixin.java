package net.satisfyu.beachparty.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.Options;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.satisfyu.beachparty.block.RadioBlock;
import net.satisfyu.beachparty.networking.BeachpartyMessages;
import net.satisfyu.beachparty.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseMixin {

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void MouseScrollOnRadio(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (window == Minecraft.getInstance().getWindow().getWindow()) {
            Minecraft client = Minecraft.getInstance();
            if(client.hitResult instanceof BlockHitResult blockHitResult){

                if (blockHitResult.getType() != HitResult.Type.BLOCK) {
                    return;
                }

                BlockPos blockPos = blockHitResult.getBlockPos();
                BlockState blockState = client.level.getBlockState(blockPos);
                if (blockState.getBlock() != ObjectRegistry.RADIO || !blockState.getValue(RadioBlock.ON)) {
                    return;
                }

                int scrollValue = (int)calculateScrollValue(vertical, client.options);
                handleScrollEvent(blockPos, scrollValue);
                ci.cancel();
            }
        }
    }

    private double calculateScrollValue(double vertical, Options options) {
        return options.discreteMouseScroll().get() ? Math.signum(vertical) : vertical * options.mouseWheelSensitivity().get();
    }

    private void handleScrollEvent(BlockPos blockPos, int scrollValue) {
        if (scrollValue == 0) {
            return;
        }
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeBlockPos(blockPos);
        buffer.writeInt(scrollValue);
        ClientPlayNetworking.send(BeachpartyMessages.MOUSE_SCROLL_C2S, buffer);
    }
}
