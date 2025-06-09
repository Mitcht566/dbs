package com.dragonballz.client.anim;

import net.minecraft.world.entity.player.Player;
import io.redspace.ironsspellbooks.api.animation.PlayerAnimationLibWrapper;

public class PlayerAnimationIntegration {
    public static void playAttackAnimation(Player player) {
        PlayerAnimationLibWrapper.playAttack(player);
    }

    public static void playTransformAnimation(Player player) {
        PlayerAnimationLibWrapper.playTransform(player);
    }

    public static void playKiChargeAnimation(Player player) {
        PlayerAnimationLibWrapper.playKiCharge(player);
    }
}