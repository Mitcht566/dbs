package com.dragonball.planets.entity;

import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class PlanetEntitySpawner {
    public static ArmorStand spawnPlanet(ServerLevel universe7, BlockPos pos, ItemStack planetTextureItem) {
        ArmorStand stand = EntityType.ARMOR_STAND.create(universe7);
        if (stand != null) {
            stand.setInvisible(true);
            stand.setInvulnerable(true);
            stand.setMarker(true); // no hitbox
            stand.setNoGravity(true);
            stand.setNoBasePlate(true);
            stand.setShowArms(false);
            stand.setCustomNameVisible(true);
            stand.setCustomName(planetTextureItem.getHoverName());
            stand.setItemSlot(ArmorStand.Slot.HEAD, planetTextureItem); // planet texture as helmet
            stand.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            universe7.addFreshEntity(stand);
        }
        return stand;
    }
}
