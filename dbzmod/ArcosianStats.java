package com.dragonballz.race;

import com.dragonballz.stats.StatManager;
import com.dragonballz.util.PowerLevelCalculator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;

public class ArcosianStats {
    public static final String NBT_TRANSFORMATION = "DBZ_ArcosianTransformation";
    public static final String NBT_100PERCENT_UNLOCKED = "DBZ_Arcosian100PercentUnlocked";
    public static final String NBT_BASE_STATS = "DBZ_ArcosianBaseStats";
    public static final float ARCOSIAN_RESISTANCE_BONUS = 0.5f;
    public static final ResourceLocation VANILLA_MAX_DAMAGE_ID = new ResourceLocation("minecraft", "generic.max_damage");

    public static boolean isArcosian(Player player) {
        String race = player.getPersistentData().getString("DBZRace");
        return "ARCOSIAN".equalsIgnoreCase(race) || "FROST_DEMON".equalsIgnoreCase(race);
    }

    public static void setupBaseStats(Player player) {
        if (!isArcosian(player)) return;
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains(NBT_BASE_STATS)) {
            float[] lowerClassSaiyanStats = StatManager.getLowerClassSaiyanStats();
            float[] thirdFormStats = new float[lowerClassSaiyanStats.length];
            for (int i = 0; i < lowerClassSaiyanStats.length; i++) {
                thirdFormStats[i] = lowerClassSaiyanStats[i] * 3;
            }
            float[] baseStats = new float[lowerClassSaiyanStats.length];
            for (int i = 0; i < baseStats.length; i++) {
                float stat = thirdFormStats[i] * 8;
                baseStats[i] = Math.min(stat, StatManager.STAT_CAP);
            }
            CompoundTag statsNBT = new CompoundTag();
            int j = 0;
            for (StatManager.Stat stat : StatManager.Stat.values()) {
                statsNBT.putFloat(stat.name(), baseStats[j]);
                StatManager.setStat(player, stat, Math.min(baseStats[j] / 2f, StatManager.STAT_CAP));
                j++;
            }
            tag.put(NBT_BASE_STATS, statsNBT);
            tag.putString(NBT_TRANSFORMATION, "BASE");
            tag.putBoolean(NBT_100PERCENT_UNLOCKED, false);
        }
        applyMeleeResistance(player);
        applyMaxDamageResistance(player);
    }

    public static void applyMeleeResistance(Player player) {
        if (!isArcosian(player)) return;
        AttributeInstance attr = player.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        if (attr != null && attr.getBaseValue() < ARCOSIAN_RESISTANCE_BONUS) {
            attr.setBaseValue(ARCOSIAN_RESISTANCE_BONUS);
        }
    }

    public static void applyMaxDamageResistance(Player player) {
        if (!isArcosian(player)) return;
        Attribute maxDamageAttribute = Registry.ATTRIBUTE.get(VANILLA_MAX_DAMAGE_ID);
        if (maxDamageAttribute != null) {
            AttributeInstance attr = player.getAttribute(maxDamageAttribute);
            if (attr != null && attr.getBaseValue() < ARCOSIAN_RESISTANCE_BONUS) {
                attr.setBaseValue(ARCOSIAN_RESISTANCE_BONUS);
            }
        }
    }

    public static boolean switchTransformation(Player player, String form) {
        if (!isArcosian(player)) return false;
        CompoundTag tag = player.getPersistentData();
        if (!tag.contains(NBT_BASE_STATS)) return false;
        CompoundTag statsNBT = tag.getCompound(NBT_BASE_STATS);

        float[] lowerClassSaiyanStats = StatManager.getLowerClassSaiyanStats();
        float[] thirdFormStats = new float[lowerClassSaiyanStats.length];
        for (int i = 0; i < lowerClassSaiyanStats.length; i++) {
            thirdFormStats[i] = lowerClassSaiyanStats[i] * 3;
        }

        float[] newStats = new float[lowerClassSaiyanStats.length];

        switch (form.toUpperCase()) {
            case "FIRST":
                System.arraycopy(lowerClassSaiyanStats, 0, newStats, 0, lowerClassSaiyanStats.length);
                break;
            case "SECOND":
                for (int i = 0; i < lowerClassSaiyanStats.length; i++)
                    newStats[i] = lowerClassSaiyanStats[i] * 2;
                break;
            case "THIRD":
                System.arraycopy(thirdFormStats, 0, newStats, 0, thirdFormStats.length);
                break;
            case "BASE":
                int j = 0;
                for (StatManager.Stat stat : StatManager.Stat.values()) {
                    float base = statsNBT.getFloat(stat.name());
                    newStats[j] = Math.min(base / 2f, StatManager.STAT_CAP);
                    j++;
                }
                break;
            case "100PERCENT":
                if (!tag.getBoolean(NBT_100PERCENT_UNLOCKED)) {
                    player.sendSystemMessage(Component.literal("You have not unlocked 100% power form yet!"));
                    return false;
                }
                if (!"BASE".equalsIgnoreCase(tag.getString(NBT_TRANSFORMATION))) {
                    player.sendSystemMessage(Component.literal("100% form can only be used from base form."));
                    return false;
                }
                j = 0;
                for (StatManager.Stat stat : StatManager.Stat.values()) {
                    newStats[j] = Math.min(statsNBT.getFloat(stat.name()), StatManager.STAT_CAP);
                    j++;
                }
                break;
            default:
                return false;
        }
        int k = 0;
        for (StatManager.Stat stat : StatManager.Stat.values()) {
            StatManager.setStat(player, stat, newStats[k]);
            k++;
        }
        tag.putString(NBT_TRANSFORMATION, form.toUpperCase());
        player.sendSystemMessage(Component.literal("Transformed to " + form + " form!"));
        return true;
    }

    public static void checkUnlock100Percent(Player player) {
        if (!isArcosian(player)) return;
        CompoundTag tag = player.getPersistentData();
        if (tag.getBoolean(NBT_100PERCENT_UNLOCKED)) return;
        long pl = PowerLevelCalculator.getPowerLevel(player);
        if (pl >= 100_000) {
            tag.putBoolean(NBT_100PERCENT_UNLOCKED, true);
            player.sendSystemMessage(Component.literal("You have unlocked your 100% power transformation!"));
        }
    }

    public static void handle100PercentDrain(Player player) {
        CompoundTag tag = player.getPersistentData();
        if (!isArcosian(player)) return;
        if (!"100PERCENT".equalsIgnoreCase(tag.getString(NBT_TRANSFORMATION))) return;
        float stamina = StatManager.getStamina(player);
        float drain = StatManager.getStaminaMax(player) * 0.02f / 20f;
        if (stamina <= drain) {
            switchTransformation(player, "BASE");
            player.sendSystemMessage(Component.literal("You are out of stamina! Reverting to base form."));
            return;
        }
        StatManager.setStamina(player, stamina - drain);
    }

    public static void onRevertKeyPressed(Player player) {
        CompoundTag tag = player.getPersistentData();
        if (!isArcosian(player)) return;
        if ("100PERCENT".equalsIgnoreCase(tag.getString(NBT_TRANSFORMATION))) {
            switchTransformation(player, "BASE");
            player.sendSystemMessage(Component.literal("Reverted to base form."));
        }
    }
}