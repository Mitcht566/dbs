package com.dragonball.planets.client;

import com.dragonball.planets.faction.PlanetSystemManager.Planet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;

public class PlanetBillboardRenderer {
    // Call this from your space dimension/world render event
    public static void renderPlanetBillboard(Planet planet, PoseStack poseStack, MultiBufferSource buffers, double cameraX, double cameraY, double cameraZ) {
        double dx = planet.spacePos.getX() - cameraX;
        double dy = planet.spacePos.getY() - cameraY;
        double dz = planet.spacePos.getZ() - cameraZ;

        poseStack.pushPose();
        poseStack.translate(dx, dy, dz);

        Minecraft mc = Minecraft.getInstance();
        float playerYaw = mc.player.getViewYRot(1.0f);
        float playerPitch = mc.player.getViewXRot(1.0f);
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(-playerYaw));
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(playerPitch));

        float size = planet.radius / 4.0f;
        poseStack.scale(size, size, size);

        ResourceLocation tex = new ResourceLocation("dragonball", "textures/planet/" + planet.biome + ".png");
        // Draw quad (pseudo, use Minecraft's VertexConsumer / RenderType)
        // Example: drawTexturedQuad(poseStack, buffers, tex, ...);

        poseStack.popPose();
    }
}
