package com.dragonball.planets.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class RecruitmentWandItem extends Item {
    public RecruitmentWandItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (!player.level().isClientSide) {
            // Logic to recruit NPC using tokens
        }
        return InteractionResult.SUCCESS;
    }
}
