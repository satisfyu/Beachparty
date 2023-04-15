package net.satisfyu.beachparty.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.satisfyu.beachparty.block.entity.RadioBlockEntity;
import net.satisfyu.beachparty.registry.EntityRegistry;
import org.jetbrains.annotations.Nullable;

public class RadioBlock extends BlockWithEntity {
    public static final BooleanProperty ON;

    public RadioBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ON, false));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RadioBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, EntityRegistry.RADIO_BLOCK_ENTITY, (world1, pos, state1, be) -> be.tick(world1, pos, state1, be));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ON);
    }

    static {
        ON = BooleanProperty.of("on");
    }


}