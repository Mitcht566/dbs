package com.dragonball.planets.faction;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import java.util.*;

public class FactionManager {
    private static final Map<ResourceLocation, FactionType> planetFactions = new HashMap<>();

    public static void init() {
        assignFriezaForceToNearest250();
    }

    private static void assignFriezaForceToNearest250() {
        // Placeholder: Replace with actual sorted list of 250 closest planets
        for (int i = 0; i < 250; i++) {
            ResourceLocation planet = new ResourceLocation("dragonballplanets:planet_" + i);
            planetFactions.put(planet, FactionType.FRIEZA_FORCE);
        }
    }

    public static FactionType getFaction(ResourceLocation planet) {
        return planetFactions.getOrDefault(planet, FactionType.NEUTRAL);
    }

    public static void setFaction(ResourceLocation planet, FactionType type) {
        planetFactions.put(planet, type);
    }
}
