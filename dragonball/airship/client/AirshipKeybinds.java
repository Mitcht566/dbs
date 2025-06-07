package com.dragonball.airship.client;

import com.dragonball.airship.AirshipClientSetup;
import com.dragonball.planets.PlanetMod;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.eventbus.api.SubscribeEvent; // Added import for SubscribeEvent

@Mod.EventBusSubscriber(modid = PlanetMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AirshipKeybinds {
    public static KeyMapping OPEN_AIRSHIP_CONTROL;
    public static KeyMapping OPEN_GPS_SCREEN;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        // Corrected KeyMapping constructor for 1.20.1+: Use GLFW.GLFW_KEY directly for the key code
        OPEN_AIRSHIP_CONTROL = new KeyMapping("key.dragonballairship.airship_control", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, "key.categories.airship");
        OPEN_GPS_SCREEN = new KeyMapping("key.dragonballairship.open_gps", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.airship");

        event.register(OPEN_AIRSHIP_CONTROL);
        event.register(OPEN_GPS_SCREEN);
    }

    public static void setupInputHandling() {
        MinecraftForge.EVENT_BUS.addListener(AirshipKeybinds::onKeyInput);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (OPEN_AIRSHIP_CONTROL.consumeClick()) {
            AirshipClientSetup.openManualControl();
        }
        if (OPEN_GPS_SCREEN.consumeClick()) {
            AirshipClientSetup.openGPSControl(); // Corrected: Removed null argument
        }
    }
}
