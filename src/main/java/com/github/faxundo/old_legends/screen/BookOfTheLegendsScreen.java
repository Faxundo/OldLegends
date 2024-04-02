package com.github.faxundo.old_legends.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class BookOfTheLegendsScreen extends HandledScreen<BookOfTheLegendsScreenHandler> {

    private static final int WIDTH = 176;
    private static final int HEIGHT = 166;

    public BookOfTheLegendsScreen(BookOfTheLegendsScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = HEIGHT;
        this.backgroundWidth = WIDTH;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

    }
}
