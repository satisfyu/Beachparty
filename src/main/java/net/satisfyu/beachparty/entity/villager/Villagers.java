package net.satisfyu.beachparty.entity.villager;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.poi.PointOfInterestType;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.registry.ObjectRegistry;

public class Villagers {
    private static final BeachpartyIdentifier BEACH_GUY_POI_IDENTIFIER = new BeachpartyIdentifier("beach_guy_poi");
    public static final PointOfInterestType BEACH_GUY_POI = PointOfInterestHelper.register(BEACH_GUY_POI_IDENTIFIER, 1, 12, ObjectRegistry.LOUNGE_CHAIR);
    public static final VillagerProfession BEACH_GUY = Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("beachparty", "beach_guy"), VillagerProfessionBuilder.create().id(new Identifier("beachparty", "beach_guy")).workstation(RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, BEACH_GUY_POI_IDENTIFIER)).build());

    private static final BeachpartyIdentifier BARKEEPER_POI_IDENTIFIER = new BeachpartyIdentifier("barkeeper_poi");
    public static final PointOfInterestType BARKEEPER_POI = PointOfInterestHelper.register(BARKEEPER_POI_IDENTIFIER, 1, 12, ObjectRegistry.TIKI_BAR);
    public static final VillagerProfession BARKEEPER = Registry.register(Registry.VILLAGER_PROFESSION, new Identifier("beachparty", "barkeeper"), VillagerProfessionBuilder.create().id(new Identifier("beachparty", "barkeeper")).workstation(RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, BARKEEPER_POI_IDENTIFIER)).build());

    public static final VillagerType BEACH = Registry.register(Registry.VILLAGER_TYPE, new Identifier("beachparty", "beachparty"), new VillagerType("beachparty"));
    
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(BEACH_GUY, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(ObjectRegistry.SUNGLASSES, 18, 1, 5));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(ObjectRegistry.BIKINI, 12, 1, 5));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(ObjectRegistry.TRUNKS, 8, 1, 5));
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(ObjectRegistry.SWIM_WINGS, 5, 1, 5));
        });

            TradeOfferHelper.registerVillagerOffers(BARKEEPER, 1, factories -> {
            factories.add(new TradeOffers.BuyForOneEmeraldFactory(Blocks.ICE, 2, 4, 5));

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 2, factories -> {

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 3, factories -> {

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 4, factories -> {

        });
        TradeOfferHelper.registerVillagerOffers(BARKEEPER, 5, factories -> {

        });

        VillagerType.BIOME_TO_TYPE.put(RegistryKey.of(Registry.BIOME_KEY, new Identifier("beach")), BEACH);
    }

    static class BuyForOneEmeraldFactory implements TradeOffers.Factory {
        private final Item buy;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public BuyForOneEmeraldFactory(ItemConvertible item, int price, int maxUses, int experience) {
            this.buy = item.asItem();
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = 0.05F;
        }

        @Override
        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = new ItemStack(this.buy, this.price);
            return new TradeOffer(itemStack, new ItemStack(Items.EMERALD), this.maxUses, this.experience, this.multiplier);
        }
    }

    static class SellItemFactory implements TradeOffers.Factory {
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
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(
                    new ItemStack(Items.EMERALD, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier
            );
        }
    }
}