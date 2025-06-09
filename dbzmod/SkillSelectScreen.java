package com.dragonballz.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SkillSelectScreen extends Screen {
    private final Player player;

    public SkillSelectScreen(Player player) {
        super(Component.literal("Select Skill"));
        this.player = player;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int yStart = 50;
        int btnWidth = 180;
        int btnHeight = 22;
        int spacing = 28;

        // Example: skill unlock buttons
        this.addRenderableWidget(Button.builder(Component.literal("Unlock Ki"), b -> unlockSkill("ki"))
                .bounds(centerX - btnWidth / 2, yStart, btnWidth, btnHeight).build());
        this.addRenderableWidget(Button.builder(Component.literal("Unlock Fly"), b -> unlockSkill("fly"))
                .bounds(centerX - btnWidth / 2, yStart + spacing, btnWidth, btnHeight).build());
    }

    private void unlockSkill(String skill) {
        // Send request to server to unlock the skill for the player
        this.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        drawCenteredString(guiGraphics, this.font, "Unlock a Skill", this.width / 2, 18, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}