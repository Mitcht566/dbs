package com.dragonballz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

public class Shenron extends Mob {
    public Shenron(EntityType<? extends Shenron> type, Level level) {
        super(type, level);
    }

    @Override
    public InteractionResult interactAt(Player player, net.minecraft.world.phys.Vec3 pos, InteractionHand hand) {
        if (!level().isClientSide) {
            player.sendSystemMessage(Component.literal("Speak your wish!"));
            // Open wishes GUI here
        }
        return InteractionResult.sidedSuccess(level().isClientSide);
    }
}