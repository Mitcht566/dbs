package com.dragonballz.race;

import net.minecraft.world.entity.player.Player;
import java.util.Random;

public class UniversalTransformationEffects {
    // Returns true if dodged
    public static boolean tryUltraInstinctDodge(Player player, String form, int attacksAgainstPlayer) {
        Random rand = new Random();
        if ("Ultra Instinct Sign".equals(form) && attacksAgainstPlayer % 10 == 0)
            return rand.nextFloat() < 0.10f;
        if ("Mastered Ultra Instinct".equals(form) && attacksAgainstPlayer % 5 == 0)
            return rand.nextFloat() < 0.12f;
        if ("True Ultra Instinct".equals(form) && attacksAgainstPlayer % 5 == 0)
            return rand.nextFloat() < 0.20f;
        return false;
    }
    // Returns true if killed (destroyer effect)
    public static boolean tryDestroyerKill(Player player, int attacksAgainstPlayer) {
        Random rand = new Random();
        if (attacksAgainstPlayer % 10 == 0) {
            float chance = 0.02f + rand.nextFloat() * 0.03f;
            return rand.nextFloat() < chance;
        }
        return false;
    }
    public static boolean isNullifiedByUltraInstinct(Player player, String form) {
        return "Mastered Ultra Instinct".equals(form) || "True Ultra Instinct".equals(form);
    }
}