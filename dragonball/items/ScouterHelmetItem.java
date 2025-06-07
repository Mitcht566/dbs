package com.dragonball.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.loading.FMLEnvironment;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Comparator;

public class ScouterHelmetItem extends ArmorItem {
    public ScouterHelmetItem() {
        super(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1));
    }
}

// Client-side event handler (register in your mod initializer)
@Mod.EventBusSubscriber(modid = "dragonballmod", value = Dist.CLIENT)
class ScouterClientEvents {
    public static final KeyMapping SCAN_CROSSHAIR = new KeyMapping("key.dragonballmod.scouter_scan", GLFW.GLFW_KEY_KP_1, "key.categories.dragonballmod");
    public static final KeyMapping SCAN_DISTANT = new KeyMapping("key.dragonballmod.scouter_find", GLFW.GLFW_KEY_KP_2, "key.categories.dragonballmod");
    private static PowerLevelDisplay lastPowerDisplay = null;
    private static long lastScanTime = 0;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(SCAN_CROSSHAIR);
        event.register(SCAN_DISTANT);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (FMLEnvironment.dist != Dist.CLIENT) return;
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || !hasScouterHelmet(player)) return;

        if (SCAN_CROSSHAIR.consumeClick()) {
            LivingEntity mob = getLookedAtMob(player, 30.0D);
            if (mob != null) {
                double pl = computePowerLevel(mob);
                playScanSound(player);
                displayPowerLevel(mob, pl, player, false, 0);
            }
        } else if (SCAN_DISTANT.consumeClick()) {
            LivingEntity mob = findHighestPowerMob(player, 4096.0D);
            if (mob != null) {
                double pl = computePowerLevel(mob);
                double dist = player.distanceTo(mob);
                playTargetLockSound(player);
                displayPowerLevel(mob, pl, player, true, dist);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        if (lastPowerDisplay == null) return;
        Minecraft mc = Minecraft.getInstance();
        Window win = mc.getWindow();
        int x = win.getGuiScaledWidth() / 2;
        int y = win.getGuiScaledHeight() - 60;
        String txt = lastPowerDisplay.getDisplayLine();
        event.getGuiGraphics().drawString(mc.font, txt, x - mc.font.width(txt) / 2, y, ChatFormatting.GREEN.getColor(), true);

        // Fade out after 2.5 seconds
        if (System.currentTimeMillis() - lastScanTime > 2500) {
            lastPowerDisplay = null;
        }
    }

    // Utility - check if player wears the Scouter helmet
    private static boolean hasScouterHelmet(Player player) {
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        return helmet != null && helmet.getItem() instanceof ScouterHelmetItem;
    }

    // Raytrace for mob under crosshair
    private static LivingEntity getLookedAtMob(Player player, double dist) {
        Minecraft mc = Minecraft.getInstance();
        HitResult hit = mc.hitResult;
        if (hit instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity le) {
            return le;
        }
        return null;
    }

    // Find highest-power mob in big radius
    private static LivingEntity findHighestPowerMob(Player player, double range) {
        List<LivingEntity> mobs = player.level().getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(range),
                e -> e != player && e.isAlive());
        return mobs.stream()
                .max(Comparator.comparingDouble(ScouterClientEvents::computePowerLevel))
                .orElse(null);
    }

    // Formula: (max_health * 10) + max_strength
    private static double computePowerLevel(LivingEntity mob) {
        double health = mob.getAttributeBaseValue(Attributes.MAX_HEALTH);
        double str = mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
        if (Double.isNaN(str)) str = 0.0;
        return (health * 10.0) + str;
    }

    // Show on HUD
    private static void displayPowerLevel(LivingEntity mob, double pl, Player player, boolean showDist, double dist) {
        String name = mob.getName().getString();
        String line = showDist
                ? "Power Level (" + name + "): " + (int)pl + " - Distance: " + (int)dist + "m"
                : "Power Level (" + name + "): " + (int)pl;
        lastPowerDisplay = new PowerLevelDisplay(line);
        lastScanTime = System.currentTimeMillis();
    }

    private static void playScanSound(Player player) {
        player.level().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_PLING.get(), SoundSource.PLAYERS, 1.0f, 1.1f);
    }
    private static void playTargetLockSound(Player player) {
        player.level().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.NOTE_BLOCK_BELL.get(), SoundSource.PLAYERS, 1.0f, 0.7f);
    }

    // Stores last scan result for HUD
    private static class PowerLevelDisplay {
        private final String displayLine;
        public PowerLevelDisplay(String s) { displayLine = s; }
        public String getDisplayLine() { return displayLine; }
    }
}
