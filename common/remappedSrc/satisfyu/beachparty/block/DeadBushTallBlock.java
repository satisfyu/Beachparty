package satisfyu.beachparty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DeadBushTallBlock extends TallFlowerBlock {
    public DeadBushTallBlock(Properties settings) {
        super(settings);
    }

    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON);
    }
}
