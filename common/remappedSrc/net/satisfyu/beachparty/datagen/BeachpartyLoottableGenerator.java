package net.satisfyu.beachparty.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class BeachpartyLoottableGenerator extends FabricBlockLootTableProvider {

    private static final float[] SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    protected BeachpartyLoottableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        this.add(ObjectRegistry.SAND_SLAB, BlockLoot::createSlabItemTable);
        this.dropSelf(ObjectRegistry.LOUNGE_CHAIR);
        this.dropSelf(ObjectRegistry.CHAIR);
        this.dropSelf(ObjectRegistry.TABLE);
        this.add(ObjectRegistry.BEACH_CHAIR, b -> createSinglePropConditionTable(b, BedBlock.PART, BedPart.HEAD));
        this.dropSelf(ObjectRegistry.TIKI_CHAIR);
        this.dropSelf(ObjectRegistry.TIKI_BAR);
        this.dropSelf(ObjectRegistry.STRIPPED_PALM_LOG);
        this.dropSelf(ObjectRegistry.PALM_LOG);
        this.dropSelf(ObjectRegistry.STRIPPED_PALM_WOOD);
        this.dropSelf(ObjectRegistry.PALM_WOOD);
        this.dropSelf(ObjectRegistry.MESSAGE_IN_A_BOTTLE);
        this.dropSelf(ObjectRegistry.PALM_PLANKS);
        this.dropSelf(ObjectRegistry.PALM_STAIRS);
        this.add(ObjectRegistry.PALM_SLAB, BlockLoot::createSlabItemTable);
        this.dropSelf(ObjectRegistry.PALM_FENCE);
        this.dropSelf(ObjectRegistry.PALM_BEAM);
        this.dropSelf(ObjectRegistry.PALM_FENCE_GATE);
        this.dropSelf(ObjectRegistry.PALM_BUTTON);
        this.dropSelf(ObjectRegistry.PALM_PRESSURE_PLATE);
        this.add(ObjectRegistry.PALM_DOOR, BlockLoot::createDoorTable);
        this.dropSelf(ObjectRegistry.PALM_TRAPDOOR);
        this.dropSelf(ObjectRegistry.PALM_SIGN);
        this.dropSelf(ObjectRegistry.DRIED_WHEAT_BLOCK);
        this.dropSelf(ObjectRegistry.DRIED_WHEAT_STAIRS);
        this.dropSelf(ObjectRegistry.DRIED_WHEAT_SLAB);
        this.dropSelf(ObjectRegistry.PALM_LEAVES);
        this.dropSelf(ObjectRegistry.PALM_TORCH);
        this.dropSelf(ObjectRegistry.PALM_WALL_TORCH);
        this.dropSelf(ObjectRegistry.PALM_TALL_TORCH);
        this.dropSelf(ObjectRegistry.RADIO);
        this.dropSelf(ObjectRegistry.CABINET);
        this.dropSelf(ObjectRegistry.MINI_FRIDGE);
        this.dropSelf(ObjectRegistry.SAND_BUCKET_BLOCK);
        this.dropSelf(ObjectRegistry.EMPTY_SAND_BUCKET_BLOCK);
        this.add(ObjectRegistry.BEACH_TOWEL, b -> createSinglePropConditionTable(b, BedBlock.PART, BedPart.HEAD));
        this.add(ObjectRegistry.SANDWAVES, b -> createOreDrop(b, Item.byBlock(Blocks.SAND)));
        this.add(ObjectRegistry.SAND_DIRTY, b -> createOreDrop(b, Items.STICK));
        this.add(ObjectRegistry.BEACH_GRASS, b -> createOreDrop(b, Items.STICK));
        this.add(ObjectRegistry.SAND_SEASTARS, b -> createOreDrop(b, Item.byBlock(Blocks.SAND)));
        this.add(ObjectRegistry.DRY_BUSH, b -> createOreDrop(b, Items.STICK));
        this.add(ObjectRegistry.DRY_BUSH_TALL, b -> createSinglePropConditionTable(b, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.add(ObjectRegistry.HAMMOCK, b -> createSinglePropConditionTable(b, BedBlock.PART, BedPart.HEAD));
        this.add(ObjectRegistry.DECK_CHAIR, b -> createSinglePropConditionTable(b, BedBlock.PART, BedPart.HEAD));
    }
}