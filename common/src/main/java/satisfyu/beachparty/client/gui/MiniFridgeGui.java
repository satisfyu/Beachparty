package satisfyu.beachparty.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.beachparty.BeachpartyIdentifier;
import satisfyu.beachparty.client.gui.handler.MiniFridgeGuiHandler;
import satisfyu.beachparty.client.recipebook.MiniFridgeRecipeBook;

public class MiniFridgeGui extends AbstractRecipeBookGUIScreen<MiniFridgeGuiHandler> {

    private static final ResourceLocation BG = new BeachpartyIdentifier("textures/gui/freezer.png");

    public MiniFridgeGui(MiniFridgeGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new MiniFridgeRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        final int progressX = this.menu.getShakeXProgress();
        guiGraphics.fill( leftPos + 94, topPos + 45, 177, 26, progressX, 10);
        final int progressY = this.menu.getShakeYProgress();
        guiGraphics.fill( leftPos + 96, topPos + 22 + 20 - progressY, 179, 2 + 20 - progressY, 15, progressY);
    }
}

