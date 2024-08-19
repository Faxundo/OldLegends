package com.github.faxundo.old_legends.screen.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.screen.widget.OLBookWidget;
import com.github.faxundo.old_legends.screen.widget.OLItemWidget;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
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
        int q, r, x, y;
        ItemStack itemStack = this.handler.getPlayer().getMainHandStack();


        boolean emeraldMourning = itemStack.contains(OLDataComponent.EMERALD_MOURNING_PAGE);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                x = ((width - this.backgroundWidth) / 2) + 36 + (j * xSpacing);
                y = ((height - this.backgroundHeight) / 2) + 25 + (i * ySpacing);
                OLBookWidget olBookWidget = new OLBookWidget(x, y, this, false);


                q = ((width - this.backgroundWidth) / 2) + 40 + (j * xSpacing);
                r = ((height - this.backgroundHeight) / 2) + 29 + (i * ySpacing);

                Identifier texture = OldLegends.identifier("textures/gui/icon/unknown.png");
                String nbtItem = "";

                if (emeraldMourning) {
                    texture = OldLegends.identifier("textures/gui/icon/emerald_sword.png");
                }

//                switch (i * cols + j) {
//                    case 0:
//                        nbtItem = "item.old_legends.emerald_mourning_page";
//                        if (nbt.contains(OldLegends.MOD_ID + "." + nbtItem)) {
//                            texture = OldLegends.identifier("textures/gui/icon/emerald_sword.png");
//                        }
//                        break;
//                    case 1:
//                        nbtItem = "item.old_legends.swallows_storm_page";
//                        if (nbt.contains(OldLegends.MOD_ID + "." + nbtItem)) {
//                            texture = OldLegends.identifier("textures/gui/icon/swallows_storm.png");
//                        }
//                        break;
//                    case 2:
//                        nbtItem = "item.old_legends.flutter_echo_page";
//                        if (nbt.contains(OldLegends.MOD_ID + "." + nbtItem)) {
//                            texture = OldLegends.identifier("textures/gui/icon/flutter_echo.png");
//                        }
//                        break;
//                    case 3:
//                        nbtItem = "item.old_legends.reliquary_page";
//                        if (nbt.contains(OldLegends.MOD_ID + "." + nbtItem)) {
//                            texture = OldLegends.identifier("textures/gui/icon/reliquary.png");
//                        }
//                        break;
//                    case 4, 5:
//                        texture = OldLegends.identifier("textures/gui/icon/none.png");
//                        olBookWidget.setDisabled(true);
//                        break;
//                }
                if (!texture.equals(OldLegends.identifier("textures/gui/icon/unknown.png"))) {
                    olBookWidget.setTooltip(Tooltip.of(itemName(nbtItem)));

                } else {
                    olBookWidget.setTooltip(Tooltip.of(Text.of("???")));
                }
                addDrawableChild(olBookWidget);

                OLItemWidget olItemWidget = new OLItemWidget(q, r, texture);
                addDrawableChild(olItemWidget);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                x = ((width - this.backgroundWidth) / 2) + 36 + (j * xSpacing);
                y = ((height - this.backgroundHeight) / 2) + 100 + (i * ySpacing);
                OLBookWidget olBookWidget = new OLBookWidget(x, y, this, true);
                addDrawableChild(olBookWidget);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                x = ((width - this.backgroundWidth) / 2) + 181 + (j * xSpacing);
                y = ((height - this.backgroundHeight) / 2) + 25 + (i * ySpacing);
                OLBookWidget olBookWidget = new OLBookWidget(x, y, this, true);
                addDrawableChild(olBookWidget);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                x = ((width - this.backgroundWidth) / 2) + 181 + (j * xSpacing);
                y = ((height - this.backgroundHeight) / 2) + 100 + (i * ySpacing);
                OLBookWidget olBookWidget = new OLBookWidget(x, y, this, true);
                addDrawableChild(olBookWidget);
            }
        }
    }

    public Text itemName(String name) {
        StringBuilder finalText = new StringBuilder();
        String[] itemNamePage = name.split("\\.");
        String[] itemName = itemNamePage[itemNamePage.length - 1].split("_");
        for (String word : itemName) {
            if (!word.equals("page") && !word.isEmpty()) {
                String word2 = word.substring(0, 1).toUpperCase() + word.substring(1);
                finalText.append(" ").append(word2);
            }
        }
        finalText = new StringBuilder(finalText.toString().replaceFirst("\\s", ""));
        return Text.literal(finalText.toString());
    }
}
