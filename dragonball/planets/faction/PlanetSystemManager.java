package com.dragonball.planets.faction;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlanetSystemManager extends SavedData {
    private static final String DATA_NAME = "dragonball_planet_stands";
    private static final Map<String, UUID> planetArmorStandUuids = new HashMap<>();
    private static final Map<String, BlockPos> planetArmorStandPositions = new HashMap<>();

    // ---- Registry API ----
    public static void registerPlanetArmorStand(String planetName, UUID armorStandUuid, BlockPos pos) {
        planetArmorStandUuids.put(planetName, armorStandUuid);
        planetArmorStandPositions.put(planetName, pos);
    }

    public static UUID getPlanetArmorStandUuid(String planetName) {
        return planetArmorStandUuids.get(planetName);
    }

    public static BlockPos getPlanetArmorStandPos(String planetName) {
        return planetArmorStandPositions.get(planetName);
    }

    public static void removePlanetArmorStand(String planetName) {
        planetArmorStandUuids.remove(planetName);
        planetArmorStandPositions.remove(planetName);
    }

    // ---- Persistence ----
    public static PlanetSystemManager get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(PlanetSystemManager::load, PlanetSystemManager::new, DATA_NAME);
    }

    public PlanetSystemManager() {}

    public static PlanetSystemManager load(CompoundTag tag) {
        PlanetSystemManager manager = new PlanetSystemManager();
        ListTag standList = tag.getList("planetStands", Tag.TAG_COMPOUND);
        for (Tag t : standList) {
            CompoundTag entry = (CompoundTag)t;
            String name = entry.getString("planet");
            UUID uuid = entry.getUUID("uuid");
            BlockPos pos = new BlockPos(entry.getInt("x"), entry.getInt("y"), entry.getInt("z"));
            planetArmorStandUuids.put(name, uuid);
            planetArmorStandPositions.put(name, pos);
        }
        return manager;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag standList = new ListTag();
        for (String planet : planetArmorStandUuids.keySet()) {
            CompoundTag entry = new CompoundTag();
            entry.putString("planet", planet);
            entry.putUUID("uuid", planetArmorStandUuids.get(planet));
            BlockPos pos = planetArmorStandPositions.get(planet);
            entry.putInt("x", pos.getX());
            entry.putInt("y", pos.getY());
            entry.putInt("z", pos.getZ());
            standList.add(entry);
        }
        tag.put("planetStands", standList);
        return tag;
    }
}
