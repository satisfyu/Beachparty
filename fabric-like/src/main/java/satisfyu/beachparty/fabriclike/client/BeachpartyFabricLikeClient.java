package satisfyu.beachparty.fabriclike.client;

import net.fabricmc.api.ClientModInitializer;
import satisfyu.beachparty.client.BeachPartyClient;

public class BeachpartyFabricLikeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BeachPartyClient.preInitClient();
        BeachPartyClient.initClient();
    }


}
