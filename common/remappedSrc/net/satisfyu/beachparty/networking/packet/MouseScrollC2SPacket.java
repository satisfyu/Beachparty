package net.satisfyu.beachparty.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.beachparty.block.RadioBlock;
import net.satisfyu.beachparty.networking.BeachpartyMessages;

import java.util.List;

public class MouseScrollC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        ServerLevel serverWorld = player.getLevel();
        BlockPos blockPos = buf.readBlockPos();
        BlockState blockState = serverWorld.getBlockState(blockPos);

        if (blockState.getBlock() instanceof RadioBlock radioBlock) {
            if (!blockState.getValue(RadioBlock.ON) || blockState.getValue(RadioBlock.SEARCHING)) {
                return;
            }
            int scrollValue = buf.readInt();
            int channel = radioBlock.tune(serverWorld, blockState, blockPos, scrollValue);

            FriendlyByteBuf buffer = PacketByteBufs.create();
            buffer.writeBlockPos(blockPos);
            buffer.writeInt(channel);

            List<ServerPlayer> serverPlayerEntities = server.getPlayerList().getPlayers();
            for (ServerPlayer serverPlayer : serverPlayerEntities) {
                ServerPlayNetworking.send(serverPlayer, BeachpartyMessages.TUNE_RADIO_S2C, buffer);
            }
        }
    }
}
