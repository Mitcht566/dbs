package com.dragonball.airship.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics; // Import GuiGraphics
import com.mojang.blaze3d.vertex.PoseStack; // Keep if PoseStack is used elsewhere or remove if not needed.

public class AirshipControlScreen extends Screen {
    // Updated: Use ResourceLocation(namespace:path) string constructor for newer Forge
    private final ResourceLocation TEXTURE = new ResourceLocation("dragonballairship:textures/gui/airship_control.png");

    public AirshipControlScreen() {
        super(Component.literal("Airship Control"));
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(Button.builder(Component.literal("Forward"), b -> sendMoveCommand("forward"))
            .bounds(width / 2 - 50, height / 2 - 20, 100, 20)
            .build());
        addRenderableWidget(Button.builder(Component.literal("Backward"), b -> sendMoveCommand("backward"))
            .bounds(width / 2 - 50, height / 2 + 10, 100, 20)
            .build());
    }

    private void sendMoveCommand(String direction) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Sending command: " + direction));
        this.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int x = (width - 176) / 2;
        int y = (height - 166) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, 176, 166);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
