package net.satisfyu.beachparty.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.beachparty.Beachparty;
import net.satisfyu.beachparty.BeachpartyIdentifier;

import java.util.List;

public class SoundEventRegistry {
    public static final SoundEvent RADIO_CLICK = registerSoundEvent("radio_click");
    public static final SoundEvent RADIO_TUNE = registerSoundEvent("radio_tune");
    public static final SoundEvent RADIO_REGGEA = registerSoundEvent("radio_reggea");
    public static final SoundEvent RADIO_HAWAII = registerSoundEvent("radio_hawaii");
    public static final SoundEvent RADIO_TROPICAL = registerSoundEvent("radio_tropical");
    public static final SoundEvent RADIO_BEACHPARTY = registerSoundEvent("radio_beachparty");
    public static final SoundEvent POOL_NOODLE = registerSoundEvent("pool_noodle");
    public static final SoundEvent AMBIENT_BEACH = registerSoundEvent("ambient_beach");
    public static final SoundEvent PELICAN_HURT = registerSoundEvent("pelican_hurt");
    public static final SoundEvent PELICAN_IDLE = registerSoundEvent("pelican_idle");
    public static final SoundEvent PELICAN_DEATH = registerSoundEvent("pelican_death");
    public static final SoundEvent CABINET_OPEN = registerSoundEvent("cabinet_open");
    public static final SoundEvent CABINET_CLOSE = registerSoundEvent("cabinet_close");

    public static final List<SoundEvent> RADIO_SOUNDS = List.of(RADIO_REGGEA, RADIO_HAWAII, RADIO_TROPICAL, RADIO_BEACHPARTY);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new BeachpartyIdentifier(name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void init() {
        Beachparty.LOGGER.debug("Register " + SoundEventRegistry.class);
    }
}
