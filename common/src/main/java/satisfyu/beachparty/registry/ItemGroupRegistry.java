package satisfyu.beachparty.registry;

import dev.architectury.registry.CreativeTabRegistry;

import static satisfyu.beachparty.Beachparty.BEACHPARTY_TAB;
import static satisfyu.beachparty.registry.ObjectRegistry.*;


public class ItemGroupRegistry {


    public static void addItems() {

        CreativeTabRegistry.append(BEACHPARTY_TAB,
                BEACH_GRASS.get(), DRY_BUSH.get(), DRY_BUSH_TALL.get(), PALM_LEAVES.get(), PALM_SAPLING.get(),
                SAND_DIRTY.get(), SAND_SEASTARS.get(), SANDWAVES.get(), SAND_SLAB.get(), SAND_PILE.get(),
                STRIPPED_PALM_LOG.get(), PALM_LOG.get(), STRIPPED_PALM_WOOD.get(), PALM_WOOD.get(),
                PALM_BEAM.get(), PALM_PLANKS.get(), PALM_FLOORBOARD.get(), PALM_STAIRS.get(), PALM_SLAB.get(),
                PALM_FENCE.get(), PALM_FENCE_GATE.get(), PALM_BUTTON.get(), PALM_PRESSURE_PLATE.get(), PALM_DOOR.get(),
                PALM_TRAPDOOR.get(), DRIED_WHEAT_BLOCK.get(), DRIED_WHEAT_STAIRS.get(), DRIED_WHEAT_SLAB.get(),
                LOUNGE_CHAIR.get(), CHAIR.get(), TABLE.get(), BEACH_CHAIR.get(), DECK_CHAIR.get(), HAMMOCK.get(),
                TIKI_CHAIR.get(), TIKI_BAR.get(), CABINET.get(), MINI_FRIDGE.get(), RADIO.get(), MESSAGE_IN_A_BOTTLE.get(),
                COCONUT_OPEN.get(), COCONUT_COCKTAIL.get(), SWEETBERRIES_COCKTAIL.get(), COCOA_COCKTAIL.get(),
                PUMPKIN_COCKTAIL.get(), HONEY_COCKTAIL.get(), MELON_COCKTAIL.get(), MESSAGE_IN_A_BOTTLE_ITEM.get(),
                SEASHELL.get(), COCONUT.get(), ICECREAM_COCONUT.get(), ICECREAM_MELON.get(), ICECREAM_CACTUS.get(),
                ICECREAM_SWEETBERRIES.get(), ICECREAM_CHOCOLATE.get(), RAW_PELICAN.get(), COOKED_PELICAN.get(),
                RAW_MUSSEL_MEAT.get(), COOKED_MUSSEL_MEAT.get(), BEACH_TOWEL.get(), BEACH_HAT.get(), SUNGLASSES.get(),
                TRUNKS.get(), BIKINI.get(), CROCS.get(), SWIM_WINGS.get(), RUBBER_RING_BLUE.get(), RUBBER_RING_PINK.get(),
                RUBBER_RING_STRIPPED.get(), RUBBER_RING_PELICAN.get(), RUBBER_RING_AXOLOTL.get(), POOL_NOODLE.get(),
                PALM_TORCH.get(), PALM_WALL_TORCH.get(), PALM_TORCH_ITEM.get(), PALM_TALL_TORCH.get(),
                PELICAN_SPAWN_EGG.get(), SANDCASTLE.get(), COCONUT_HANGING.get()
        );
    }
}

