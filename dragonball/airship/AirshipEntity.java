package com.dragonball.airship;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AirshipEntity extends Entity {
    public double targetX, targetY, targetZ;
    public boolean autopilot = false;

    public AirshipEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        targetX = tag.getDouble("TargetX");
        targetY = tag.getDouble("TargetY");
        targetZ = tag.getDouble("TargetZ");
        autopilot = tag.getBoolean("Autopilot");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putDouble("TargetX", targetX);
        tag.putDouble("TargetY", targetY);
        tag.putDouble("TargetZ", targetZ);
        tag.putBoolean("Autopilot", autopilot);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;

        if (autopilot) {
            double dx = targetX - getX();
            double dy = targetY - getY();
            double dz = targetZ - getZ(); // Corrected: dz declared here
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (dist > 1.0) {
                double speed = 10.0 / 20.0;
                setDeltaMovement(dx / dist * speed, dy / dist * speed, dz / dist * speed);
                this.setPos(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);
            } else {
                this.autopilot = false;
                this.setDeltaMovement(0, 0, 0);
                this.setPos(targetX, targetY, targetZ);
            }
        }
    }

    public void setTarget(double x, double y, double z) {
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
        this.autopilot = true;
    }

    public double getTargetX() { return targetX; }
    public double getTargetY() { return targetY; }
    public double getTargetZ() { return targetZ; }
    public boolean isAutopilotActive() { return autopilot; }
}
