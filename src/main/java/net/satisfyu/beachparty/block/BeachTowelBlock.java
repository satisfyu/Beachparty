package net.satisfyu.beachparty.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.satisfyu.beachparty.entity.chair.ChairUtil;
import net.satisfyu.beachparty.util.BeachpartyUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BeachTowelBlock extends HorizontalFacingBlock {
	public static final EnumProperty<BedPart> PART = Properties.BED_PART;
	private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
		VoxelShape shape = VoxelShapes.empty();
		shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0, 0.0625, 0.9375, 0.0625, 0.9375));
		return shape;
	};

	private static final VoxelShape VOXEL_SHAPE = voxelShapeSupplier.get();

	public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
		for (Direction direction : Direction.Type.HORIZONTAL.stream().toList()) {
			map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, VOXEL_SHAPE));
		}
	});

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction direction = getOppositePartDirection(state).getOpposite();
		return SHAPE.get(direction);
	}

	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public BeachTowelBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(PART, BedPart.FOOT));
	}

	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == getDirectionTowardsOtherPart(state.get(PART), state.get(FACING))) {
			return neighborState.isOf(this) && neighborState.get(PART) != state.get(PART) ? state : Blocks.AIR.getDefaultState();
		} else {
			return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		}
	}
	
	private static Direction getDirectionTowardsOtherPart(BedPart part, Direction direction) {
		return part == BedPart.FOOT ? direction : direction.getOpposite();
	}

	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient && player.isCreative()) {
			removeOtherPart(world, pos, state, player);
		}

		super.onBreak(world, pos, state, player);
	}

	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getPlayerFacing().rotateYClockwise();
		BlockPos blockPos = ctx.getBlockPos();
		BlockPos blockPos2 = blockPos.offset(direction);
		World world = ctx.getWorld();
		return world.getBlockState(blockPos2).canReplace(ctx) && world.getWorldBorder().contains(blockPos2) ? this.getDefaultState().with(FACING, direction) : null;
	}

	public static Direction getOppositePartDirection(BlockState state) {
		Direction direction = state.get(FACING);
		return state.get(PART) == BedPart.HEAD ? direction.getOpposite() : direction;
	}

	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		if (!world.isClient) {
			placeOtherPart(world, pos, state);
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand
			hand, BlockHitResult hit) {
		return ChairUtil.onUse(world, player, hand, hit, -0.4);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART);
	}
	
	private void placeOtherPart(World world, BlockPos pos, BlockState state) {
		BlockPos blockPos = pos.offset(state.get(FACING));
		world.setBlockState(blockPos, state.with(PART, BedPart.HEAD), Block.NOTIFY_ALL);
		world.updateNeighbors(pos, Blocks.AIR);
		state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
	}
	
	private void removeOtherPart(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BedPart bedPart = state.get(PART);
		if (bedPart == BedPart.FOOT) {
			BlockPos blockPos = pos.offset(getDirectionTowardsOtherPart(bedPart, state.get(FACING)));
			BlockState blockState = world.getBlockState(blockPos);
			if (blockState.isOf(this) && blockState.get(PART) == BedPart.HEAD) {
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
				if (player != null)
					world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
			}
		}
	}
}