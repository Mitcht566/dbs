package com.dragonball.airship;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

public class GPSMenuProvider implements MenuProvider {
    private final BlockPos pos;

    public GPSMenuProvider(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("GPS Controller");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }
}
