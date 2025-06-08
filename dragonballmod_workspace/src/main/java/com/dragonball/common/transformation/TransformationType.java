package com.dragonball.common.transformation;

public enum TransformationType {
    BASE(1.0f),
    SUPER_SAIYAN(1.5f),
    SUPER_SAIYAN_BLUE(2.0f),
    ULTRA_INSTINCT(2.5f);

    private final float powerMultiplier;

    TransformationType(float powerMultiplier) {
        this.powerMultiplier = powerMultiplier;
    }

    public float getPowerMultiplier() {
        return powerMultiplier;
    }
}
