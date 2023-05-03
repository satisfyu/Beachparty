package net.satisfyu.beachparty.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.networking.packet.MouseScrollC2SPacket;
import net.satisfyu.beachparty.networking.packet.TuneRadioS2CPacket;
import net.satisfyu.beachparty.networking.packet.TurnRadioS2CPacket;

public class BeachpartyMessages {
    public static final ResourceLocation MOUSE_SCROLL_C2S = new BeachpartyIdentifier("mouse_scroll");
    public static final ResourceLocation TURN_RADIO_S2C = new BeachpartyIdentifier("turn_radio");
    public static final ResourceLocation TUNE_RADIO_S2C = new BeachpartyIdentifier("tune_radio");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(MOUSE_SCROLL_C2S, MouseScrollC2SPacket::receive);

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(TURN_RADIO_S2C, TurnRadioS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(TUNE_RADIO_S2C, TuneRadioS2CPacket::receive);
    }
}
