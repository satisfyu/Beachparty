package satisfyu.beachparty.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.client.gui.handler.MiniFridgeGuiHandler;
import satisfyu.beachparty.client.gui.handler.TikiBarGuiHandler;


public class ScreenHandlerTypesRegistry {

    public static final MenuType<TikiBarGuiHandler> TIKI_BAR_GUI_HANDLER = new MenuType<>(TikiBarGuiHandler::new);
    public static final MenuType<MiniFridgeGuiHandler> MINI_FRIDGE_GUI_HANDLER = new MenuType<>(MiniFridgeGuiHandler::new);



    public static void init() {
        Registry.register(Registry.MENU, new BeachpartyIdentifier("tiki_bar_gui_handler"), TIKI_BAR_GUI_HANDLER);
        Registry.register(Registry.MENU, new BeachpartyIdentifier("mini_fridge_gui_handler"), MINI_FRIDGE_GUI_HANDLER);

    }
}
