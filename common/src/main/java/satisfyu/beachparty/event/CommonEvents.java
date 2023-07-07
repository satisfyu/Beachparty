package satisfyu.beachparty.event;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import satisfyu.beachparty.util.BeachpartyLoottableInjector;

public class CommonEvents {

    public static void init()
    {
        LootEvent.MODIFY_LOOT_TABLE.register(CommonEvents::onModifyLootTable);
}

    public static void onModifyLootTable(BuiltInLootTables tables, ResourceLocation id, LootEvent.LootTableModificationContext context, boolean builtin)
    {
        BeachpartyLoottableInjector.InjectLoot(id, context);
    }
}
