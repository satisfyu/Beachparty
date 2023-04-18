package net.satisfyu.beachparty.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.beachparty.Beachparty;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.block.entity.RadioBlockEntity;
import net.satisfyu.beachparty.entity.CoconutEntity;
import net.satisfyu.beachparty.entity.TikiBarBlockEntity;
import net.satisfyu.beachparty.entity.chair.ChairEntity;


import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {

    private static final Map<Identifier, BlockEntityType<?>> BLOCK_ENTITY_TYPES = new HashMap<>();
    private static final Map<Identifier, EntityType<?>> ENTITY_TYPES = new HashMap<>();


    public static final EntityType<ChairEntity> CHAIR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Beachparty.MOD_ID, "chair"),
            FabricEntityTypeBuilder.<ChairEntity>create(SpawnGroup.MISC, ChairEntity::new).dimensions(EntityDimensions.fixed(0.001F, 0.001F)).build()
    );

    //public static final BlockEntityType<RadioBlockEntity> RADIO_BLOCK_ENTITY = create("radio", FabricBlockEntityTypeBuilder.create(RadioBlockEntity::new, ObjectRegistry.RADIO).build());
    public static final BlockEntityType<TikiBarBlockEntity> TIKI_BAR_BLOCK_ENTITY = create("tiki_bar", FabricBlockEntityTypeBuilder.create(TikiBarBlockEntity::new, ObjectRegistry.TIKI_BAR).build());
    public static final BlockEntityType<RadioBlockEntity> RADIO_BLOCK_ENTITY = create("radio", FabricBlockEntityTypeBuilder.create(RadioBlockEntity::new, ObjectRegistry.RADIO).build());

    public static final EntityType<CoconutEntity> COCONUT = create("coconut", FabricEntityTypeBuilder.<CoconutEntity>create(SpawnGroup.MISC, CoconutEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    private static <T extends BlockEntityType<?>> T create(final String path, final T type) {
        BLOCK_ENTITY_TYPES.put(new BeachpartyIdentifier(path), type);
        return type;
    }

    private static <T extends EntityType<?>> T create(final String path, final T type) {
        ENTITY_TYPES.put(new BeachpartyIdentifier(path), type);
        return type;
    }

    public static void init() {
        for (Map.Entry<Identifier, BlockEntityType<?>> entry : BLOCK_ENTITY_TYPES.entrySet()) {
            Registry.register(Registry.BLOCK_ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Identifier, EntityType<?>> entry : ENTITY_TYPES.entrySet()) {
            Registry.register(Registry.ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
    }
}
