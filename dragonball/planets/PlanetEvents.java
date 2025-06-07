package com.dragonball.planets;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = PlanetMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlanetEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Example: Register a custom screen
            // MenuScreens.register(YOUR_MENU_TYPE, YourMenuScreen::new);
        });
    }
}
