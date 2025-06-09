package com.dragonballz.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class TrainingGUIScreen extends Screen {
    private final Player player;

    public TrainingGUIScreen(Player player) {
        super(Component.literal("Training Menu"));
        this.player = player;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int yStart = 50;
        int btnWidth = 180;
        int btnHeight = 22;
        int spacing = 28;

        // Example: Add stat training buttons
        this.addRenderableWidget(Button.builder(Component.literal("Train Strength"), b -> trainStat("STRENGTH"))
                .bounds(centerX - btnWidth / 2, yStart, btnWidth, btnHeight).build());
        this.addRenderableWidget(Button.builder(Component.literal("Train Ki"), b -> trainStat("KI"))
                .bounds(centerX - btnWidth / 2, yStart + spacing, btnWidth, btnHeight).build());
        // ...add more as needed
    }

    private void trainStat(String stat) {
        // Call server-side handler to perform stat training for player
        // Could trigger an animation or effect too
        this.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        drawCenteredString(guiGraphics, this.font, "Training Menu", this.width / 2, 18, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}