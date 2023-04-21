package net.satisfyu.beachparty.util;

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

public class RadioHelper {
    public static final int CHANNELS = BeachpartySounds.RADIO_SOUNDS.size();
    private static Map<BlockPos, List<PositionedSoundInstance>> soundInstances = new HashMap<>();

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
            stopSound(pos, channel);
        }
    }

    private static void playSound(BlockPos pos, int channel, int delay) {
        PositionedSoundInstance soundInstance = getSound(pos, channel);
        if (soundInstance != null) {
            MinecraftClient.getInstance().getSoundManager().play(soundInstance, delay);
        }

    }

    private static void stopSound(BlockPos pos, int channel) {
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
