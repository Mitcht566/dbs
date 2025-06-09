package com.dragonballz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

public class KingKaiNPC extends Mob {
    public KingKaiNPC(EntityType<? extends KingKaiNPC> type, Level level) {
        super(type, level);
    }

    @Override
    public InteractionResult interactAt(Player player, net.minecraft.world.phys.Vec3 pos, InteractionHand hand) {
        if (!level().isClientSide) {
            player.sendSystemMessage(Component.literal("Welcome to King Kai's planet! Train hard, learn Kaio-Ken!"));
            // Open GUI or check power level, unlock kaio-ken, and spawn dummies
        }
        return InteractionResult.sidedSuccess(level().isClientSide);
    }
}