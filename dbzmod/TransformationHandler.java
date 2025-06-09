package com.dragonballz.race;

import com.dragonballz.util.TransformationMultipliers;
import com.dragonballz.util.PowerLevelCalculator;
import net.minecraft.world.entity.player.Player;

public class TransformationHandler {
    public static boolean canTransform(Player player, String transformation) {
        long pl = PowerLevelCalculator.getPowerLevel(player);
        return pl >= TransformationMultipliers.getRequiredPL(transformation);
    }
    public static int getTransformationEnergyCost(String transformation) {
        return TransformationMultipliers.getEnergyCost(transformation);
    }
    public static float[] getTransformationMultipliers(String transformation) {
        return TransformationMultipliers.getMultipliers(transformation);
    }
}