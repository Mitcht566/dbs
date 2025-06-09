package com.dragonballz.skills;

import com.dragonballz.stats.StatManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import java.util.*;

public class AbsorbSkill {
    public static void absorbMob(Player player, LivingEntity mob, boolean isAndroid, boolean isBioAndroid) {
        String absorbKey = isAndroid ? "DBZ_AndroidAbsorbed" : "DBZ_BioAndroidAbsorbed";
        int count = player.getPersistentData().getInt(absorbKey);
        count++;
        player.getPersistentData().putInt(absorbKey, count);

        float health = (float) mob.getAttributeValue(Attributes.MAX_HEALTH);
        float addAmount = health * 0.25f;

        if (isAndroid) {
            List<StatManager.Stat> possibleStats = new ArrayList<>(Arrays.asList(StatManager.Stat.values()));
            possibleStats.remove(StatManager.Stat.MIND);
            StatManager.Stat chosenStat = possibleStats.get(player.level().random.nextInt(possibleStats.size()));
            float oldVal = StatManager.getStat(player, chosenStat);
            StatManager.setStat(player, chosenStat, oldVal + addAmount);

            float maxEnergy = player.getPersistentData().contains("DBZ_MaxEnergy") ? player.getPersistentData().getFloat("DBZ_MaxEnergy") : 100f;
            float curEnergy = player.getPersistentData().contains("DBZ_Energy") ? player.getPersistentData().getFloat("DBZ_Energy") : 0f;
            player.getPersistentData().putFloat("DBZ_Energy", Math.min(curEnergy + maxEnergy * 0.5f, maxEnergy));
        } else if (isBioAndroid) {
            for (StatManager.Stat stat : StatManager.Stat.values()) {
                if (stat == StatManager.Stat.MIND) continue;
                float oldVal = StatManager.getStat(player, stat);
                StatManager.setStat(player, stat, oldVal + addAmount);
            }
        }
        player.sendSystemMessage(Component.literal("Absorbed mob: " + count + "/100"));
    }

    public static boolean tryNullifyKiAttack(Player player, String attackType) {
        if (attackType.equals("Spirit Bomb") || attackType.equals("Supernova")) return false;
        return true;
    }
}