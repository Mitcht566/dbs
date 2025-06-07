package com.dragonball.airship;

import com.dragonball.airship.client.gui.GPSControllerScreen;
import com.dragonball.airship.client.gui.AirshipControlScreen;
import com.dragonball.planets.PlanetMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.core.BlockPos;

@Mod.EventBusSubscriber(modid = PlanetMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AirshipClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Register client-side entity renderers here. Example:
        // EntityRenderers.register(PlanetRegistry.AIRSHIP_ENTITY.get(), AirshipRenderer::new);
    }

    public static void openManualControl() {
        Minecraft.getInstance().setScreen(new AirshipControlScreen());
    }

    // Corrected: Removed BlockPos argument as GPSControllerScreen constructor does not take it
    public static void openGPSControl() {
        Minecraft.getInstance().setScreen(new GPSControllerScreen());
    }
}
