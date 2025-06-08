package com.dragonball.common.race;

import java.util.HashMap;
import java.util.Map;

public class RacialSkillRegistry {
    private static final Map<RaceType, RacialSkill> registry = new HashMap<>();

    public static void register(RaceType race, RacialSkill skill) {
        registry.put(race, skill);
    }

    public static RacialSkill getSkill(RaceType race) {
        return registry.get(race);
    }
}
