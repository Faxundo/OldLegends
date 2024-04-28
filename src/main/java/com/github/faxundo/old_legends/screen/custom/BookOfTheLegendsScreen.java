package com.github.faxundo.old_legends.screen.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.screen.widget.OLBookWidget;
import com.github.faxundo.old_legends.screen.widget.OLItemWidget;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BookOfTheLegendsScreen extends HandledScreen<BookOfTheLegendsScreenHandler> {

    private static final Identifier TEXTURE = OldLegends.identifier("textures/gui/book_of_the_legends_open.png");

    public BookOfTheLegendsScreen(BookOfTheLegendsScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 320;
        this.backgroundHeight = 208;
        this.titleY = 1000;
        this.playerInventoryTitleY = 1000;
    }


    @Override
    protected void init() {
        super.init();
        addBookSlots();
        addItems();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - this.backgroundWidth) / 2;
        int y = (height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    public void addBookSlots() {
        int rows = 2;
        int cols = 3;
        int xSpacing = 33 + 2;
        int ySpacing = 33 + 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = ((width - this.backgroundWidth) / 2) + 36 + (j * xSpacing);
                int y = ((height - this.backgroundHeight) / 2) + 25 + (i * ySpacing);
                OLBookWidget OLBookWidget = new OLBookWidget(x, y, this);
                addDrawableChild(OLBookWidget);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = ((width - this.backgroundWidth) / 2) + 36 + (j * xSpacing);
                int y = ((height - this.backgroundHeight) / 2) + 100 + (i * ySpacing);
                OLBookWidget OLBookWidget = new OLBookWidget(x, y, this);
                addDrawableChild(OLBookWidget);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = ((width - this.backgroundWidth) / 2) + 181 + (j * xSpacing);
                int y = ((height - this.backgroundHeight) / 2) + 25 + (i * ySpacing);
                OLBookWidget OLBookWidget = new OLBookWidget(x, y, this);
                addDrawableChild(OLBookWidget);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = ((width - this.backgroundWidth) / 2) + 181 + (j * xSpacing);
                int y = ((height - this.backgroundHeight) / 2) + 100 + (i * ySpacing);
                OLBookWidget olbookwidget = new OLBookWidget(x, y, this);
                addDrawableChild(olbookwidget);
            }
        }
    }

    public void addItems() {
        ItemStack stack = this.handler.getPlayer().getMainHandStack();
        NbtCompound nbt = stack.getOrCreateNbt();

        //Work in process
//        for (int i = 0; i <= nbt.getSize(); i++) {
//            String[] list = stack.getTranslationKey().split("\\.");
//            String nameItem = list[2];
//            System.out.println(list);
//            if (nbt.contains(OldLegends.MOD_ID + "." + nameItem)) {
//                System.out.println(nameItem);
//                int x = ((width - this.backgroundWidth) / 2) + 40;
//                int y = ((height - this.backgroundHeight) / 2) + 29;
//                OLItemWidget olItemWidget = new OLItemWidget(x, y, OldLegends.identifier("textures/gui/icon/" + nameItem + "_icon.png"));
//                addDrawableChild(olItemWidget);
//            }
//        }

        if (nbt.contains(OldLegends.MOD_ID + "." + "item.old_legends.emerald_mourning_page")) {
            int x = ((width - this.backgroundWidth) / 2) + 40;
            int y = ((height - this.backgroundHeight) / 2) + 29;
            OLItemWidget olItemWidget = new OLItemWidget(x, y, OldLegends.identifier("textures/gui/icon/emerald_sword_icon.png"));
            addDrawableChild(olItemWidget);
        }
        if (nbt.contains(OldLegends.MOD_ID + "." + "item.old_legends.swallows_storm_page")) {
            int x = ((width - this.backgroundWidth) / 2) + 75;
            int y = ((height - this.backgroundHeight) / 2) + 29;
            OLItemWidget olItemWidget = new OLItemWidget(x, y, OldLegends.identifier("textures/gui/icon/swallows_storm_icon.png"));
            addDrawableChild(olItemWidget);
        }
        if (nbt.contains(OldLegends.MOD_ID + "." + "item.old_legends.flutter_echo_page")) {
            int x = ((width - this.backgroundWidth) / 2) + 110;
            int y = ((height - this.backgroundHeight) / 2) + 29;
            OLItemWidget olItemWidget = new OLItemWidget(x, y, OldLegends.identifier("textures/gui/icon/flutter_echo_icon.png"));
            addDrawableChild(olItemWidget);
        }
        if (nbt.contains(OldLegends.MOD_ID + "." + "item.old_legends.reliquary_page")) {
            int x = ((width - this.backgroundWidth) / 2) + 40;
            int y = ((height - this.backgroundHeight) / 2) + 65;
            OLItemWidget olItemWidget = new OLItemWidget(x, y, OldLegends.identifier("textures/gui/icon/reliquary_icon.png"));
            addDrawableChild(olItemWidget);
        }

    }


}
