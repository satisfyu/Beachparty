package satisfyu.beachparty.forge.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.registry.ObjectRegistry;

import java.lang.reflect.InvocationTargetException;

public class VillagersForge {
    /*

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Beachparty.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Beachparty.MOD_ID);

    public static final DeferredRegister<VillagerType> VILLAGER_TYPES = DeferredRegister.create(Registry.VILLAGER_TYPE_REGISTRY, Beachparty.MOD_ID);


    public static final RegistryObject<PoiType> BEACH_GUY_POI = POI_TYPES.register("beach_guy_poi", () ->
            new PoiType(ImmutableSet.copyOf(ObjectRegistry.LOUNGE_CHAIR.get().getStateDefinition().getPossibleStates()), 1, 12));
    public static final RegistryObject<VillagerProfession> BEACH_GUY = VILLAGER_PROFESSIONS.register("beach_guy", () ->
            new VillagerProfession("beach_guy", x -> x.get() == BEACH_GUY_POI.get(), x -> x.get() == BEACH_GUY_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_FARMER));


    public static final RegistryObject<PoiType> BARKEEPER_POI = POI_TYPES.register("barkeeper_poi", () ->
            new PoiType(ImmutableSet.copyOf(ObjectRegistry.TIKI_BAR.get().getStateDefinition().getPossibleStates()), 1, 12));
    public static final RegistryObject<VillagerProfession> BARKEEPER = VILLAGER_PROFESSIONS.register("barkeeper", () ->
            new VillagerProfession("beach_guy", x -> x.get() == BARKEEPER_POI.get(), x -> x.get() == BARKEEPER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_FARMER));


    public static final RegistryObject<VillagerType> BEACH = VILLAGER_TYPES.register("beachparty", () -> new VillagerType("beachparty"));
    
    public static void addToBiomes() {
        VillagerType.BY_BIOME.put(ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("beach")), BEACH.get());
    }

     */


    public static void registerPOIs(){
        /*
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, BARKEEPER_POI.get(), BEACH_GUY_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }

         */
    }

    public static void register(IEventBus eventBus) {
        /*
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);

         */
    }
}