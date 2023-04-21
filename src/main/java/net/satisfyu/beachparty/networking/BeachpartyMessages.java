package net.satisfyu.beachparty.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.networking.packet.RadioC2SPacket;

public class BeachpartyMessages {
    public static final Identifier RADIO_ID = new BeachpartyIdentifier("radio");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(RADIO_ID, RadioC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }
}
