package com.dragonball.planets.airship;

import com.dragonball.planets.faction.PlanetSystemManager;
import net.minecraft.server.level.ServerPlayer;
import java.util.List;

public class AirshipPlanetInteraction {
    public static void checkPlayerProximity(ServerPlayer player, List<PlanetSystemManager.Planet> planets) {
        var playerPos = player.blockPosition();
        for (var planet : planets) {
            double distSq = playerPos.distSqr(planet.spacePos);
            if (distSq <= planet.radius * planet.radius) {
                com.dragonball.planets.faction.PlanetTeleportHandler.teleportPlayerToPlanet(player, planet);
                break;
            }
        }
    }
}
