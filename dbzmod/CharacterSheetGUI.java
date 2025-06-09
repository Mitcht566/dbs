package com.dragonballz.client.gui;

import com.dragonballz.stats.StatManager;
import com.dragonballz.skills.KaioKenSkillHandler;
import com.dragonballz.util.PowerLevelCalculator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class CharacterSheetGUI extends Screen {
    private Player player;

    public CharacterSheetGUI() {
        super(Component.literal("Character Sheet"));
    }

    @Override
    protected void init() {
        player = Minecraft.getInstance().player;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        int x = this.width / 2 - 100;
        int y = 40;
        this.font.draw(guiGraphics, "Power Level: " + getDisplayedPL(), x, y, 0xFFFFFF);
        y += 16;
        for (StatManager.Stat stat : StatManager.Stat.values()) {
            float value = StatManager.getStat(player, stat);
            this.font.draw(guiGraphics, stat.name() + ": " + value, x, y, 0xFFFFFF);
            y += 12;
        }
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    private String getDisplayedPL() {
        float basePL = PowerLevelCalculator.getPowerLevel(player);
        if (KaioKenSkillHandler.isKaioKenActive(player)) {
            return String.valueOf((int)(basePL * KaioKenSkillHandler.getKaioKenMultiplier(player)));
        }
        return String.valueOf((int)basePL);
    }
}