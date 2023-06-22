package satisfyu.beachparty.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.forge.entity.EntitySpawnForge;
import satisfyu.beachparty.forge.registry.VillagersForge;

@Mod(Beachparty.MOD_ID)
public class BeachpartyForge {
    public BeachpartyForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Beachparty.MOD_ID, modEventBus);
        Beachparty.init();
        VillagersForge.register(modEventBus);
        EntitySpawnForge.registerEntitySpawn();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(VillagersForge::registerPOIs);
        Beachparty.commonSetup();
    }


}
