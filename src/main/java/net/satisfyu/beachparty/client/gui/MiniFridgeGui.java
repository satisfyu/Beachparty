package net.satisfyu.beachparty.client.gui;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.satisfyu.beachparty.BeachpartyIdentifier;
import net.satisfyu.beachparty.client.gui.handler.MiniFridgeGuiHandler;
import net.satisfyu.beachparty.client.screen.recipe.custom.MiniFridgeRecipeBook;

public class MiniFridgeGui extends AbstractRecipeBookGUIScreen<MiniFridgeGuiHandler> {

    private static final Identifier BG = new BeachpartyIdentifier("textures/gui/freezer.png");

    public MiniFridgeGui(MiniFridgeGuiHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, new MiniFridgeRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleX += 2;
        this.titleY += -3;
        super.init();
    }

    protected void renderProgressArrow(MatrixStack matrices) {
        final int progressX = this.handler.getShakeXProgress();
        this.drawTexture(matrices, x + 94, y + 45, 177, 26, progressX, 10);
        final int progressY = this.handler.getShakeYProgress();
        this.drawTexture(matrices, x + 96, y + 22 + 20 - progressY, 179, 2 + 20 - progressY, 15, progressY);
    }
}

