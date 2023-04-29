package net.satisfyu.beachparty.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class BeachpartyLoottableGenerator extends FabricBlockLootTableProvider {

    private static final float[] SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    protected BeachpartyLoottableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        this.addDrop(ObjectRegistry.SAND_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.LOUNGE_CHAIR);
        this.addDrop(ObjectRegistry.CHAIR);
        this.addDrop(ObjectRegistry.TABLE);
        this.addDrop(ObjectRegistry.BEACH_CHAIR, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.TIKI_CHAIR);
        this.addDrop(ObjectRegistry.TIKI_BAR);
        this.addDrop(ObjectRegistry.STRIPPED_PALM_LOG);
        this.addDrop(ObjectRegistry.PALM_LOG);
        this.addDrop(ObjectRegistry.STRIPPED_PALM_WOOD);
        this.addDrop(ObjectRegistry.PALM_WOOD);
        this.addDrop(ObjectRegistry.MESSAGE_IN_A_BOTTLE);
        this.addDrop(ObjectRegistry.PALM_PLANKS);
        this.addDrop(ObjectRegistry.PALM_STAIRS);
        this.addDrop(ObjectRegistry.PALM_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.PALM_FENCE);
        this.addDrop(ObjectRegistry.PALM_BEAM);
        this.addDrop(ObjectRegistry.PALM_FENCE_GATE);
        this.addDrop(ObjectRegistry.PALM_BUTTON);
        this.addDrop(ObjectRegistry.PALM_PRESSURE_PLATE);
        this.addDrop(ObjectRegistry.PALM_DOOR, BlockLootTableGenerator::doorDrops);
        this.addDrop(ObjectRegistry.PALM_TRAPDOOR);
        this.addDrop(ObjectRegistry.PALM_SIGN);
        this.addDrop(ObjectRegistry.DRIED_WHEAT_BLOCK);
        this.addDrop(ObjectRegistry.DRIED_WHEAT_STAIRS);
        this.addDrop(ObjectRegistry.DRIED_WHEAT_SLAB);
        this.addDrop(ObjectRegistry.PALM_LEAVES);
        this.addDrop(ObjectRegistry.PALM_TORCH);
        this.addDrop(ObjectRegistry.PALM_WALL_TORCH);
        this.addDrop(ObjectRegistry.PALM_TALL_TORCH);
        this.addDrop(ObjectRegistry.RADIO);
        this.addDrop(ObjectRegistry.CABINET);
        this.addDrop(ObjectRegistry.MINI_FRIDGE);
        this.addDrop(ObjectRegistry.SAND_BUCKET_BLOCK);
        this.addDrop(ObjectRegistry.EMPTY_SAND_BUCKET_BLOCK);
        this.addDrop(ObjectRegistry.BEACH_TOWEL, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.SANDWAVES, b -> oreDrops(b, Item.fromBlock(Blocks.SAND)));
        this.addDrop(ObjectRegistry.SAND_DIRTY, b -> oreDrops(b, Items.STICK));
        this.addDrop(ObjectRegistry.BEACH_GRASS, b -> oreDrops(b, Items.STICK));
        this.addDrop(ObjectRegistry.SAND_SEASTARS, b -> oreDrops(b, Item.fromBlock(Blocks.SAND)));
        this.addDrop(ObjectRegistry.DRY_BUSH, b -> oreDrops(b, Items.STICK));
        this.addDrop(ObjectRegistry.DRY_BUSH_TALL, b -> dropsWithProperty(b, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
    }
}