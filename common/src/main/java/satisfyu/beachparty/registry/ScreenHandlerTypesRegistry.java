package satisfyu.beachparty.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import satisfyu.beachparty.Beachparty;
import satisfyu.beachparty.client.gui.handler.MiniFridgeGuiHandler;
import satisfyu.beachparty.client.gui.handler.TikiBarGuiHandler;

import java.util.function.Supplier;


public class ScreenHandlerTypesRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Beachparty.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<TikiBarGuiHandler>> TIKI_BAR_GUI_HANDLER = create("tiki_bar_gui_handler", () -> new MenuType<>(TikiBarGuiHandler::new));
    public static final RegistrySupplier<MenuType<MiniFridgeGuiHandler>> MINI_FRIDGE_GUI_HANDLER = create("mini_fridge_gui_handler", () -> new MenuType<>(MiniFridgeGuiHandler::new));



    public static void init() {
        MENU_TYPES.register();
    }

    private static <T extends MenuType<?>> RegistrySupplier<T> create(String name, Supplier<T> type) {
        return MENU_TYPES.register(name, type);
    }
}
