package com.dragonball.common.race;

import java.util.HashMap;
import java.util.Map;

public class RacialAbilityRegistry {
    private static final Map<RaceType, RacialAbility> registry = new HashMap<>();

    public static void register(RaceType race, RacialAbility ability) {
        registry.put(race, ability);
    }

    public static RacialAbility getAbility(RaceType race) {
        return registry.get(race);
    }
}
