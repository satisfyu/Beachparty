package satisfyu.beachparty.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.entity.CabinetBlockEntity;
import satisfyu.beachparty.entity.MiniFridgeBlockEntity;
import satisfyu.beachparty.entity.TikiBarBlockEntity;

import java.util.function.Supplier;

public class BlockEntityRegistry {

    private static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Beachparty.MOD_ID, (Registries.BLOCK_ENTITY_TYPE)).getRegistrar();


    public static final RegistrySupplier<BlockEntityType<CabinetBlockEntity>> CABINET_BLOCK_ENTITY = createBlockEntity("cabinet", () -> BlockEntityType.Builder.of(CabinetBlockEntity::new, ObjectRegistry.CABINET.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TikiBarBlockEntity>> TIKI_BAR_BLOCK_ENTITY = createBlockEntity("tiki_bar", () -> BlockEntityType.Builder.of(TikiBarBlockEntity::new, ObjectRegistry.TIKI_BAR.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<MiniFridgeBlockEntity>> MINI_FRIDGE_BLOCK_ENTITY = createBlockEntity("mini_fridge", () -> BlockEntityType.Builder.of(MiniFridgeBlockEntity::new, ObjectRegistry.MINI_FRIDGE.get()).build(null));


    private static <T extends BlockEntityType<?>> RegistrySupplier<T> createBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(new BeachpartyIdentifier(path), type);
    }

    public static void init(){
        Beachparty.LOGGER.debug("Registering Mod BlockEntities for " + Beachparty.MOD_ID);
    }

}
