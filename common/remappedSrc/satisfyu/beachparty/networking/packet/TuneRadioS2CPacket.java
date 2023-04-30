package satisfyu.beachparty.networking.packet;

import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import satisfyu.beachparty.util.RadioHelper;

public class TuneRadioS2CPacket implements NetworkManager.NetworkReceiver {

    @Override
    public void receive(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        BlockPos blockPos = buf.readBlockPos();
        int channel = buf.readInt();



        RadioHelper.tune(blockPos, channel);
    }
}
