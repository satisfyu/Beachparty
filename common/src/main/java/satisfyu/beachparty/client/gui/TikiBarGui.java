package satisfyu.beachparty.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.client.gui.handler.TikiBarGuiHandler;
import satisfyu.beachparty.client.recipebook.TikiBarRecipeBook;

public class TikiBarGui extends AbstractRecipeBookGUIScreen<TikiBarGuiHandler> {

    public TikiBarGui(TikiBarGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new TikiBarRecipeBook(), new BeachpartyIdentifier("textures/gui/tiki_bar_gui.png"));
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }


    protected void renderProgressArrow(PoseStack matrices) {
        final int progressX = this.menu.getShakeXProgress();
        this.blit(matrices, leftPos + 94, topPos + 45, 177, 26, progressX, 10);
        final int progressY = this.menu.getShakeYProgress();
        this.blit(matrices, leftPos + 96, topPos + 22 + 20 - progressY, 179, 2 + 20 - progressY, 15, progressY);
    }
}

