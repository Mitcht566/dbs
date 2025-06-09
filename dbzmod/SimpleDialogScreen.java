package com.dragonballz.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SimpleDialogScreen extends Screen {
    private final Component dialogText;
    private final Runnable onCloseAction;

    public SimpleDialogScreen(Component dialogText, Runnable onClose) {
        super(dialogText);
        this.dialogText = dialogText;
        this.onCloseAction = onClose;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int yStart = this.height / 2 + 30;
        int btnWidth = 100;
        this.addRenderableWidget(Button.builder(Component.literal("OK"), b -> {
            this.onClose();
        }).bounds(centerX - btnWidth / 2, yStart, btnWidth, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        drawCenteredString(guiGraphics, this.font, dialogText.getString(), this.width / 2, this.height / 2, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        super.onClose();
        if (onCloseAction != null) onCloseAction.run();
    }
}