package com.dragonball.airship;

public class AirshipNavigator {
    private static AirshipEntity airship;

    public static void setAirship(AirshipEntity entity) {
        airship = entity;
    }

    public static void setTarget(double x, double y, double z) {
        if (airship != null) {
            airship.setTarget(x, y, z);
        }
    }
}
