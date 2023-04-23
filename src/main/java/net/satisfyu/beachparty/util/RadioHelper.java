package net.satisfyu.beachparty.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.satisfyu.beachparty.sound.BeachpartySounds;
import org.apache.commons.compress.utils.Lists;

import java.util.*;

@Environment(EnvType.CLIENT)
public class RadioHelper {
    public static final int CHANNELS = BeachpartySounds.RADIO_SOUNDS.size();
    public static final int DELAY = 2 * 20;
    private static final Map<BlockPos, List<PositionedSoundInstance>> soundInstances = new HashMap<>();

    public static void setPlaying(BlockPos pos, int channel, boolean play) {
        setPlaying(pos, channel, play, 0);
    }

    public static void setPlaying(BlockPos pos, int channel, boolean play, int delay) {
        if (play) {
            if (!soundInstances.containsKey(pos)) {
                addSounds(pos);
            }
            playSound(pos, channel, delay);
        } else {
            stopSounds(pos);
        }
    }

    public static void tune(BlockPos pos, int channel) {
        MinecraftClient.getInstance().getSoundManager().play(new PositionedSoundInstance(BeachpartySounds.RADIO_TUNE, SoundCategory.RECORDS, 1.0f, 1.0f, Random.create(), pos));
        stopSounds(pos);
        if (!soundInstances.containsKey(pos)) {
            addSounds(pos);
        }
        playSound(pos, channel, DELAY);
    }

    private static void playSound(BlockPos pos, int channel, int delay) {
        PositionedSoundInstance soundInstance = getSound(pos, channel);
        if (soundInstance != null) {
            MinecraftClient.getInstance().getSoundManager().play(soundInstance, delay);
        }

    }

    private static void stopSounds(BlockPos pos) {
        if (soundInstances.containsKey(pos)) {
            for (PositionedSoundInstance soundInstance : soundInstances.get(pos)) {
                MinecraftClient.getInstance().getSoundManager().stop(soundInstance);
            }
        }
    }

    private static void addSounds(BlockPos blockPos) {
        List<PositionedSoundInstance> soundInstance = Lists.newArrayList();
        for (SoundEvent sound : BeachpartySounds.RADIO_SOUNDS) {
            soundInstance.add(new PositionedSoundInstance(
                    sound.getId(),
                    SoundCategory.RECORDS,
                    1.0f,
                    1.0f,
                    Random.create(),
                    true,
                    0,
                    SoundInstance.AttenuationType.LINEAR,
                    blockPos.getX(),
                    blockPos.getY(),
                    blockPos.getZ(),
                    false
            ));
            soundInstances.put(blockPos, soundInstance);
        }
    }

    public static PositionedSoundInstance getSound(BlockPos pos, int channel) {
        return soundInstances.get(pos).get(channel);
    }
}
