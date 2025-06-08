package com.dragonball.common.ki;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KiChargeHandler {

    public static void chargeKi(Player player) {
        player.getCapability(com.dragonball.common.capability.KiCapabilityProvider.KI_CAP).ifPresent(ki -> {
            ki.addKi(5); // simple increment, tune as needed
        });
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            // Example: charge ki when holding key (implement actual key check yourself)
            if (com.dragonball.client.keybinds.KeyBindings.KI_CHARGE_KEY.isDown()) {
                chargeKi(player);
            }
        }
    }
}
