package com.dragonball.airship;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.dragonball.planets.PlanetMod;
import com.mojang.blaze3d.platform.InputConstants; // Import InputConstants

@Mod.EventBusSubscriber(modid = PlanetMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AirshipManualControl {
    private static final double SPEED = 0.5;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player != null && player.getVehicle() instanceof AirshipEntity airship) {
            // Corrected: Use mc.options for movement keys. keySneak is now keyShift.
            if (mc.options.keyUp.isDown()) airship.setDeltaMovement(airship.getDeltaMovement().add(0, 0, -SPEED));
            if (mc.options.keyDown.isDown()) airship.setDeltaMovement(airship.getDeltaMovement().add(0, 0, SPEED));
            if (mc.options.keyLeft.isDown()) airship.setDeltaMovement(airship.getDeltaMovement().add(-SPEED, 0, 0));
            if (mc.options.keyRight.isDown()) airship.setDeltaMovement(airship.getDeltaMovement().add(SPEED, 0, 0));
            if (mc.options.keyJump.isDown()) airship.setDeltaMovement(airship.getDeltaMovement().add(0, SPEED, 0));
            if (mc.options.keyShift.isDown()) airship.setDeltaMovement(airship.getDeltaMovement().add(0, -SPEED, 0)); // Corrected to keyShift

            airship.autopilot = false;
        }
    }
}
