package net.satisfyu.beachparty.block;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.beachparty.block.entity.RadioBlockEntity;
import net.satisfyu.beachparty.networking.BeachpartyMessages;
import net.satisfyu.beachparty.entity.RadioBlockEntity;
import net.satisfyu.beachparty.registry.EntityRegistry;
import net.satisfyu.beachparty.sound.BeachpartySounds;
import net.satisfyu.beachparty.util.BeachpartyUtil;
import net.satisfyu.beachparty.util.RadioHelper;
import net.satisfyu.beachparty.util.radio.RadioHelper;
import org.jetbrains.annotations.Nullable;

public class RadioBlock extends BlockWithEntity {
    public static final BooleanProperty ON;
    public static final IntProperty CHANNEL;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty POWERED;


    public RadioBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ON, false).with(CHANNEL, 0).with(POWERED, false));
        this.setDefaultState(this.stateManager.getDefaultState().with(ON, false).with(CHANNEL, 0).with(FACING, Direction.NORTH));
    }



    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.3125, 0.875, 0.5, 0.6875));



        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Type.HORIZONTAL.stream().toList()) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE.get(state.get(FACING));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(POWERED)) {
            return ActionResult.CONSUME;
        }
        if (hand == Hand.MAIN_HAND) {
            boolean newState = !state.get(ON);
            if (newState) {
                turnON(state, world, pos, player);
            } else {
                turnOFF(state, world, pos, player);
            }
            return ActionResult.SUCCESS;
            if (!world.isClient) {
                RadioHelper.setPlaying(pos, state.get(CHANNEL), newState);

                Direction playerFacing = player.getHorizontalFacing();
                world.setBlockState(pos, state.with(ON, newState).with(FACING, playerFacing));

                if (newState) {
                    for (int i = 0; i < 5; i++) {
                        double x = pos.getX() + 0.5 + (world.random.nextDouble() - 0.5) * 0.5;
                        double y = pos.getY() + 0.5 + world.random.nextDouble() * 0.5;
                        double z = pos.getZ() + 0.5 + (world.random.nextDouble() - 0.5) * 0.5;
                        world.addParticle(ParticleTypes.NOTE, x, y, z, 0, 0, 0);
                        world.addParticle(ParticleTypes.NOTE, x, y, z, 0.1, 0, 0);
                    }
                }

                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    private void turnON(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        world.playSound(player, pos, BeachpartySounds.RADIO_CLICK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.playSound(player, pos, BeachpartySounds.RADIO_TUNE, SoundCategory.RECORDS, 1.0f, 1.0f);
        if (!world.isClient) {
            pressButton(state, world, pos, true);
            sendPacket(state, world, pos, true);

    public void tune(BlockState state, World world, BlockPos pos, int scrollValue) {
        MinecraftClient.getInstance().getSoundManager().play(new PositionedSoundInstance(BeachpartySounds.RADIO_TUNE, SoundCategory.RECORDS,  1.0f, 1.0f, Random.create(), pos));
        if (scrollValue % 3 == 0) {
            return;
        }
    }

    private void turnOFF(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        world.playSound(player, pos, BeachpartySounds.RADIO_CLICK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        if (!world.isClient) {
            pressButton(state, world, pos, false);
            sendPacket(state, world, pos, false);
        }
    }

    public int tune(World world, BlockState blockState, BlockPos blockPos, int scrollValue) {
        if (scrollValue % RadioHelper.CHANNELS == 0) {
            return blockState.get(CHANNEL);
        }
        int currentChannel = blockState.get(CHANNEL);
        int newChannel = scrollValue < 0 ? (RadioHelper.CHANNELS - (Math.abs(currentChannel + scrollValue) % RadioHelper.CHANNELS)) % RadioHelper.CHANNELS : (currentChannel + scrollValue) % RadioHelper.CHANNELS;
        world.setBlockState(blockPos, blockState.with(CHANNEL, newChannel).with(POWERED, true), 3);
        world.createAndScheduleBlockTick(blockPos, this, RadioHelper.DELAY);
        return newChannel;
    }

    private void pressButton(BlockState state, World world, BlockPos pos, boolean on) {
            world.setBlockState(pos, state.with(ON, on).with(POWERED, true), 3);
            world.createAndScheduleBlockTick(pos, this, RadioHelper.DELAY);
    }

    private void sendPacket(BlockState state, World world, BlockPos pos, boolean on) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeBlockPos(pos);
        buffer.writeInt(state.get(CHANNEL));
        buffer.writeBoolean(on);
        for (PlayerEntity player : world.getPlayers()) {
            ServerPlayNetworking.send((ServerPlayerEntity) player, BeachpartyMessages.TURN_RADIO_S2C, buffer);
        }
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

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction facing = ctx.getSide();
        int channel = Random.create().nextBetween(0, CHANNELS - 1);
        return this.getDefaultState().with(CHANNEL, channel).with(FACING, facing);
    }
     */

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction facing = context.getPlayerFacing();
        if (facing.getAxis() != Direction.Axis.Y) {
            return this.getDefaultState().with(FACING, facing);
        }
        return this.getDefaultState().with(FACING, Direction.NORTH);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
        return this.getDefaultState().with(CHANNEL, Random.create().nextBetween(0, RadioHelper.CHANNELS - 1));
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            RadioHelper.setPlaying(pos, state.get(CHANNEL), false);
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            world.setBlockState(pos, state.with(POWERED, false), 3);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ON, CHANNEL, POWERED);
        builder.add(ON, CHANNEL, FACING);
    }

    static {
        ON = BooleanProperty.of("on");
        CHANNEL = IntProperty.of("channel", 0, RadioHelper.CHANNELS - 1);
        POWERED = Properties.POWERED;
    }
}