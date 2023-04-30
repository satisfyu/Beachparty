package satisfyu.beachparty.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;

import java.util.List;

public class SoundEventRegistry {
    
    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Beachparty.MOD_ID, Registry.SOUND_EVENT_REGISTRY);
    
    public static final RegistrySupplier<SoundEvent> RADIO_CLICK = registerSoundEvent("radio_click");
    public static final RegistrySupplier<SoundEvent> RADIO_TUNE = registerSoundEvent("radio_tune");
    public static final RegistrySupplier<SoundEvent> RADIO_REGGEA = registerSoundEvent("radio_reggea");
    public static final RegistrySupplier<SoundEvent> RADIO_HAWAII = registerSoundEvent("radio_hawaii");
    public static final RegistrySupplier<SoundEvent> RADIO_TROPICAL = registerSoundEvent("radio_tropical");
    public static final RegistrySupplier<SoundEvent> RADIO_BEACHPARTY = registerSoundEvent("radio_beachparty");
    public static final RegistrySupplier<SoundEvent> AMBIENT_BEACH = registerSoundEvent("ambient_beach");
    public static final RegistrySupplier<SoundEvent> PELICAN_HURT = registerSoundEvent("pelican_hurt");
    public static final RegistrySupplier<SoundEvent> PELICAN_IDLE = registerSoundEvent("pelican_idle");
    public static final RegistrySupplier<SoundEvent> PELICAN_DEATH = registerSoundEvent("pelican_death");
    public static final RegistrySupplier<SoundEvent> CABINET_OPEN = registerSoundEvent("cabinet_open");
    public static final RegistrySupplier<SoundEvent> CABINET_CLOSE = registerSoundEvent("cabinet_close");

    public static final List<RegistrySupplier<SoundEvent>> RADIO_SOUNDS = List.of(RADIO_REGGEA, RADIO_HAWAII, RADIO_TROPICAL, RADIO_BEACHPARTY);

    private static RegistrySupplier<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, ()-> new SoundEvent(new BeachpartyIdentifier(name)));
    }

    public static void init() {
        Beachparty.LOGGER.debug("Register " + SoundEventRegistry.class);
        SOUND_EVENTS.register();
    }
}
