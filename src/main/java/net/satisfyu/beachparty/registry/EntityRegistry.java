package net.satisfyu.beachparty.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.beachparty.Beachparty;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.entity.TikiBarBlockEntity;
import net.satisfyu.beachparty.entity.chair.ChairEntity;


import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {

    private static final Map<Identifier, BlockEntityType<?>> BLOCK_ENTITY_TYPES = new HashMap<>();


    public static final EntityType<ChairEntity> CHAIR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Beachparty.MOD_ID, "chair"),
            FabricEntityTypeBuilder.<ChairEntity>create(SpawnGroup.MISC, ChairEntity::new).dimensions(EntityDimensions.fixed(0.001F, 0.001F)).build()
    );

    //public static final BlockEntityType<RadioBlockEntity> RADIO_BLOCK_ENTITY = create("radio", FabricBlockEntityTypeBuilder.create(RadioBlockEntity::new, ObjectRegistry.RADIO).build());
    public static final BlockEntityType<TikiBarBlockEntity> TIKI_BAR_BLOCK_ENTITY = create("tiki_bar", FabricBlockEntityTypeBuilder.create(TikiBarBlockEntity::new, ObjectRegistry.TIKI_BAR).build());


    private static <T extends BlockEntityType<?>> T create(final String path, final T type) {
        BLOCK_ENTITY_TYPES.put(new BeachpartyIdentifier(path), type);
        return type;
    }

    public static void init() {
        for (Map.Entry<Identifier, BlockEntityType<?>> entry : BLOCK_ENTITY_TYPES.entrySet()) {
            Registry.register(Registry.BLOCK_ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
    }
}
