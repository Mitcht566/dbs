package com.dragonballz.client.gui;

import com.dragonballz.race.RaceFeatures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import com.dragonballz.client.anim.PlayerAnimationIntegration;

import java.util.List;

public class TransformationMenuScreen extends Screen {
    private Player player;
    private List<String> unlockedTransformations;

    public TransformationMenuScreen() {
        super(Component.literal("Transformations"));
    }

    @Override
    protected void init() {
        player = Minecraft.getInstance().player;
        if (player == null) return;
        unlockedTransformations = RaceFeatures.getUnlockedTransformations(player);

        int centerX = this.width / 2;
        int yStart = 50;
        int btnWidth = 180;
        int btnHeight = 22;
        int spacing = 28;

        for (int i = 0; i < unlockedTransformations.size(); i++) {
            String transformation = unlockedTransformations.get(i);
            this.addRenderableWidget(Button.builder(Component.literal(transformation), b -> {
                RaceFeatures.requestTransformation(player, transformation);
                PlayerAnimationIntegration.playTransformAnimation(player);
            }).bounds(centerX - btnWidth / 2, yStart + i * spacing, btnWidth, btnHeight).build());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        drawCenteredString(guiGraphics, this.font, "Select Transformation", this.width / 2, 18, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}