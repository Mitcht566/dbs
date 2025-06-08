package com.dragonball.common.flight;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FlightHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            // Simplified flight toggle logic
            if (com.dragonball.client.keybinds.KeyBindings.FLIGHT_TOGGLE_KEY.consumeClick()) {
                player.getAbilities().mayfly = !player.getAbilities().mayfly;
                player.onUpdateAbilities();
            }
            if (player.getAbilities().mayfly && player.isFallFlying()) {
                // Drain ki while flying
                player.getCapability(com.dragonball.common.capability.KiCapabilityProvider.KI_CAP).ifPresent(ki -> {
                    ki.drainKi(1);
                });
            }
        }
    }
}
