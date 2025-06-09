package com.dragonballz.skills;

import net.minecraft.world.entity.player.Player;

public class KaioKenSkillHandler {
    private static final float[] MULTIPLIERS = {0.5f, 1f, 1.5f, 2f, 3f, 4f, 5f, 6f, 7f, 8f};
    private static final int[] HEALTH_COST = {100, 200, 500, 1000, 1500, 2500, 3500, 4500, 5500, 8000};
    private static final int[] SECONDS = {5, 5, 5, 5, 3, 3, 3, 3, 3, 3};
    private static final long[] REQUIRED_PL = {0, 0, 0, 0, 1_000_000, 3_000_000, 3_000_000, 8_000_000, 10_000_000, 12_000_000};

    public static int getKaioKenLevel(Player player) {
        return player.getPersistentData().getInt("DBZ_KaioKen_Level");
    }

    public static void setKaioKenLevel(Player player, int level) {
        player.getPersistentData().putInt("DBZ_KaioKen_Level", level);
    }

    public static boolean isKaioKenActive(Player player) {
        return player.getPersistentData().getBoolean("DBZ_KaioKen_Active");
    }

    public static void setKaioKenActive(Player player, boolean active) {
        player.getPersistentData().putBoolean("DBZ_KaioKen_Active", active);
    }

    public static float getKaioKenMultiplier(Player player) {
        return MULTIPLIERS[getKaioKenLevel(player)];
    }

    public static int getKaioKenHealthCost(Player player) {
        return HEALTH_COST[getKaioKenLevel(player)];
    }

    public static int getKaioKenDuration(Player player) {
        return SECONDS[getKaioKenLevel(player)];
    }

    public static boolean canActivateLevel(Player player, int level, long powerLevel) {
        return powerLevel >= REQUIRED_PL[level];
    }
}