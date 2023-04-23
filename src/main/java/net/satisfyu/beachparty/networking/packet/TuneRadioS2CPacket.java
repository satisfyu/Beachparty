package net.satisfyu.beachparty.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.beachparty.util.RadioHelper;

public class TuneRadioS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        BlockPos blockPos = buf.readBlockPos();
        int channel = buf.readInt();



        RadioHelper.tune(blockPos, channel);
    }
}
