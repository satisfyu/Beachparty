package net.satisfyu.beachparty.registry;

import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import com.terraformersmc.terraform.wood.block.StrippableLogBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.satisfyu.beachparty.Beachparty;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.block.*;
import net.satisfyu.beachparty.item.BeachHatItem;
import net.satisfyu.beachparty.item.CocktailItem;
import net.satisfyu.beachparty.item.SwimwearArmorItem;
import net.satisfyu.beachparty.util.BeachpartyArmorMaterials;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ObjectRegistry {
    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();



    public static final Block BEACH_SAND = register("beach_sand", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BEACH_SAND_DIRTY = register("beach_sand_dirty", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BEACH_SAND_SEASTARS = register("beach_sand_seastars", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BEACH_SANDWAVES = register("beach_sandwaves", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block BEACH_SAND_SLAB = register("beach_sand_slab", new SandSlabBlock(getSlabSettings().sounds(BlockSoundGroup.SAND)));
    public static final Block DRY_BUSH = register("dry_bush", new DeadBushBlock(FabricBlockSettings.copyOf(Blocks.DANDELION)));
    public static final Block DRY_BUSH_TALL = register("dry_bush_tall", new DeadBushTallBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH)));
    public static final Block LOUNGE_CHAIR = register("lounge_chair", new LoungeChairBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block CHAIR = register("chair", new ChairBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block TABLE = register("table", new TableBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block BEACH_CHAIR = register("beach_chair", new BeachChairBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block TIKI_CHAIR = register("tiki_chair", new TikiChairBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block TIKI_BAR = register("tiki_bar", new TikiBarBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block STRIPPED_PALM_LOG = registerLog("stripped_palm_log");
    public static final Block PALM_LOG = register("palm_log", new StrippableLogBlock(() -> STRIPPED_PALM_LOG, MapColor.OAK_TAN, getLogBlockSettings()));
    public static final Block STRIPPED_PALM_WOOD = registerLog("stripped_palm_wood");
    public static final Block PALM_WOOD = register("palm_wood", new StrippableLogBlock(() -> STRIPPED_PALM_WOOD, MapColor.OAK_TAN, getLogBlockSettings()));
    public static final Block PALM_PLANKS = register("palm_planks", new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PALM_STAIRS = register("palm_stairs", new StairsBlock(PALM_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(PALM_PLANKS)));
    public static final Block PALM_SLAB = register("palm_slab", new SlabBlock(getSlabSettings()));
    public static final Block PALM_FENCE = register("palm_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block PALM_FENCE_GATE = register("palm_fence_gate", new FenceGateBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block PALM_BUTTON = register("palm_button", new WoodenButtonBlock(AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final Block PALM_PRESSURE_PLATE = register("palm_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block PALM_DOOR = register("palm_door", new DoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block PALM_TRAPDOOR = register("palm_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    private static final BeachpartyIdentifier PALM_SIGN_TEXTURE = new BeachpartyIdentifier("entity/signs/palm");
    public static final TerraformSignBlock PALM_SIGN = register("palm_sign", new TerraformSignBlock(PALM_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)), false);
    public static final Block PALM_WALL_SIGN = register("palm_wall_sign", new TerraformWallSignBlock(PALM_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)), false);
    public static final Item PALM_SIGN_ITEM = register("palm_sign", new SignItem(getSettings().maxCount(16), PALM_SIGN, PALM_WALL_SIGN));
    public static final Block DRIED_WHEAT_BLOCK = register("dried_wheat_block", new HayBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK)));
    public static final Block DRIED_WHEAT_STAIRS = register("dried_wheat_stairs", new StairsBlock(PALM_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(PALM_PLANKS).sounds(BlockSoundGroup.GRASS)));
    public static final Block DRIED_WHEAT_SLAB = register("dried_wheat_slab", new SlabBlock(getSlabSettings().sounds(BlockSoundGroup.GRASS)));
    public static final Block PALM_LEAVES = register("palm_leaves", new LeavesBlock(FabricBlockSettings.copy(Blocks.JUNGLE_LEAVES)));
    public static final Item PALM_SAPLING = register("palm_sapling", new Item(getSettings()));
    public static final Block PALM_TORCH = register("palm_torch", new TorchBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> {return 14;}).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME));
    public static final Block PALM_TORCH_TALL = register("palm_torch_tall", new TorchBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> {return 14;}).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME));
    public static final Block RADIO = register("radio", new Block(FabricBlockSettings.of(Material.DECORATION))); // RadioBlock(FabricBlockSettings.copy(Blocks.JUKEBOX)));
    public static final Item SAND_BUCKET = register("sand_bucket", new Item(getSettings()));
    public static final Block COCONUT = register("coconut", new CoconutBlock(FabricBlockSettings.of(Material.BAMBOO)));
    public static final Item COCONUT_OPEN = register("coconut_open", new Item(getSettings()));
    public static final Item COCONUT_COCKTAIL = register("coconut_cocktail", new CocktailItem(getSettings(), StatusEffects.INSTANT_HEALTH));
    public static final Item SWEETBERRIES_COCKTAIL = register("sweetberries_cocktail", new CocktailItem(getSettings(), StatusEffects.ABSORPTION));
    public static final Item COCOA_COCKTAIL = register("cocoa_cocktail", new CocktailItem(getSettings(), StatusEffects.REGENERATION));
    public static final Item PUMPKIN_COCKTAIL = register("pumpkin_cocktail", new CocktailItem(getSettings(), StatusEffects.FIRE_RESISTANCE));
    public static final Item HONEY_COCKTAIL = register("honey_cocktail", new CocktailItem(getSettings(), StatusEffects.HASTE));
    public static final Item MELON_COCKTAIL = register("melon_cocktail", new CocktailItem(getSettings(), StatusEffects.LUCK));
    public static final Item ICECREAM_COCONUT = register("icecream_coconut", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item ICECREAM_MELON = register("icecream_melon", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item ICECREAM_CACTUS = register("icecream_cactus", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item ICECREAM_SWEETBERRIES = register("icecream_sweetberries", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item BEACH_HAT = register("beach_hat", new BeachHatItem(getSettings().rarity(Rarity.COMMON)));
    public static final Item TRUNKS = register("trunks", new SwimwearArmorItem(BeachpartyArmorMaterials.TRUNKS, EquipmentSlot.LEGS, getSettings().rarity(Rarity.COMMON)));
    public static final Item BIKINI = register("bikini", new SwimwearArmorItem(BeachpartyArmorMaterials.BIKINI, EquipmentSlot.LEGS, getSettings().rarity(Rarity.COMMON)));


    private static PillarBlock registerLog(String path) {
        return register(path, new PillarBlock(getLogBlockSettings()));
    }

    private static AbstractBlock.Settings getLogBlockSettings() {
        return AbstractBlock.Settings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD);
    }

    private static AbstractBlock.Settings getSlabSettings() {
        return getLogBlockSettings().resistance(3.0F);
    }

    private static <T extends Block> T register(String path, T block) {
        return register(path, block, true);
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem) {
        return register(path, block, registerItem, settings -> {
        });
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem, Consumer<Item.Settings> consumer) {
        return register(path, block, registerItem, BlockItem::new, consumer);
    }

    private static <T extends Block> T register(String path, T block, boolean registerItem, BiFunction<T, Item.Settings, ? extends BlockItem> function, Consumer<Item.Settings> consumer) {
        final Identifier id = new BeachpartyIdentifier(path);
        BLOCKS.put(id, block);
        if (registerItem) {
            ITEMS.put(id, function.apply(block, getSettings(consumer)));
        }
        return block;
    }

    private static Item.Settings getSettings(Consumer<Item.Settings> consumer) {
        Item.Settings settings = new Item.Settings().group(Beachparty.CREATIVE_TAB);
        consumer.accept(settings);
        return settings;
    }

    private static Item.Settings getSettings() {
        return getSettings(settings -> {
        });
    }

    private static <T extends Item> T register(String path, T item) {
        final Identifier id = new BeachpartyIdentifier(path);
        ITEMS.put(id, item);
        return item;
    }

    public static List<ItemConvertible> getItemConvertibles() {
        List<ItemConvertible> list = new ArrayList<>();
        for (Block entry : BLOCKS.values()) {
            if (entry.asItem() != null) {
                list.add(entry);
            }
        }
        list.addAll(ITEMS.values());
        return list;
    }

    public static Map<Identifier, Block> getBlocks() {
        return Collections.unmodifiableMap(BLOCKS);
    }

    public static Map<Identifier, Item> getItems() {
        return Collections.unmodifiableMap(ITEMS);
    }


    public static void init() {
        for (Map.Entry<Identifier, Block> entry : BLOCKS.entrySet()) {
            Registry.register(Registry.BLOCK, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Identifier, Item> entry : ITEMS.entrySet()) {
            Registry.register(Registry.ITEM, entry.getKey(), entry.getValue());
        }
        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(PALM_PLANKS, 5, 20);
        flammableRegistry.add(STRIPPED_PALM_LOG, 5, 5);
        flammableRegistry.add(PALM_LOG, 5, 5);
        flammableRegistry.add(STRIPPED_PALM_WOOD, 5, 5);
        flammableRegistry.add(PALM_WOOD, 5, 5);
        flammableRegistry.add(PALM_SLAB, 5, 20);
        flammableRegistry.add(PALM_STAIRS, 5, 20);
        flammableRegistry.add(PALM_FENCE, 5, 20);
        flammableRegistry.add(PALM_FENCE_GATE, 5, 20);
        flammableRegistry.add(CHAIR, 5, 5);
        flammableRegistry.add(LOUNGE_CHAIR, 5, 5);
        flammableRegistry.add(BEACH_CHAIR, 5, 5);
        flammableRegistry.add(PALM_DOOR, 5, 5);
        flammableRegistry.add(PALM_TRAPDOOR, 5, 5);
        flammableRegistry.add(PALM_PRESSURE_PLATE, 2, 2);
        flammableRegistry.add(PALM_BUTTON, 2, 2);
        flammableRegistry.add(DRIED_WHEAT_BLOCK, 2, 25);
        flammableRegistry.add(DRIED_WHEAT_SLAB, 2, 25);
        flammableRegistry.add(DRIED_WHEAT_STAIRS, 2, 25);



        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
        fuelRegistry.add(PALM_FENCE, 300);
        fuelRegistry.add(PALM_FENCE_GATE, 300);
    }
}
