package com.dragonball.planets;

import com.dragonball.airship.AirshipClientSetup;
import com.dragonball.airship.client.AirshipKeybinds;
import com.dragonball.airship.ModBlocks;
import com.dragonball.airship.ModItems;
import com.dragonball.airship.AirshipManualControl;
import com.dragonball.planets.faction.FactionManager;
import com.dragonball.planets.item.FriezaForceTokenItem;
import com.dragonball.planets.item.RecruitmentWandItem;
import com.dragonball.planets.mob.PlanetMobSpawner;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod(PlanetMod.MOD_ID)
public class PlanetMod {
    public static final String MOD_ID = "dragonballplanets";

    public PlanetMod() {
        // Updated: FMLJavaModLoadingContext.get() is deprecated; review migration guide for newer APIs
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();  // TODO: This method is deprecated, check migration guide.

        // Register deferred registers
        PlanetRegistry.init(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        // Register client setup event for screens and keybinds
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);

        // Register the PlanetEvents class to the mod event bus
        modEventBus.register(PlanetEvents.class);

        // Register AirshipManualControl to the FORGE event bus
        MinecraftForge.EVENT_BUS.register(new AirshipManualControl());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FactionManager.init();
            PlanetMobSpawner.register();
            // Other common setup tasks
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            AirshipKeybinds.setupInputHandling();
        });
    }
}
