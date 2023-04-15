package net.satisfyu.beachparty.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.beachparty.registry.EntityRegistry;
import net.satisfyu.beachparty.sound.LoopSound;

public class RadioBlockEntity extends BlockEntity implements BlockEntityTicker<RadioBlockEntity>  {
    public final LoopSound loopSound;
    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(EntityRegistry.RADIO_BLOCK_ENTITY, pos, state);
        loopSound = new LoopSound(SoundEvents.BLOCK_BARREL_CLOSE, 100, 10);

    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, RadioBlockEntity blockEntity) {
        loopSound.tick();
    }
}
