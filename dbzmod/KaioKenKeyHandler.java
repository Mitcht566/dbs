package com.dragonballz.handlers;

import com.dragonballz.skills.KaioKenSkillHandler;
import com.dragonballz.util.PowerLevelCalculator;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "dragonballz", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KaioKenKeyHandler {
    private static long kaioKenHoldStart = 0;
    private static int lastLevel = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Player player = net.minecraft.client.Minecraft.getInstance().player;
        if (player == null) return;
        // Z: Kaio Ken activation
        if (event.getKey() == GLFW.GLFW_KEY_Z) {
            if (event.getAction() == GLFW.GLFW_PRESS) {
                kaioKenHoldStart = System.currentTimeMillis();
            } else if (event.getAction() == GLFW.GLFW_RELEASE) {
                long held = System.currentTimeMillis() - kaioKenHoldStart;
                int level = Math.min((int)(held / 5000), 9); // 5s per level
                long pl = PowerLevelCalculator.getPowerLevel(player);
                if (KaioKenSkillHandler.canActivateLevel(player, level, pl)) {
                    KaioKenSkillHandler.setKaioKenLevel(player, level);
                    KaioKenSkillHandler.setKaioKenActive(player, true);
                }
                kaioKenHoldStart = 0;
                lastLevel = level;
            }
        }
        // H: Kaio Ken deactivation
        if (event.getKey() == GLFW.GLFW_KEY_H && event.getAction() == GLFW.GLFW_PRESS) {
            KaioKenSkillHandler.setKaioKenActive(player, false);
        }
    }
}