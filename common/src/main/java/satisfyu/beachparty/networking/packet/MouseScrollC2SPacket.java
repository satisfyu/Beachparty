package satisfyu.beachparty.networking.packet;

import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import satisfyu.beachparty.block.RadioBlock;
import satisfyu.beachparty.networking.BeachpartyMessages;
import satisfyu.beachparty.util.BeachpartyUtil;

import java.util.List;

public class MouseScrollC2SPacket implements NetworkManager.NetworkReceiver {

    @Override
    public void receive(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
        Level serverWorld = context.getPlayer().getLevel();
        BlockPos blockPos = buf.readBlockPos();
        BlockState blockState = serverWorld.getBlockState(blockPos);

        if (blockState.getBlock() instanceof RadioBlock radioBlock) {
            if (!blockState.getValue(RadioBlock.ON) || blockState.getValue(RadioBlock.SEARCHING)) {
                return;
            }
            int scrollValue = buf.readInt();
            int channel = radioBlock.tune(serverWorld, blockState, blockPos, scrollValue);

            FriendlyByteBuf buffer = BeachpartyUtil.createPacketBuf();;
            buffer.writeBlockPos(blockPos);
            buffer.writeInt(channel);

            List<ServerPlayer> serverPlayerEntities = context.getPlayer().getServer().getPlayerList().getPlayers();
            for (ServerPlayer serverPlayer : serverPlayerEntities) {
                NetworkManager.sendToPlayer(serverPlayer, BeachpartyMessages.TUNE_RADIO_S2C, buffer);
            }
        }
    }
}
