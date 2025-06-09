package com.dragonballz.handlers;

import com.dragonballz.skills.KiUnlockHandler;
import com.dragonballz.skills.FlySkillHandler;
import com.dragonballz.skills.TurboModeHandler;
import com.dragonballz.client.anim.KiAuraIntegration;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "dragonballz", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkillActivationHandler {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Player player = net.minecraft.client.Minecraft.getInstance().player;
        if (player == null) return;

        // K - Fly toggle
        if (event.getKey() == GLFW.GLFW_KEY_K && event.getAction() == GLFW.GLFW_PRESS && FlySkillHandler.canFly(player)) {
            FlySkillHandler.setFlying(player, !FlySkillHandler.isFlying(player));
        }
        // R - Turbo Mode toggle (if ki unlocked)
        if (event.getKey() == GLFW.GLFW_KEY_R && event.getAction() == GLFW.GLFW_PRESS && TurboModeHandler.canUseTurbo(player)) {
            boolean turbo = !TurboModeHandler.isTurboMode(player);
            TurboModeHandler.setTurboMode(player, turbo);
            if (turbo) {
                KiAuraIntegration.enableAura(player);
            } else {
                KiAuraIntegration.disableAura(player);
            }
        }
    }
}