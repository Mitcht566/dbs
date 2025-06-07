package com.dragonball.planets.client.hud;

import com.dragonball.airship.AirshipEntity;
import com.dragonball.planets.PlanetMod;
import com.mojang.blaze3d.vertex.PoseStack; // Still needed for compatibility or if used elsewhere
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraft.client.gui.GuiGraphics; // Import GuiGraphics

@Mod.EventBusSubscriber(modid = PlanetMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlanetDistanceHud {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        // Removed the specific ForgeGui.DEBUG_TEXT_OVERLAY check due to API change,
        // as it might not be directly exposed or might have changed name.
        // If you specifically want to hide it when debug text is on, consider checking Minecraft.getInstance().options.renderDebug().

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || !(player.getVehicle() instanceof AirshipEntity airship)) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics(); // Get GuiGraphics from the event
        Font font = mc.font;

        // Assuming targetX, targetY, targetZ, and autopilot are public fields in AirshipEntity
        // OR you have public getter methods like airship.getTargetX(), airship.isAutopilotActive()
        double targetX = airship.targetX;
        double targetY = airship.targetY;
        double targetZ = airship.targetZ;

        if (!airship.autopilot) {
            return;
        }

        Vec3 airshipPos = airship.position();
        Vec3 targetPos = new Vec3(targetX, targetY, targetZ);
        double distance = airshipPos.distanceTo(targetPos);

        Component distanceText = Component.literal(String.format("Target Distance: %.1f blocks", distance));
        int distanceTextColor = 0xFFFFFF; // White

        int x = 10;
        int y = 10;

        // Use GuiGraphics for drawing strings
        guiGraphics.drawString(font, distanceText, x, y, distanceTextColor, false);

        if (distance <= 500) {
            Component warningText = Component.literal("WARNING: Approaching Target Planet!");
            int warningTextColor = 0xFF0000; // Red

            guiGraphics.drawString(font, warningText, x, y + font.lineHeight + 2, warningTextColor, false);
        }
    }
}
