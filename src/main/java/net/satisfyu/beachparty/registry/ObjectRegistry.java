package net.satisfyu.beachparty.registry;

import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import com.terraformersmc.terraform.wood.block.StrippableLogBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.satisfyu.beachparty.Beachparty;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.block.*;
import net.satisfyu.beachparty.item.*;
import net.satisfyu.beachparty.item.BeachHatItem;
import net.satisfyu.beachparty.item.BetterCustomArmorModelItem;
import net.satisfyu.beachparty.item.DrinkBlockItem;
import net.satisfyu.beachparty.item.SwimwearArmorItem;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ObjectRegistry {
    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();

    public static final Block BEACH_GRASS = register("beach_grass", new DeadBushBlock(FabricBlockSettings.copyOf(Blocks.ALLIUM)));
    public static final Block DRY_BUSH = register("dry_bush", new DeadBushBlock(FabricBlockSettings.copyOf(Blocks.DANDELION)));
    public static final Block DRY_BUSH_TALL = register("dry_bush_tall", new DeadBushTallBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH)));
    public static final Block PALM_LEAVES = register("palm_leaves", new LeavesBlock(FabricBlockSettings.copy(Blocks.JUNGLE_LEAVES)));
    public static final Item PALM_SAPLING = register("palm_sapling", new Item(getSettings()));
    public static final Block SAND_DIRTY = register("sand_dirty", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block SAND_SEASTARS = register("sand_seastars", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block SANDWAVES = register("sandwaves", new SandBlock(14406560, AbstractBlock.Settings.of(Material.AGGREGATE, MapColor.PALE_YELLOW).strength(0.5F).sounds(BlockSoundGroup.SAND)));
    public static final Block SAND_SLAB = register("sand_slab", new SandSlabBlock(getSlabSettings().sounds(BlockSoundGroup.SAND)));
    public static final Block SAND_PILE = register("sand_pile", new SandPileBlock(14406560, FabricBlockSettings.copy(Blocks.SAND)), false);
    public static final Block STRIPPED_PALM_LOG = registerLog("stripped_palm_log");
    public static final Block PALM_LOG = register("palm_log", new StrippableLogBlock(() -> STRIPPED_PALM_LOG, MapColor.OAK_TAN, getLogBlockSettings()));
    public static final Block STRIPPED_PALM_WOOD = registerLog("stripped_palm_wood");
    public static final Block PALM_WOOD = register("palm_wood", new StrippableLogBlock(() -> STRIPPED_PALM_WOOD, MapColor.OAK_TAN, getLogBlockSettings()));
    public static final Block PALM_BEAM = registerLog("palm_beam");
    public static final Block PALM_PLANKS = register("palm_planks", new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PALM_FLOORBOARD = register("palm_floorboard", new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block PALM_STAIRS = register("palm_stairs", new StairsBlock(PALM_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(PALM_PLANKS)));
    public static final Block PALM_SLAB = register("palm_slab", new SlabBlock(getSlabSettings()));
    public static final Block PALM_FENCE = register("palm_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block PALM_FENCE_GATE = register("palm_fence_gate", new FenceGateBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE)));
    public static final Block PALM_BUTTON = register("palm_button", new WoodenButtonBlock(AbstractBlock.Settings.copy(Blocks.OAK_BUTTON)));
    public static final Block PALM_PRESSURE_PLATE = register("palm_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final Block PALM_DOOR = register("palm_door", new DoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
    public static final Block PALM_TRAPDOOR = register("palm_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
    public static final Block DRIED_WHEAT_BLOCK = register("dried_wheat_block", new HayBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK)));
    public static final Block DRIED_WHEAT_STAIRS = register("dried_wheat_stairs", new StairsBlock(PALM_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(PALM_PLANKS).sounds(BlockSoundGroup.GRASS)));
    public static final Block DRIED_WHEAT_SLAB = register("dried_wheat_slab", new SlabBlock(getSlabSettings().sounds(BlockSoundGroup.GRASS)));
    public static final Block LOUNGE_CHAIR = register("lounge_chair", new LoungeChairBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block CHAIR = register("chair", new ChairBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block TABLE = register("table", new TableBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block BEACH_CHAIR = register("beach_chair", new BeachChairBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block TIKI_CHAIR = register("tiki_chair", new TikiChairBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block TIKI_BAR = register("tiki_bar", new TikiBarBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    private static final BeachpartyIdentifier PALM_SIGN_TEXTURE = new BeachpartyIdentifier("entity/signs/palm");
    public static final TerraformSignBlock PALM_SIGN = register("palm_sign", new TerraformSignBlock(PALM_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_SIGN)), false);
    public static final Block PALM_WALL_SIGN = register("palm_wall_sign", new TerraformWallSignBlock(PALM_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN)), false);
    public static final Item PALM_SIGN_ITEM = register("palm_sign", new SignItem(getSettings().maxCount(16), PALM_SIGN, PALM_WALL_SIGN));
    public static final Block MINI_FRIDGE = register("mini_fridge", new MiniFridgeBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.COPPER)));
    public static final Block RADIO = register("radio", new RadioBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.BAMBOO)));
    public static final Block SAND_BUCKET_BLOCK = register("sand_bucket_block", new SandBucketBlock(FabricBlockSettings.of(Material.SOIL)), false);//TODO was für Material?
    public static final Item OVERGROWN_DISC = register("overgrown_disc", new MusicDiscItem(1, SoundEventRegistry.RADIO_BEACHPARTY, getSettings(), 214));
    public static final Block MESSAGE_IN_A_BOTTLE = register("message_in_a_bottle", new ShapeBlock(FabricBlockSettings.copy(Blocks.GLASS), Block.createCuboidShape(4.0f, 0.0f, 4.0f, 12.0f, 6.0f, 12.0f)));
    public static final Block EMPTY_SAND_BUCKET_BLOCK = register("empty_sand_bucket_block", new SandBucketBlock(FabricBlockSettings.of(Material.SOIL)), false);
    public static final Item SAND_BUCKET = register("sand_bucket", new SandBucketItem(SAND_BUCKET_BLOCK, getSettingsWithoutTab().maxCount(1)));
    public static final Item EMPTY_SAND_BUCKET = register("empty_sand_bucket", new SandBucketItem(EMPTY_SAND_BUCKET_BLOCK, getSettings()));
    public static final Block COCONUT_BLOCK = register("coconut_block", new CoconutBlock(FabricBlockSettings.of(Material.BAMBOO)), false);
    public static final Item COCONUT = register("coconut", new CoconutItem(COCONUT_BLOCK, getSettings()));
    public static final Item COCONUT_OPEN = register("coconut_open", new Item(getSettings().food(FoodComponents.CARROT)));
    public static final Block COCONUT_COCKTAIL = registerCocktail("coconut_cocktail", new CocktailBlock(getCocktailSettings()), StatusEffects.INSTANT_HEALTH);
    public static final Block SWEETBERRIES_COCKTAIL = registerCocktail("sweetberries_cocktail", new CocktailBlock(getCocktailSettings()), StatusEffects.ABSORPTION);
    public static final Block COCOA_COCKTAIL = registerCocktail("cocoa_cocktail", new CocktailBlock(getCocktailSettings()), StatusEffects.REGENERATION);
    public static final Block PUMPKIN_COCKTAIL = registerCocktail("pumpkin_cocktail", new CocktailBlock(getCocktailSettings()), StatusEffects.FIRE_RESISTANCE);
    public static final Block HONEY_COCKTAIL = registerCocktail("honey_cocktail", new CocktailBlock(getCocktailSettings()), StatusEffects.HASTE);
    public static final Block MELON_COCKTAIL = registerCocktail("melon_cocktail", new CocktailBlock(getCocktailSettings()), StatusEffects.LUCK);
    public static final Item ICECREAM_COCONUT = register("icecream_coconut", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item ICECREAM_MELON = register("icecream_melon", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item ICECREAM_CACTUS = register("icecream_cactus", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item ICECREAM_SWEETBERRIES = register("icecream_sweetberries", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item ICECREAM_CHOCOLATE = register("icecream_chocolate", new Item(getSettings().food(FoodComponents.COOKIE)));
    public static final Item RAW_PELICAN = register("raw_pelican", new Item(getSettings().food(FoodComponents.BEEF)));
    public static final Item COOKED_PELICAN = register("cooked_pelican", new Item(getSettings().food(FoodComponents.COOKED_BEEF)));
    public static final Block BEACH_TOWEL = register("beach_towel", new BeachTowelBlock(FabricBlockSettings.copy(Blocks.WHITE_WOOL).sounds(BlockSoundGroup.WOOL)));
    public static final Item BEACH_HAT = register("beach_hat", new BeachHatItem(getSettings().rarity(Rarity.COMMON)));
    public static final Item SUNGLASSES = register("sunglasses", new SwimwearArmorItem(MaterialsRegistry.SUNGLASSES, EquipmentSlot.HEAD, getSettings()));
    public static final Item TRUNKS = register("trunks", new SwimwearArmorItem(MaterialsRegistry.TRUNKS, EquipmentSlot.LEGS, getSettings().rarity(Rarity.COMMON)));
    public static final Item BIKINI = register("bikini", new SwimwearArmorItem(MaterialsRegistry.BIKINI, EquipmentSlot.LEGS, getSettings().rarity(Rarity.COMMON)));
    public static final Item SWIM_WINGS = register("swim_wings", new SwimwearArmorItem(MaterialsRegistry.SWIM_WINGS, EquipmentSlot.CHEST, getSettings()));
    public static final Item RUBBER_RING_BLUE = register("rubber_ring_blue", new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_blue.png"), -0.7f));
    public static final Item RUBBER_RING_PINK = register("rubber_ring_pink", new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_pink.png"), -0.7f));
    public static final Item RUBBER_RING_STRIPPED = register("rubber_ring_stripped", new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_stripped.png"), -0.7f));
    public static final Item RUBBER_RING_PELICAN = register("rubber_ring_pelican", new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/entity/rubber_ring_pelican.png"), -0.7f));
    public static final Item RUBBER_RING_AXOLOTL = register("rubber_ring_axolotl", new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/entity/rubber_ring_axolotl.png"), -0.7f));
    public static final Item POOL_NOODLE_BLUE = register("pool_noodle_blue", new PoolnoodleItem(ToolMaterials.WOOD, 1, -1.4F, getSettings()));
    public static final Item POOL_NOODLE_RED = register("pool_noodle_red", new SwordItem(ToolMaterials.WOOD, 1, -1.4F, getSettings()));
    public static final Item POOL_NOODLE_GREEN = register("pool_noodle_green", new SwordItem(ToolMaterials.WOOD, 1, -1.4F, getSettings()));
    public static final Item POOL_NOODLE_YELLOW = register("pool_noodle_yellow", new SwordItem(ToolMaterials.WOOD, 1, -1.4F, getSettings()));
    public static final Block PALM_TORCH = register("palm_torch", new TorchBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME), false);
    public static final Block PALM_WALL_TORCH = register("palm_wall_torch", new WallTorchBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD).dropsLike(PALM_TORCH), ParticleTypes.FLAME), false);
    public static final Item PALM_TORCH_ITEM = register("palm_torch_item", new WallStandingBlockItem(ObjectRegistry.PALM_TORCH, ObjectRegistry.PALM_WALL_TORCH, getSettings()));
    public static final Block PALM_TALL_TORCH = register("palm_tall_torch", new TallTorchBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME));
    public static final Item PELICAN_SPAWN_EGG = register("pelican_spawn_egg", new SpawnEggItem(EntityRegistry.PELICAN, 16710877, 16116736, getSettings()));
    public static final Block SANDCASTLE = register("sandcastle", new SandCastleBlock(FabricBlockSettings.copy(Blocks.SAND)), false); //TODO was für Material?


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

    private static Item.Settings getSettingsWithoutTab(Consumer<Item.Settings> consumer) {
        Item.Settings settings = new Item.Settings();
        consumer.accept(settings);
        return settings;
    }


    private static <T extends Block> T registerCocktail(String path, T block, StatusEffect effect) {
        return register(path, block, true, DrinkBlockItem::new, settings -> settings.food(wineFoodComponent(effect)));
    }



    private static FoodComponent wineFoodComponent(StatusEffect effect) {
        FoodComponent.Builder component = new FoodComponent.Builder().hunger(1);
        if(effect != null) component.statusEffect(new StatusEffectInstance(effect, 45 * 20), 1.0f);
        return component.build();
    }
    private static AbstractBlock.Settings getCocktailSettings() {
        return AbstractBlock.Settings.copy(Blocks.GLASS).nonOpaque().breakInstantly();
    }

    private static Item.Settings getSettings() {
        return getSettings(settings -> {
        });
    }

    private static Item.Settings getSettingsWithoutTab() {
        return getSettingsWithoutTab(settings -> {
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

    private static <T extends MobEntity> Item registerSpawnEgg(EntityType<T> entityType, int color1, int color2){
        return register(Registry.ENTITY_TYPE.getId(entityType).toString().split(":")[1] + "_spawn_egg", new SpawnEggItem(entityType, color1, color2, new Item.Settings()));
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
        flammableRegistry.add(PALM_BEAM, 5, 20);

        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
        fuelRegistry.add(PALM_FENCE, 300);
        fuelRegistry.add(PALM_FENCE_GATE, 300);
        fuelRegistry.add(PALM_PLANKS, 300);
        fuelRegistry.add(PALM_LOG, 300);
        fuelRegistry.add(PALM_WOOD, 300);
        fuelRegistry.add(STRIPPED_PALM_LOG, 300);
        fuelRegistry.add(STRIPPED_PALM_WOOD, 300);

        registerCompostableItem(ObjectRegistry.DRY_BUSH, 0.4F);
        registerCompostableItem(ObjectRegistry.DRY_BUSH_TALL, 0.4F);
        registerCompostableItem(ObjectRegistry.PALM_LEAVES, 0.6F);
        registerCompostableItem(ObjectRegistry.PALM_SAPLING, 0.4F);
        registerCompostableItem(ObjectRegistry.BEACH_GRASS, 0.4F);
        registerCompostableItem(ObjectRegistry.COCONUT, 0.3F);
        registerCompostableItem(ObjectRegistry.COCONUT_OPEN, 0.3F);



    }

    public static void registerCompostableItem(ItemConvertible item, float chance) {
        if (item.asItem() != Items.AIR) {
            ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), chance);
        }
    }
}

