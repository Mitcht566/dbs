package com.dragonballz.handlers;

import com.dragonballz.client.anim.KiAuraIntegration;
import com.dragonballz.client.anim.PlayerAnimationIntegration;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "dragonballz", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KiChargeTransformEventHandler {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Player player = net.minecraft.client.Minecraft.getInstance().player;
        if (player == null) return;
        if (event.getKey() == GLFW.GLFW_KEY_C && event.getAction() == GLFW.GLFW_PRESS) {
            // Charge Ki
            KiAuraIntegration.enableAura(player);
            PlayerAnimationIntegration.playKiChargeAnimation(player);
        }
        if (event.getKey() == GLFW.GLFW_KEY_H && event.getAction() == GLFW.GLFW_PRESS) {
            // Transform
            KiAuraIntegration.enableAura(player);
            PlayerAnimationIntegration.playTransformAnimation(player);
        }
        // Optionally, disable aura when key is released
        if ((event.getKey() == GLFW.GLFW_KEY_C || event.getKey() == GLFW.GLFW_KEY_H) && event.getAction() == GLFW.GLFW_RELEASE) {
            KiAuraIntegration.disableAura(player);
        }
    }
}