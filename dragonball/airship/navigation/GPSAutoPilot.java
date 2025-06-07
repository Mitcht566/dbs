package com.dragonball.airship.navigation;

import com.dragonball.airship.AirshipEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class GPSAutoPilot {
    private final AirshipEntity airship;

    public GPSAutoPilot(AirshipEntity airship) {
        this.airship = airship;
    }

    public void navigateTo(BlockPos targetPos) {
        if (airship != null) {
            airship.setTarget(targetPos.getX(), targetPos.getY(), targetPos.getZ());
        }
    }

    public static BlockPos findNearestPlanet(ServerLevel level, BlockPos currentPos) {
        return currentPos.offset(500, 0, 500);
    }
}
