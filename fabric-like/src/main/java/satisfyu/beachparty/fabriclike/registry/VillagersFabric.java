package satisfyu.beachparty.fabriclike.registry;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.block.Blocks;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.registry.ObjectRegistry;

public class VillagersFabric {
    private static final BeachpartyIdentifier BEACH_GUY_POI_IDENTIFIER = new BeachpartyIdentifier("beach_guy_poi");
    public static final PoiType BEACH_GUY_POI = PointOfInterestHelper.register(BEACH_GUY_POI_IDENTIFIER, 1, 12, ObjectRegistry.LOUNGE_CHAIR.get());
    public static final VillagerProfession BEACH_GUY = Registry.register(Registry.VILLAGER_PROFESSION, new ResourceLocation("beachparty", "beach_guy"), VillagerProfessionBuilder.create().id(new ResourceLocation("beachparty", "beach_guy")).workstation(ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, BEACH_GUY_POI_IDENTIFIER)).build());

    private static final BeachpartyIdentifier BARKEEPER_POI_IDENTIFIER = new BeachpartyIdentifier("barkeeper_poi");
    public static final PoiType BARKEEPER_POI = PointOfInterestHelper.register(BARKEEPER_POI_IDENTIFIER, 1, 12, ObjectRegistry.TIKI_BAR.get());
    public static final VillagerProfession BARKEEPER = Registry.register(Registry.VILLAGER_PROFESSION, new ResourceLocation("beachparty", "barkeeper"), VillagerProfessionBuilder.create().id(new ResourceLocation("beachparty", "barkeeper")).workstation(ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, BARKEEPER_POI_IDENTIFIER)).build());

    public static final VillagerType BEACH = Registry.register(Registry.VILLAGER_TYPE, new ResourceLocation("beachparty", "beachparty"), new VillagerType("beachparty"));
    
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(BEACH_GUY, 1, factories -> {
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.SUNGLASSES.get(), 1, 18, 5));
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.BIKINI.get(), 1, 12, 5));
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.TRUNKS.get(), 1, 8, 5));
            factories.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.SWIM_WINGS.get(), 1, 5, 5));
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
}