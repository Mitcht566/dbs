package com.dragonball.airship.client.gui;

import com.dragonball.airship.AirshipNavigator;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics; // Import GuiGraphics
import com.mojang.blaze3d.vertex.PoseStack; // Keep if PoseStack is used elsewhere or remove if not needed.

public class GPSControllerScreen extends Screen { // Changed access to public
    private EditBox xField, yField, zField;

    public GPSControllerScreen() { // Changed to public access
        super(Component.literal("GPS Controller"));
    }

    @Override
    protected void init() {
        xField = new EditBox(font, width / 2 - 50, 40, 100, 20, Component.literal("X"));
        yField = new EditBox(font, width / 2 - 50, 70, 100, 20, Component.literal("Y"));
        zField = new EditBox(font, width / 2 - 50, 100, 100, 20, Component.literal("Z"));

        addRenderableWidget(xField);
        addRenderableWidget(yField);
        addRenderableWidget(zField);

        addRenderableWidget(Button.builder(Component.literal("Set Target"), btn -> {
            try {
                double x = Double.parseDouble(xField.getValue());
                double y = Double.parseDouble(yField.getValue());
                double z = Double.parseDouble(zField.getValue());
                AirshipNavigator.setTarget(x, y, z);
                this.onClose();
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format for GPS coordinates: " + e.getMessage());
            }
        }).bounds(width / 2 - 40, 130, 80, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) { // Changed signature to GuiGraphics
        this.renderBackground(guiGraphics); // Updated rendering call, GuiGraphics.renderBackground() no longer takes minecraft instance
        super.render(guiGraphics, mouseX, mouseY, partialTicks); // Updated super call with GuiGraphics

        // Pass GuiGraphics to render methods of EditBox
        xField.render(guiGraphics, mouseX, mouseY, partialTicks);
        yField.render(guiGraphics, mouseX, mouseY, partialTicks);
        zField.render(guiGraphics, mouseX, mouseY, partialTicks);

        // Use GuiGraphics for drawing strings
        guiGraphics.drawString(font, "X:", xField.getX() - 15, xField.getY() + (xField.getHeight() / 2) - (font.lineHeight / 2), 0xFFFFFF, false);
        guiGraphics.drawString(font, "Y:", yField.getX() - 15, yField.getY() + (yField.getHeight() / 2) - (font.lineHeight / 2), 0xFFFFFF, false);
        guiGraphics.drawString(font, "Z:", zField.getX() - 15, zField.getY() + (zField.getHeight() / 2) - (font.lineHeight / 2), 0xFFFFFF, false);
        guiGraphics.drawCenteredString(font, this.title, width / 2, 20, 0xFFFFFF);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
