package com.dragonball.common.race;

public interface RacialAbility {
    void applyAbility(net.minecraft.world.entity.player.Player player);
    String getDescription();
}
