package net.satisfyu.beachparty.client.gui;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.client.gui.handler.TikiBarGuiHandler;
import net.satisfyu.beachparty.client.screen.recipe.custom.TikiBarRecipeBook;

public class TikiBarGui extends AbstractRecipeBookGUIScreen<TikiBarGuiHandler> {

    public TikiBarGui(TikiBarGuiHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, new TikiBarRecipeBook(), new BeachpartyIdentifier("textures/gui/tiki_bar_gui.png"));
    }

    protected void renderProgressArrow(MatrixStack matrices) {
        final int progressX = this.handler.getShakeXProgress();
        this.drawTexture(matrices, x + 94, y + 45, 177, 26, progressX, 10);
        final int progressY = this.handler.getShakeYProgress();
        this.drawTexture(matrices, x + 96, y + 22 + 20 - progressY, 179, 2 + 20 - progressY, 15, progressY);
    }
}

