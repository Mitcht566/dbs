package com.dragonballz.util;

import java.util.HashMap;
import java.util.Map;

public class TransformationMultipliers {
    public static final Map<String, float[]> multipliers = new HashMap<>();
    public static final Map<String, Integer> energyCost = new HashMap<>();
    public static final Map<String, Long> requiredPL = new HashMap<>();

    static {
        // Saiyans/Bio-Androids
        multipliers.put("Super Saiyan", new float[]{5, 5, 5, 1, 1, 5});
        energyCost.put("Super Saiyan", 100);
        requiredPL.put("Super Saiyan", 3_000_000L);

        multipliers.put("Super Saiyan Grade 2", new float[]{8, 8, 8, 1, 1, 8});
        energyCost.put("Super Saiyan Grade 2", 250);
        requiredPL.put("Super Saiyan Grade 2", 6_000_000L);

        multipliers.put("Super Saiyan Grade 3", new float[]{12, 12, 12, 1, 1, 12});
        energyCost.put("Super Saiyan Grade 3", 400);
        requiredPL.put("Super Saiyan Grade 3", 9_000_000L);

        multipliers.put("Perfected Super Saiyan", new float[]{10, 10, 10, 1, 1, 10});
        energyCost.put("Perfected Super Saiyan", 50);
        requiredPL.put("Perfected Super Saiyan", 15_000_000L);

        multipliers.put("Super Saiyan 2", new float[]{20, 20, 20, 1, 1, 20});
        energyCost.put("Super Saiyan 2", 400);
        requiredPL.put("Super Saiyan 2", 20_000_000L);

        multipliers.put("Super Saiyan 3", new float[]{50, 50, 50, 1, 1, 50});
        energyCost.put("Super Saiyan 3", 3000);
        requiredPL.put("Super Saiyan 3", 90_000_000L);

        multipliers.put("Super Saiyan God", new float[]{200, 200, 200, 1, 1, 200});
        energyCost.put("Super Saiyan God", 1000);
        requiredPL.put("Super Saiyan God", 500_000_000L);

        multipliers.put("Super Saiyan Blue", new float[]{220, 220, 220, 1, 1, 220});
        energyCost.put("Super Saiyan Blue", 1000);
        requiredPL.put("Super Saiyan Blue", 750_000_000L);

        multipliers.put("Super Saiyan Blue Evolved", new float[]{250, 250, 250, 1, 1, 250});
        energyCost.put("Super Saiyan Blue Evolved", 2000);
        requiredPL.put("Super Saiyan Blue Evolved", 1_000_000_000L);

        // Universal
        multipliers.put("God Ki", new float[]{100, 100, 100, 1, 1, 100});
        energyCost.put("God Ki", 800);
        requiredPL.put("God Ki", 250_000_000L);

        multipliers.put("Ultra Instinct Sign", new float[]{200, 200, 200, 1, 1, 200});
        energyCost.put("Ultra Instinct Sign", 800);
        requiredPL.put("Ultra Instinct Sign", 1_000_000_000L);

        multipliers.put("Mastered Ultra Instinct", new float[]{280, 280, 280, 1, 1, 280});
        energyCost.put("Mastered Ultra Instinct", 2000);
        requiredPL.put("Mastered Ultra Instinct", 2_000_000_000L);

        multipliers.put("True Ultra Instinct", new float[]{350, 350, 350, 1, 1, 350});
        energyCost.put("True Ultra Instinct", 800);
        requiredPL.put("True Ultra Instinct", 5_000_000_000L);

        multipliers.put("Destroyer Ki", new float[]{350, 350, 350, 1, 1, 350});
        energyCost.put("Destroyer Ki", 3000);
        requiredPL.put("Destroyer Ki", 5_000_000_000L);
    }

    public static float[] getMultipliers(String form) {
        return multipliers.getOrDefault(form, new float[]{1, 1, 1, 1, 1, 1});
    }

    public static int getEnergyCost(String form) {
        return energyCost.getOrDefault(form, 0);
    }

    public static long getRequiredPL(String form) {
        return requiredPL.getOrDefault(form, 0L);
    }
}