package com.github.faxundo.old_legends.screen.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RuneTableScreen extends HandledScreen<RuneTableScreenHandler> {

    private static final Identifier TEXTURE = OldLegends.identifier("textures/gui/rune_table_gui.png");
    private static final Rect2i BOOK_ICON = new Rect2i(13, 16, 16, 16);
    private static final Rect2i ITEM_ICON = new Rect2i(80, 54, 16, 16);
    private static final Rect2i AMETHYST_ICON_N = new Rect2i(80, 18, 16, 16);
    private static final Rect2i AMETHYST_ICON_S = new Rect2i(117, 54, 16, 16);
    private static final Rect2i AMETHYST_ICON_E = new Rect2i(80, 91, 16, 16);
    private static final Rect2i AMETHYST_ICON_W = new Rect2i(43, 54, 16, 16);

    public RuneTableScreen(RuneTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 196;
        this.backgroundHeight = 198;
        this.titleY = 5;
        this.playerInventoryTitleY = 108;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
        if (!handler.getSlot(0).hasStack()) {
            context.drawTexture(TEXTURE, x+BOOK_ICON.getX(), y+BOOK_ICON.getY(),
                    240, 0, BOOK_ICON.getWidth(), BOOK_ICON.getHeight());
        }
        if (!handler.getSlot(1).hasStack()) {
            context.drawTexture(TEXTURE, x+ITEM_ICON.getX(), y+ITEM_ICON.getY(),
                    240, 16, ITEM_ICON.getWidth(), ITEM_ICON.getHeight());
        }
        if (!handler.getSlot(2).hasStack()) {
            context.drawTexture(TEXTURE, x+AMETHYST_ICON_N.getX(), y+AMETHYST_ICON_N.getY(),
                    240, 32, AMETHYST_ICON_N.getWidth(), AMETHYST_ICON_N.getHeight());
        }
        if (!handler.getSlot(4).hasStack()) {
            context.drawTexture(TEXTURE, x+AMETHYST_ICON_S.getX(), y+AMETHYST_ICON_S.getY(),
                    240, 32, AMETHYST_ICON_S.getWidth(), AMETHYST_ICON_S.getHeight());
        }
        if (!handler.getSlot(6).hasStack()) {
            context.drawTexture(TEXTURE, x+AMETHYST_ICON_E.getX(), y+AMETHYST_ICON_E.getY(),
                    240, 32, AMETHYST_ICON_E.getWidth(), AMETHYST_ICON_E.getHeight());
        }
        if (!handler.getSlot(8).hasStack()) {
            context.drawTexture(TEXTURE, x+AMETHYST_ICON_W.getX(), y+AMETHYST_ICON_W.getY(),
                    240, 32, AMETHYST_ICON_W.getWidth(), AMETHYST_ICON_W.getHeight());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
