package net.satisfyu.beachparty.sound;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;

public class LoopSound {
    private final MinecraftClient minecraft = MinecraftClient.getInstance();
    private final SoundEvent sound;
    private final int duration;
    private final int repeatCount;
    private int repeat = 0;
    private int ticks = 0;

    public LoopSound(SoundEvent sound, int duration, int repeatCount) {
        this.sound = sound;
        this.duration = duration;
        this.repeatCount = repeatCount;
    }

    public void tick() {
        ticks++;
        if (ticks % duration == 0) {
            repeat++;
            minecraft.getSoundManager().play(new LoopingSoundInstance(sound, SoundCategory.AMBIENT, repeat < repeatCount));
        }
    }

    private class LoopingSoundInstance extends AbstractSoundInstance {
        private final SoundEvent sound;

        public LoopingSoundInstance(SoundEvent sound, SoundCategory category, boolean loop) {
            super(sound, category, Random.create());
            this.sound = sound;
            this.repeat = loop;
        }

        @Override
        public boolean shouldAlwaysPlay() {
            return true;
        }

        @Override
        public AttenuationType getAttenuationType() {
            return AttenuationType.NONE;
        }

        public void tick() {
            if (!minecraft.getSoundManager().isPlaying(this)) {
                minecraft.getSoundManager().play(new LoopingSoundInstance(sound, category, repeat));
            }
        }
    }
}