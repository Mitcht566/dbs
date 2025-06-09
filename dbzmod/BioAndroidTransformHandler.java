package com.dragonballz.race;

import net.minecraft.world.entity.player.Player;

public class BioAndroidTransformHandler {
    public static boolean canUnlockSemiPerfect(Player player) {
        return getPowerLevel(player) >= 10_000_000L;
    }
    public static boolean canUnlockPerfect(Player player) {
        return getPowerLevel(player) >= 25_000_000L;
    }
    public static boolean canUseSuperSaiyan(Player player) {
        return getPowerLevel(player) >= 3_000_000L;
    }
    public static long getPowerLevel(Player player) {
        return com.dragonballz.util.PowerLevelCalculator.getPowerLevel(player);
    }
}