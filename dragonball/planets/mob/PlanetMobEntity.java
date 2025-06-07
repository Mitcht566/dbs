package com.dragonball.planets.mob;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PlanetMobEntity extends Mob {
    public PlanetMobEntity(EntityType<? extends Mob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder setAttributes(double health, double damage) {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, health)
            .add(Attributes.ATTACK_DAMAGE, damage)
            .add(Attributes.MOVEMENT_SPEED, 0.25);
    }
}
