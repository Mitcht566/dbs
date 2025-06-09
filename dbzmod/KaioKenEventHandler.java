package com.dragonballz.handlers;

import com.dragonballz.skills.KaioKenSkillHandler;
import com.dragonballz.util.PowerLevelCalculator;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dragonballz")
public class KaioKenEventHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!KaioKenSkillHandler.isKaioKenActive(player)) return;
        int level = KaioKenSkillHandler.getKaioKenLevel(player);
        int cost = KaioKenSkillHandler.getKaioKenHealthCost(player);
        int duration = KaioKenSkillHandler.getKaioKenDuration(player);

        if (player.tickCount % (duration * 20) == 0) {
            player.hurt(player.damageSources().magic(), cost);
            if (player.getHealth() <= 0.1f) {
                KaioKenSkillHandler.setKaioKenActive(player, false);
            }
        }
    }
}