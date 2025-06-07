package com.dragonball.planets.util;

import net.minecraft.resources.ResourceLocation;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlanetConquestGoal {
    private static final Map<ResourceLocation, Integer> conquestGoals = new HashMap<>();

    public static void assignGoal(ResourceLocation planet) {
        int killsNeeded = (new Random().nextInt(3001)) + 2000;
        // Corrected: Use ResourceLocation(namespace, path) constructor
        conquestGoals.put(planet, killsNeeded);
    }

    public static int getGoal(ResourceLocation planet) {
        return conquestGoals.getOrDefault(planet, 5000);
    }

    public static void markKill(ResourceLocation planet) {
        conquestGoals.put(planet, getGoal(planet) - 1);
    }

    public static boolean isConquered(ResourceLocation planet) {
        return getGoal(planet) <= 0;
    }
}
