package com.dragonball.planets.faction;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics; // Import GuiGraphics

public class ConquestMapScreen extends Screen {
    public ConquestMapScreen() {
        super(Component.literal("Conquest Map"));
    }

    @Override
    protected void init() {
        // Initialize dragging, zooming, and planet indicators
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) { // Changed signature to include GuiGraphics
        this.renderBackground(guiGraphics); // Updated rendering call, renderBackground no longer takes minecraft instance
        // Draw faction colors and indicators
        super.render(guiGraphics, mouseX, mouseY, partialTicks); // Updated super call with GuiGraphics
    }
}
