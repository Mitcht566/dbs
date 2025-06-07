package com.dragonball.planets.faction;

public enum Faction {
    GOOD("Good", 0xFF00BFFF),
    EVIL("Evil", 0xFFFF0000),
    NEUTRAL("Neutral", 0xFFFFFF00),
    FRIEZA_FORCE("Frieza Force", 0xFF8000FF); // Purple

    private final String displayName;
    private final int color;

    Faction(String displayName, int color) {
        this.displayName = displayName;
        this.color = color;
    }

    public String getDisplayName() { return displayName; }
    public int getColor() { return color; }
}
