package com.dragonballz.client.anim;

import net.minecraft.world.entity.LivingEntity;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

public class KiAuraIntegration {
    private static final Set<UUID> auraEntities = new HashSet<>();

    public static void enableAura(LivingEntity entity) {
        auraEntities.add(entity.getUUID());
    }

    public static void disableAura(LivingEntity entity) {
        auraEntities.remove(entity.getUUID());
    }

    public static boolean hasAura(LivingEntity entity) {
        return auraEntities.contains(entity.getUUID());
    }
}