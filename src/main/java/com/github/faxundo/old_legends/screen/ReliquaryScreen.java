package com.github.faxundo.old_legends.screen;

import com.github.faxundo.old_legends.OldLegends;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ReliquaryScreen extends HandledScreen<ReliquaryScreenHandler> {

    private static final Identifier TEXTURE = OldLegends.identifier("textures/gui/reliquary_gui.png");
    public ButtonWidget lockButton;

    public ReliquaryScreen(ReliquaryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }


    @Override
    protected void init() {
        super.init();
        lockButton = ButtonWidget.builder(Text.translatable("block.old_legends.button_name"), button -> {
            if(this.client != null) {
                this.client.interactionManager.clickButton(this.handler.syncId,0);
                this.close();
            }
                })
                .dimensions((width / 2) - 14, (height / 2) - 28, 30, 15)
                .tooltip(Tooltip.of(Text.translatable("tooltip.old_legends.button")))
                .build();
        addDrawableChild(lockButton);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
