package net.satisfyu.beachparty.registry;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.client.gui.handler.TikiBarGuiHandler;


public class ScreenHandlerTypesRegistry {

    public static final ScreenHandlerType<TikiBarGuiHandler> TIKI_BAR_GUI_HANDLER = new ScreenHandlerType<>(TikiBarGuiHandler::new);



    public static void init() {
        Registry.register(Registry.SCREEN_HANDLER, new BeachpartyIdentifier("tiki_bar_gui_handler"), TIKI_BAR_GUI_HANDLER);


    }
}
