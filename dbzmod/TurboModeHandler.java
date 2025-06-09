package com.dragonballz.skills;

import net.minecraft.world.entity.player.Player;

public class TurboModeHandler {
    public static boolean isTurboMode(Player player) {
        return player.getPersistentData().getBoolean("DBZ_TurboMode");
    }

    public static void setTurboMode(Player player, boolean enabled) {
        player.getPersistentData().putBoolean("DBZ_TurboMode", enabled);
    }

    public static boolean canUseTurbo(Player player) {
        return KiUnlockHandler.isKiUnlocked(player);
    }
}