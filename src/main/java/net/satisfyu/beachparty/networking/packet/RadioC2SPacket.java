package net.satisfyu.beachparty.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.beachparty.block.RadioBlock;

public class RadioC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld serverWorld = player.getWorld();
        BlockPos blockPos = buf.readBlockPos();
        BlockState blockState = serverWorld.getBlockState(blockPos);

        if (blockState.getBlock() instanceof RadioBlock radioBlock) {
            int scrollValue = buf.readInt();
            radioBlock.tune(blockState, serverWorld, blockPos, scrollValue);
        }

    }
}
