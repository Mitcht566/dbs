package com.dragonball.planets.faction;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class PlanetTeleportHandler {
    public static void teleportPlayerToPlanet(ServerPlayer player, PlanetSystemManager.Planet planet) {
        var planetDim = planet.dimensionKey;
        var serverLevel = player.server.getLevel(planetDim);
        if (serverLevel != null) {
            player.teleportTo(serverLevel, 0.5, 100, 0.5, player.getYRot(), player.getXRot());
        }
    }

    public static void checkPlanetExit(ServerPlayer player, PlanetSystemManager.Planet planet) {
        if (player.getY() > 400) {
            var overworld = player.server.getLevel(Level.OVERWORLD);
            if (overworld != null) {
                var pos = planet.spacePos;
                player.teleportTo(overworld, pos.getX() + 0.5, pos.getY() + planet.radius + 10, pos.getZ() + 0.5, player.getYRot(), player.getXRot());
            }
        }
    }
}
