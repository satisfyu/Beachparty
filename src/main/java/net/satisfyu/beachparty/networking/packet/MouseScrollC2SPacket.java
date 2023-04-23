package net.satisfyu.beachparty.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.beachparty.block.RadioBlock;
import net.satisfyu.beachparty.networking.BeachpartyMessages;

import java.util.List;

public class MouseScrollC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld serverWorld = player.getWorld();
        BlockPos blockPos = buf.readBlockPos();
        BlockState blockState = serverWorld.getBlockState(blockPos);

        if (blockState.getBlock() instanceof RadioBlock radioBlock) {
            if (!blockState.get(RadioBlock.ON) || blockState.get(RadioBlock.POWERED)) {
                return;
            }
            int scrollValue = buf.readInt();
            int channel = radioBlock.tune(serverWorld, blockState, blockPos, scrollValue);

            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeBlockPos(blockPos);
            buffer.writeInt(channel);

            List<ServerPlayerEntity> serverPlayerEntities = server.getPlayerManager().getPlayerList();
            for (ServerPlayerEntity serverPlayer : serverPlayerEntities) {
                ServerPlayNetworking.send(serverPlayer, BeachpartyMessages.TUNE_RADIO_S2C, buffer);
            }
        }
    }
}
