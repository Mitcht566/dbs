package com.dragonballz.race;

import com.dragonballz.stats.StatManager;
import net.minecraft.world.entity.player.Player;
import java.util.EnumMap;
import java.util.Map;

public class RacialStats {
    public enum Race {
        HUMAN, SAIYAN, NAMEKIAN, ARCOSIAN, ANDROID, BIO_ANDROID
    }

    public static final Map<Race, float[]> STARTING_STATS = new EnumMap<>(Race.class);

    static {
        STARTING_STATS.put(Race.HUMAN,      new float[]{8f, 8f, 50f, 10f, 5f, 10f});
        STARTING_STATS.put(Race.SAIYAN,     new float[]{15f, 12f, 50f, 10f, 10f, 10f});
        STARTING_STATS.put(Race.NAMEKIAN,   new float[]{10f, 10f, 50f, 10f, 10f, 10f});
        STARTING_STATS.put(Race.ARCOSIAN,   new float[]{10f, 10f, 50f, 10f, 15f, 15f});
        STARTING_STATS.put(Race.ANDROID,    new float[]{10f, 10f, 50f, 10f, 10f, 10f});
        STARTING_STATS.put(Race.BIO_ANDROID,new float[]{12f, 12f, 50f, 10f, 12f, 15f});
    }

    public static void applyStartingStats(Player player, Race race) {
        float[] stats = STARTING_STATS.get(race);
        StatManager.Stat[] statTypes = StatManager.Stat.values();
        for (int i = 0; i < statTypes.length; i++) {
            StatManager.setStat(player, statTypes[i], stats[i]);
        }
    }

    public static Race getRace(Player player) {
        String race = player.getPersistentData().getString("DBZRace");
        try {
            return Race.valueOf(race.toUpperCase());
        } catch (Exception e) {
            return Race.HUMAN;
        }
    }
}