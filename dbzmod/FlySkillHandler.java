package com.dragonballz.skills;

import net.minecraft.world.entity.player.Player;

public class FlySkillHandler {
    public static boolean canFly(Player player) {
        return player.getPersistentData().getBoolean("DBZ_FlySkillUnlocked");
    }

    public static void unlockFly(Player player) {
        player.getPersistentData().putBoolean("DBZ_FlySkillUnlocked", true);
    }

    public static boolean isFlying(Player player) {
        return player.getPersistentData().getBoolean("DBZ_IsFlying");
    }

    public static void setFlying(Player player, boolean flying) {
        player.getPersistentData().putBoolean("DBZ_IsFlying", flying);
    }
}