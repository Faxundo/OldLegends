package com.github.faxundo.old_legends.screen.widget;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.screen.custom.BookOfTheLegendsScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class OLBookWidget extends PressableWidget {

    private final BookOfTheLegendsScreen screen;
    public static final Identifier NORMAL_BUTTON = OldLegends.identifier("textures/gui/widget/book_button.png");
    public static final Identifier DISABLED_BUTTON = OldLegends.identifier("textures/gui/widget/book_button_disabled.png");
    public static final Identifier HIGHLIGHTED_BUTTON = OldLegends.identifier("textures/gui/widget/book_button_highlighted.png");
    public boolean disabled;

    public OLBookWidget(int i, int j, BookOfTheLegendsScreen screen, boolean disabled) {
        super(i, j, 33, 33, Text.literal(""));
        this.screen = screen;
        this.disabled = disabled;
    }

    @Override
    public void onPress() {
        if (disabled) {
            return;
        }
        screen.close();
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        Identifier TEXTURE;

        if (disabled) {
            TEXTURE = DISABLED_BUTTON;
        } else {
            if (this.isHovered()) {
                TEXTURE = HIGHLIGHTED_BUTTON;
            } else {
                TEXTURE = NORMAL_BUTTON;
            }
        }


        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, this.getX(), this.getY(), 0, 0, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());
    }
}
