package satisfyu.beachparty.entity.villager;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.registry.ObjectRegistry;

public class Villagers {
    private static final BeachpartyIdentifier BEACH_GUY_POI_IDENTIFIER = new BeachpartyIdentifier("beach_guy_poi");
    public static final PoiType BEACH_GUY_POI = PointOfInterestHelper.register(BEACH_GUY_POI_IDENTIFIER, 1, 12, ObjectRegistry.LOUNGE_CHAIR);
    public static final VillagerProfession BEACH_GUY = Registry.register(Registry.VILLAGER_PROFESSION, new ResourceLocation("beachparty", "beach_guy"), VillagerProfessionBuilder.create().id(new ResourceLocation("beachparty", "beach_guy")).workstation(ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, BEACH_GUY_POI_IDENTIFIER)).build());

    private static final BeachpartyIdentifier BARKEEPER_POI_IDENTIFIER = new BeachpartyIdentifier("barkeeper_poi");
    public static final PoiType BARKEEPER_POI = PointOfInterestHelper.register(BARKEEPER_POI_IDENTIFIER, 1, 12, ObjectRegistry.TIKI_BAR);
    public static final VillagerProfession BARKEEPER = Registry.register(Registry.VILLAGER_PROFESSION, new ResourceLocation("beachparty", "barkeeper"), VillagerProfessionBuilder.create().id(new ResourceLocation("beachparty", "barkeeper")).workstation(ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, BARKEEPER_POI_IDENTIFIER)).build());

    public static final VillagerType BEACH = Registry.register(Registry.VILLAGER_TYPE, new ResourceLocation("beachparty", "beachparty"), new VillagerType("beachparty"));
    
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(BEACH_GUY, 1, factories -> {
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.SUNGLASSES, 18, 1, 5));
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.BIKINI, 12, 1, 5));
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.TRUNKS, 8, 1, 5));
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.SWIM_WINGS, 5, 1, 5));
        });

            TradeOfferHelper.registerVillagerOffers(BARKEEPER, 1, factories -> {
            factories.add(new VillagerTrades.EmeraldForItems(Blocks.ICE, 2, 4, 5));

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 2, factories -> {

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 3, factories -> {

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 4, factories -> {

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 5, factories -> {

        });

        VillagerType.BY_BIOME.put(ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("beach")), BEACH);
    }

    static class BuyForOneEmeraldFactory implements VillagerTrades.ItemListing {
        private final Item buy;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public BuyForOneEmeraldFactory(ItemLike item, int price, int maxUses, int experience) {
            this.buy = item.asItem();
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = 0.05F;
        }

        @Override
        public MerchantOffer getOffer(Entity entity, RandomSource random) {
            ItemStack itemStack = new ItemStack(this.buy, this.price);
            return new MerchantOffer(itemStack, new ItemStack(Items.EMERALD), this.maxUses, this.experience, this.multiplier);
        }
    }

    static class SellItemFactory implements VillagerTrades.ItemListing {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactory(Block block, int price, int count, int maxUses, int experience) {
            this(new ItemStack(block), price, count, maxUses, experience);
        }

        public SellItemFactory(Block item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactory(Item item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactory(Item item, int price, int count, int maxUses, int experience) {
            this(new ItemStack(item), price, count, maxUses, experience);
        }

        public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.05F);
        }

        public SellItemFactory(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = stack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public MerchantOffer getOffer(Entity entity, RandomSource random) {
            return new MerchantOffer(
                    new ItemStack(Items.EMERALD, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier
            );
        }
    }
}