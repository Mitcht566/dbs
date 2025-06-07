package com.dragonball.planets.airship;

import com.dragonball.planets.faction.PlanetSystemManager;
import com.dragonball.planets.entity.PlanetEntitySpawner;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AirshipGpsHandler {
    // Track the spawned armor stand for each player (or airship)
    public static final Map<UUID, UUID> playerPlanetArmorStand = new HashMap<>(); // Player UUID -> ArmorStand UUID
    public static final Map<UUID, String> playerTargetPlanet = new HashMap<>(); // Player UUID -> Planet Name

    /**
     * Call this when the player uses the GPS block to select a new planet.
     * @param player The player
     * @param targetPlanet The planet the player is now targeting
     * @param universe7Level The 'universe7' dimension level
     * @param planetTextureItem The ItemStack representing the planet's texture
     */
    public static void onGpsPlanetSelected(ServerPlayer player, PlanetSystemManager.Planet targetPlanet, ServerLevel universe7Level, ItemStack planetTextureItem) {
        despawnArmorStand(player, universe7Level);

        var stand = PlanetEntitySpawner.spawnPlanet(universe7Level, targetPlanet.spacePos, planetTextureItem);
        if (stand != null) {
            playerPlanetArmorStand.put(player.getUUID(), stand.getUUID());
            playerTargetPlanet.put(player.getUUID(), targetPlanet.name);

            // Save to registry for global tracking & persistence
            PlanetSystemManager.registerPlanetArmorStand(targetPlanet.name, stand.getUUID(), targetPlanet.spacePos);
        }
    }

    public static void despawnArmorStand(ServerPlayer player, ServerLevel universe7Level) {
        UUID standUuid = playerPlanetArmorStand.remove(player.getUUID());
        playerTargetPlanet.remove(player.getUUID());
        if (standUuid != null) {
            var entity = universe7Level.getEntity(standUuid);
            if (entity != null) entity.discard();
        }
    }
}
