package satisfyu.beachparty.forge;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.forge.registry.VillagersForge;
import satisfyu.beachparty.registry.ObjectRegistry;

import java.util.List;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = Beachparty.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event){
            if(event.getType().equals(VillagersForge.BARKEEPER.get())){
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                addBarkeeperTrades(trades);
            }
            else if(event.getType().equals(VillagersForge.BEACH_GUY.get())){
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                addBeachGuyTrades(trades);
            }
        }
    }

    private static void addBarkeeperTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades){
        //level 1
        List<VillagerTrades.ItemListing> level1 = trades.get(1);
        level1.add(new VillagerTrades.EmeraldForItems(Blocks.ICE, 1, 2, 5));

        //level 2
        List<VillagerTrades.ItemListing> level2 = trades.get(2);

        //level 3
        List<VillagerTrades.ItemListing> level3 = trades.get(3);

        //level 4
        List<VillagerTrades.ItemListing> level4 = trades.get(4);

        //level 5
        List<VillagerTrades.ItemListing> level5 = trades.get(5);
    }

    private static void addBeachGuyTrades(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades){
        //level 1
        List<VillagerTrades.ItemListing> level1 = trades.get(1);
        level1.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.SUNGLASSES.get(), 18, 1, 5));
        level1.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.BIKINI.get(), 12, 1, 5));
        level1.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.TRUNKS.get(), 8, 1, 5));
        level1.add(new VillagerTrades.EmeraldForItems(ObjectRegistry.SWIM_WINGS.get(), 5, 1, 5));

        //level 2
        List<VillagerTrades.ItemListing> level2 = trades.get(2);

        //level 3
        List<VillagerTrades.ItemListing> level3 = trades.get(3);

        //level 4
        List<VillagerTrades.ItemListing> level4 = trades.get(4);

        //level 5
        List<VillagerTrades.ItemListing> level5 = trades.get(5);
    }
}
