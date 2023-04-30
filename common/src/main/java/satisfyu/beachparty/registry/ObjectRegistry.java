package satisfyu.beachparty.registry;

import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.block.*;
import satisfyu.beachparty.item.*;
import satisfyu.beachparty.util.sign.block.TerraformSignBlock;
import satisfyu.beachparty.util.sign.block.TerraformWallSignBlock;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Beachparty.MOD_ID, Registry.ITEM_REGISTRY);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Beachparty.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> BEACH_GRASS = registerWithItem("beach_grass", () -> new DeadBushBlock(BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final RegistrySupplier<Block> DRY_BUSH = registerWithItem("dry_bush", () -> new DeadBushBlock(BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> DRY_BUSH_TALL = registerWithItem("dry_bush_tall", () -> new DeadBushTallBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> PALM_LEAVES = registerWithItem("palm_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LEAVES)));
    public static final RegistrySupplier<Item> PALM_SAPLING = registerItem("palm_sapling", () -> new Item(getSettings()));
    public static final RegistrySupplier<Block> SAND_DIRTY = registerWithItem("sand_dirty", () -> new SandBlock(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistrySupplier<Block> SAND_SEASTARS = registerWithItem("sand_seastars", () -> new SandBlock(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistrySupplier<Block> SANDWAVES = registerWithItem("sandwaves", () -> new SandBlock(14406560, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistrySupplier<Block> SAND_SLAB = registerWithItem("sand_slab", () -> new SandSlabBlock(getSlabSettings().sound(SoundType.SAND)));
    public static final RegistrySupplier<Block> SAND_PILE = registerWithoutItem("sand_pile", () -> new SandPileBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistrySupplier<Block> STRIPPED_PALM_LOG = registerLog("stripped_palm_log");
    public static final RegistrySupplier<Block> PALM_LOG = registerLog("palm_log");
    public static final RegistrySupplier<Block> STRIPPED_PALM_WOOD = registerLog("stripped_palm_wood");
    public static final RegistrySupplier<Block> PALM_WOOD = registerLog("palm_wood");
    public static final RegistrySupplier<Block> PALM_BEAM = registerLog("palm_beam");
    public static final RegistrySupplier<Block> PALM_PLANKS = registerWithItem("palm_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> PALM_FLOORBOARD = registerWithItem("palm_floorboard", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> PALM_STAIRS = registerWithItem("palm_stairs", () -> new StairBlock(PALM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(PALM_PLANKS.get())));
    public static final RegistrySupplier<Block> PALM_SLAB = registerWithItem("palm_slab", () -> new SlabBlock(getSlabSettings()));
    public static final RegistrySupplier<Block> PALM_FENCE = registerWithItem("palm_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistrySupplier<Block> PALM_FENCE_GATE = registerWithItem("palm_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistrySupplier<Block> PALM_BUTTON = registerWithItem("palm_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistrySupplier<Block> PALM_PRESSURE_PLATE = registerWithItem("palm_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistrySupplier<Block> PALM_DOOR = registerWithItem("palm_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistrySupplier<Block> PALM_TRAPDOOR = registerWithItem("palm_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> DRIED_WHEAT_BLOCK = registerWithItem("dried_wheat_block", () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistrySupplier<Block> DRIED_WHEAT_STAIRS = registerWithItem("dried_wheat_stairs", () -> new StairBlock(PALM_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(PALM_PLANKS.get()).sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> DRIED_WHEAT_SLAB = registerWithItem("dried_wheat_slab", () -> new SlabBlock(getSlabSettings().sound(SoundType.GRASS)));
    public static final RegistrySupplier<Block> LOUNGE_CHAIR = registerWithItem("lounge_chair", () -> new LoungeChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f, 3.0f).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> CHAIR = registerWithItem("chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f, 3.0f).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> TABLE = registerWithItem("table", () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> BEACH_CHAIR = registerWithItem("beach_chair", () -> new BeachChairBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> DECK_CHAIR = registerWithItem("deck_chair", () -> new BeachChairBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> TIKI_CHAIR = registerWithItem("tiki_chair", () -> new TikiChairBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> TIKI_BAR = registerWithItem("tiki_bar", () -> new TikiBarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> CABINET = registerWithItem("cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.BAMBOO), SoundEventRegistry.CABINET_OPEN.get(), SoundEventRegistry.CABINET_CLOSE.get()));
    private static final BeachpartyIdentifier PALM_SIGN_TEXTURE = new BeachpartyIdentifier("entity/signs/palm");
    public static final RegistrySupplier<TerraformSignBlock> PALM_SIGN = registerWithoutItem("palm_sign", () -> new TerraformSignBlock(PALM_SIGN_TEXTURE, BlockBehaviour.Properties.copy(Blocks.OAK_SIGN)));
    public static final RegistrySupplier<Block> PALM_WALL_SIGN = registerWithoutItem("palm_wall_sign", () -> new TerraformWallSignBlock(PALM_SIGN_TEXTURE, BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN)));
    public static final RegistrySupplier<Item> PALM_SIGN_ITEM = registerItem("palm_sign", () -> new SignItem(getSettings().stacksTo(16), PALM_SIGN.get(), PALM_WALL_SIGN.get()));
    public static final RegistrySupplier<Block> MINI_FRIDGE = registerWithItem("mini_fridge", () -> new MiniFridgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistrySupplier<Block> RADIO = registerWithItem("radio", () -> new RadioBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)));
    public static final RegistrySupplier<Block> SAND_BUCKET_BLOCK = registerWithoutItem("sand_bucket_block", () -> new SandBucketBlock(BlockBehaviour.Properties.of(Material.DIRT)));//TODO was für Material?
    public static final RegistrySupplier<Item> OVERGROWN_DISC = registerItem("overgrown_disc", () -> new RecordItem(1, SoundEventRegistry.RADIO_BEACHPARTY.get(), getSettings(), 214));
    public static final RegistrySupplier<Block> MESSAGE_IN_A_BOTTLE = registerWithoutItem("message_in_a_bottle", () -> new MessageInABottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS), Block.box(4.0f, 0.0f, 4.0f, 12.0f, 6.0f, 12.0f)));
    public static final RegistrySupplier<Item> MESSAGE_IN_A_BOTTLE_ITEM = registerItem("message_in_a_bottle", () -> new MessageInABottleItem(ObjectRegistry.MESSAGE_IN_A_BOTTLE.get(), getSettings()));
    public static final RegistrySupplier<Item> SEASHELL = registerItem("seashell", () -> new SeashellItem(getSettings()));
    public static final RegistrySupplier<Block> EMPTY_SAND_BUCKET_BLOCK = registerWithoutItem("empty_sand_bucket_block", () -> new SandBucketBlock(BlockBehaviour.Properties.of(Material.DIRT)));
    public static final RegistrySupplier<Item> SAND_BUCKET = registerItem("sand_bucket", () -> new SandBucketItem(SAND_BUCKET_BLOCK.get(), getSettings().stacksTo(1)));
    public static final RegistrySupplier<Item> EMPTY_SAND_BUCKET = registerItem("empty_sand_bucket", () -> new SandBucketItem(EMPTY_SAND_BUCKET_BLOCK.get(), getSettings()));
    public static final RegistrySupplier<Block> COCONUT_BLOCK = registerWithoutItem("coconut_block", () -> new CoconutBlock(BlockBehaviour.Properties.of(Material.BAMBOO)));
    public static final RegistrySupplier<Item> COCONUT = registerItem("coconut", () -> new CoconutItem(COCONUT_BLOCK.get(), getSettings()));
    public static final RegistrySupplier<Item> COCONUT_OPEN = registerItem("coconut_open", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Block> COCONUT_COCKTAIL = registerCocktail("coconut_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.HEAL);
    public static final RegistrySupplier<Block> SWEETBERRIES_COCKTAIL = registerCocktail("sweetberries_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.ABSORPTION);
    public static final RegistrySupplier<Block> COCOA_COCKTAIL = registerCocktail("cocoa_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.REGENERATION);
    public static final RegistrySupplier<Block> PUMPKIN_COCKTAIL = registerCocktail("pumpkin_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.FIRE_RESISTANCE);
    public static final RegistrySupplier<Block> HONEY_COCKTAIL = registerCocktail("honey_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.DIG_SPEED);
    public static final RegistrySupplier<Block> MELON_COCKTAIL = registerCocktail("melon_cocktail", () -> new CocktailBlock(getCocktailSettings()), MobEffects.LUCK);
    public static final RegistrySupplier<Item> ICECREAM_COCONUT = registerItem("icecream_coconut", () -> new IcecreamItem(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> ICECREAM_MELON = registerItem("icecream_melon", () -> new IcecreamItem(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> ICECREAM_CACTUS = registerItem("icecream_cactus", () -> new IcecreamItem(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> ICECREAM_SWEETBERRIES = registerItem("icecream_sweetberries", () -> new IcecreamItem(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> ICECREAM_CHOCOLATE = registerItem("icecream_chocolate", () -> new IcecreamItem(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> RAW_PELICAN = registerItem("raw_pelican", () -> new Item(getSettings().food(Foods.BEEF)));
    public static final RegistrySupplier<Item> COOKED_PELICAN = registerItem("cooked_pelican", () -> new Item(getSettings().food(Foods.COOKED_BEEF)));
    public static final RegistrySupplier<Item> RAW_MUSSEL_MEAT = registerItem("raw_mussel_meat", () -> new Item(getSettings().food(Foods.BEEF)));
    public static final RegistrySupplier<Item> COOKED_MUSSEL_MEAT = registerItem("cooked_mussel_meat", () -> new Item(getSettings().food(Foods.COOKED_BEEF)));
    public static final RegistrySupplier<Block> BEACH_TOWEL = registerWithItem("beach_towel", () -> new BeachTowelBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOL)));
    public static final RegistrySupplier<Item> BEACH_HAT = registerItem("beach_hat", () -> new BeachHatItem(getSettings().rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> SUNGLASSES = registerItem("sunglasses", () -> new SwimwearArmorItem(MaterialsRegistry.SUNGLASSES, EquipmentSlot.HEAD, getSettings()));
    public static final RegistrySupplier<Item> TRUNKS = registerItem("trunks", () -> new SwimwearArmorItem(MaterialsRegistry.TRUNKS, EquipmentSlot.LEGS, getSettings().rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> BIKINI = registerItem("bikini", () -> new SwimwearArmorItem(MaterialsRegistry.BIKINI, EquipmentSlot.LEGS, getSettings().rarity(Rarity.COMMON)));
    public static final RegistrySupplier<Item> CROCS = registerItem("crocs", () -> new SwimwearArmorItem(MaterialsRegistry.CROCS, EquipmentSlot.FEET, getSettings().rarity(Rarity.UNCOMMON)));
    public static final RegistrySupplier<Item> SWIM_WINGS = registerItem("swim_wings", () -> new SwimwearArmorItem(MaterialsRegistry.SWIM_WINGS, EquipmentSlot.CHEST, getSettings()));
    public static final RegistrySupplier<Item> RUBBER_RING_BLUE = registerItem("rubber_ring_blue", () -> new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_blue.png"), -0.7f));
    public static final RegistrySupplier<Item> RUBBER_RING_PINK = registerItem("rubber_ring_pink", () -> new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_pink.png"), -0.7f));
    public static final RegistrySupplier<Item> RUBBER_RING_STRIPPED = registerItem("rubber_ring_stripped", () -> new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.COMMON), new BeachpartyIdentifier("textures/entity/rubber_ring_stripped.png"), -0.7f));
    public static final RegistrySupplier<Item> RUBBER_RING_PELICAN = registerItem("rubber_ring_pelican", () -> new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/entity/rubber_ring_pelican.png"), -0.7f));
    public static final RegistrySupplier<Item> RUBBER_RING_AXOLOTL = registerItem("rubber_ring_axolotl", () -> new BetterCustomArmorModelItem(EquipmentSlot.CHEST, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/entity/rubber_ring_axolotl.png"), -0.7f));
    public static final RegistrySupplier<Item> POOL_NOODLE_BLUE = registerItem("pool_noodle_blue", () -> new SwordItem(Tiers.WOOD, 1, -1.4F, getSettings()));
    public static final RegistrySupplier<Item> POOL_NOODLE_RED = registerItem("pool_noodle_red", () -> new SwordItem(Tiers.WOOD, 1, -1.4F, getSettings()));
    public static final RegistrySupplier<Item> POOL_NOODLE_GREEN = registerItem("pool_noodle_green", () -> new SwordItem(Tiers.WOOD, 1, -1.4F, getSettings()));
    public static final RegistrySupplier<Item> POOL_NOODLE_YELLOW = registerItem("pool_noodle_yellow", () -> new SwordItem(Tiers.WOOD, 1, -1.4F, getSettings()));
    public static final RegistrySupplier<Block> PALM_TORCH = registerWithoutItem("palm_torch", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistrySupplier<Block> PALM_WALL_TORCH = registerWithoutItem("palm_wall_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD).dropsLike(PALM_TORCH.get()), ParticleTypes.FLAME));
    public static final RegistrySupplier<Item> PALM_TORCH_ITEM = registerItem("palm_torch_item", () -> new StandingAndWallBlockItem(ObjectRegistry.PALM_TORCH.get(), ObjectRegistry.PALM_WALL_TORCH.get(), getSettings()));
    public static final RegistrySupplier<Block> PALM_TALL_TORCH = registerWithItem("palm_tall_torch", () -> new TallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistrySupplier<Item> PELICAN_SPAWN_EGG = registerItem("pelican_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.PELICAN, 16710877, 16116736, getSettings()));
    public static final RegistrySupplier<Block> SANDCASTLE = registerWithoutItem("sandcastle", () -> new SandCastleBlock(BlockBehaviour.Properties.copy(Blocks.SAND))); //TODO was für Material?


    private static RegistrySupplier<Block> registerLog(String path) {
        return registerWithItem(path, () -> new RotatedPillarBlock(getLogBlockSettings()));
    }

    private static BlockBehaviour.Properties getLogBlockSettings() {
        return BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties getSlabSettings() {
        return getLogBlockSettings().explosionResistance(3.0F);
    }
    

    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties().tab(Beachparty.CREATIVE_TAB);
        consumer.accept(settings);
        return settings;
    }



    private static <T extends Block> RegistrySupplier<T> registerCocktail(String name, Supplier<T> block, MobEffect effect) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, () -> new DrinkBlockItem(toReturn.get(), getSettings(settings -> settings.food(cocktailFoodComponent(effect)))));
        return toReturn;
    }




    private static FoodProperties cocktailFoodComponent(MobEffect effect) {
        FoodProperties.Builder component = new FoodProperties.Builder().nutrition(2).saturationMod(1);
        if (effect != null) component.effect(new MobEffectInstance(effect, 45 * 20), 1.0f);
        return component.build();
    }
    
    private static BlockBehaviour.Properties getCocktailSettings() {
        return BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().instabreak();
    }


    private static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }
    

    public static void init() {
        Beachparty.LOGGER.debug("Registering Mod Block and Items for " + Beachparty.MOD_ID);
        ITEMS.register();
        BLOCKS.register();
    }

    public static void commonInit(){
                /*
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

         */

        FuelRegistry.register(300, PALM_FENCE.get(), PALM_FENCE_GATE.get(), PALM_PLANKS.get(), PALM_LOG.get(), PALM_WOOD.get(), STRIPPED_PALM_LOG.get(), STRIPPED_PALM_WOOD.get());

    }

    private static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return BLOCKS.register(path, block);
    }

    private static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(Beachparty.CREATIVE_TAB)));
        return toReturn;
    }
    private static <T extends Item> void registerBlockItem(String path, Supplier<T> itemSupplier) {
        ITEMS.register(path, itemSupplier);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }


}

