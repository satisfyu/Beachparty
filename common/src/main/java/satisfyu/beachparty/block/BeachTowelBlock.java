package satisfyu.beachparty.block;

import de.cristelknight.doapi.common.util.ChairUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import satisfyu.beachparty.util.BeachpartyUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BeachTowelBlock extends HorizontalDirectionalBlock {
	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
	private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
		VoxelShape shape = Shapes.empty();
		shape = Shapes.or(shape, Shapes.box(0, 0, 0.0625, 0.9375, 0.0625, 0.9375));
		return shape;
	};

	private static final VoxelShape VOXEL_SHAPE = voxelShapeSupplier.get();

	public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
		for (Direction direction : Direction.Plane.HORIZONTAL.stream().toList()) {
			map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, VOXEL_SHAPE));
		}
	});

	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Direction direction = getOppositePartDirection(state).getOpposite();
		return SHAPE.get(direction);
	}

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public BeachTowelBlock(Properties settings) {
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(PART, BedPart.FOOT));
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
		if (direction == getDirectionTowardsOtherPart(state.getValue(PART), state.getValue(FACING))) {
			return neighborState.is(this) && neighborState.getValue(PART) != state.getValue(PART) ? state : Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
		}
	}
	
	private static Direction getDirectionTowardsOtherPart(BedPart part, Direction direction) {
		return part == BedPart.FOOT ? direction : direction.getOpposite();
	}

	public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		if (!world.isClientSide && player.isCreative()) {
			removeOtherPart(world, pos, state, player);
		}

		super.playerWillDestroy(world, pos, state, player);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction direction = ctx.getHorizontalDirection();
		BlockPos blockPos = ctx.getClickedPos();
		BlockPos blockPos2 = blockPos.relative(direction);
		Level world = ctx.getLevel();
		return world.getBlockState(blockPos2).canBeReplaced(ctx) && world.getWorldBorder().isWithinBounds(blockPos2) ? this.defaultBlockState().setValue(FACING, direction) : null;
	}

	public static Direction getOppositePartDirection(BlockState state) {
		Direction direction = state.getValue(FACING);
		return state.getValue(PART) == BedPart.HEAD ? direction.getOpposite() : direction;
	}

	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.setPlacedBy(world, pos, state, placer, itemStack);
		if (!world.isClientSide) {
			placeOtherPart(world, pos, state);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand
			hand, BlockHitResult hit) {
		return ChairUtil.onUse(world, player, hand, hit, -0.4);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART);
	}
	
	private void placeOtherPart(Level world, BlockPos pos, BlockState state) {
		BlockPos blockPos = pos.relative(state.getValue(FACING));
		world.setBlock(blockPos, state.setValue(PART, BedPart.HEAD), Block.UPDATE_ALL);
		world.blockUpdated(pos, Blocks.AIR);
		state.updateNeighbourShapes(world, pos, Block.UPDATE_ALL);
	}
	
	private void removeOtherPart(Level world, BlockPos pos, BlockState state, Player player) {
		BedPart bedPart = state.getValue(PART);
		if (bedPart == BedPart.FOOT) {
			BlockPos blockPos = pos.relative(getDirectionTowardsOtherPart(bedPart, state.getValue(FACING)));
			BlockState blockState = world.getBlockState(blockPos);
			if (blockState.is(this) && blockState.getValue(PART) == BedPart.HEAD) {
				world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
				if (player != null)
					world.levelEvent(player, LevelEvent.PARTICLES_DESTROY_BLOCK, blockPos, Block.getId(blockState));
			}
		}
	}
	@Override
	public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
		tooltip.add(Component.translatable("tooltip.beachparty.canbeplaced").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
	}
}