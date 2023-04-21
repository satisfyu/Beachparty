package net.satisfyu.beachparty.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.satisfyu.beachparty.registry.EntityRegistry;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class RadioBlockEntity extends BlockEntity implements BlockEntityTicker<RadioBlockEntity>  {

    //public SoundEvent tuneSound = new SoundEvent(new BeachpartyIdentifier("tuning"));
    public SoundEvent tuneSound = SoundEvents.BLOCK_CHEST_CLOSE;
    private final List<SoundEvent> sounds = List.of(SoundEvents.BLOCK_BARREL_CLOSE, SoundEvents.BLOCK_ANVIL_PLACE);
    private final List<PositionedSoundInstance> soundInstances;

    private int channel;
    private final int maxChannel;
    private boolean isPlaying = false;
    private int ticksElapsed = 0;

    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(EntityRegistry.RADIO_BLOCK_ENTITY, pos, state);
        this.soundInstances = generateInstances();
        this.maxChannel = soundInstances.size();
        this.channel = Random.create().nextBetween(0, maxChannel - 1);
    }

    private PositionedSoundInstance getToPlaySound(int channel) {
        return this.soundInstances.get(channel);
    }

    public void setPlaying(boolean isPlaying) {
        setPlaying(isPlaying, 0);
    }

    public void setPlaying(boolean isPlaying, int delay) {
        this.isPlaying = isPlaying;
            if (isPlaying) {
                MinecraftClient.getInstance().getSoundManager().play(getToPlaySound(this.channel), delay);
            } else {
                MinecraftClient.getInstance().getSoundManager().stop(getToPlaySound(this.channel));
            }
    }

    public void tuning(int scrollValue) {
        if (world != null) {
            world.playSound(this.getPos().getX(), this.getPos().getY(),this.getPos().getZ(), tuneSound, SoundCategory.RECORDS, 1.0f, 1.0f, true);
        }
        if (scrollValue % 3 == 0) {
            return;
        }
        setPlaying(false);
        this.channel = (this.channel + scrollValue) % maxChannel;
        setPlaying(true, 1 * 20);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, RadioBlockEntity blockEntity) {
    }

    private List<PositionedSoundInstance> generateInstances() {
        List<PositionedSoundInstance> soundInstance = Lists.newArrayList();
        for (SoundEvent sound : sounds) {
            soundInstance.add(new PositionedSoundInstance(
                    sound.getId(),
                    SoundCategory.RECORDS,
                    1.0f,
                    1.0f,
                    Random.create(),
                    true,
                    0,
                    SoundInstance.AttenuationType.LINEAR,
                    this.pos.getX(),
                    this.pos.getY(),
                    this.pos.getZ(),
                    false
            ));
        }
        return soundInstance;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        isPlaying = tag.getBoolean("isPlaying");
        ticksElapsed = tag.getInt("ticksElapsed");
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putBoolean("isPlaying", isPlaying);
        tag.putInt("ticksElapsed", ticksElapsed);
    }
}
