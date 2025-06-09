package com.dragonballz.handlers;

import com.dragonballz.skills.TurboModeHandler;
import com.dragonballz.client.anim.KiAuraIntegration;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dragonballz")
public class TurboModeEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.level().isClientSide) return;
        boolean turbo = TurboModeHandler.isTurboMode(player);
        if (turbo) {
            KiAuraIntegration.enableAura(player);
        } else {
            KiAuraIntegration.disableAura(player);
        }
    }
}