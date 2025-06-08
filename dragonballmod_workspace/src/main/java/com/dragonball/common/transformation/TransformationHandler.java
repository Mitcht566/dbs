package com.dragonball.common.transformation;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TransformationHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;

            // Example transformation activation (toggle on key press)
            if (com.dragonball.client.keybinds.KeyBindings.TRANSFORM_KEY.consumeClick()) {
                // Cycle transformations or toggle (simplified)
                TransformationType current = getCurrentTransformation(player);
                TransformationType next = getNextTransformation(current);
                setTransformation(player, next);
            }

            // Handle ki drain per transformation
            TransformationType current = getCurrentTransformation(player);
            if (current != TransformationType.BASE) {
                player.getCapability(com.dragonball.common.capability.KiCapabilityProvider.KI_CAP).ifPresent(ki -> {
                    ki.drainKi(2);
                });
            }
        }
    }

    private static TransformationType getCurrentTransformation(Player player) {
        // Placeholder, store in capability or persistent data
        return TransformationType.BASE;
    }

    private static TransformationType getNextTransformation(TransformationType current) {
        switch (current) {
            case BASE: return TransformationType.SUPER_SAIYAN;
            case SUPER_SAIYAN: return TransformationType.SUPER_SAIYAN_BLUE;
            case SUPER_SAIYAN_BLUE: return TransformationType.ULTRA_INSTINCT;
            default: return TransformationType.BASE;
        }
    }

    private static void setTransformation(Player player, TransformationType type) {
        // Save transformation to capability or persistent data
    }
}
