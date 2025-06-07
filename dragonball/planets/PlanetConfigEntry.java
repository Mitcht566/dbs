package com.dragonball.planets;

// No specific imports needed for this simple data class, assuming Gson handles it.

public class PlanetConfigEntry {
    // These fields will be populated by Gson when reading the JSON
    public int x;
    public int z;
    public int textureId; // Ensure this matches the key in your JSON (e.g., "textureId")

    // A default no-argument constructor is good practice for JSON deserialization
    public PlanetConfigEntry() {}

    // An optional constructor for easier creation in code (e.g., for default entries)
    public PlanetConfigEntry(int x, int z, int textureId) {
        this.x = x;
        this.z = z;
        this.textureId = textureId;
    }

    @Override
    public String toString() {
        return "PlanetConfigEntry{" +
               "x=" + x +
               ", z=" + z +
               ", textureId=" + textureId +
               '}';
    }
}