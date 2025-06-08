package com.dragonball.common.race;

import com.dragonball.common.attributes.IAttributeStorage;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RaceAttributeHandler {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        RaceType race = getPlayerRace(player);

        player.getCapability(com.dragonball.common.attributes.AttributeProvider.ATTRIBUTE_CAP).ifPresent(attr -> {
            switch (race) {
                case SAIYAN:
                    attr.setStrength(attr.getStrength() + 5);
                    attr.setDexterity(attr.getDexterity() + 3);
                    break;
                case NAMEKIAN:
                    attr.setWillpower(attr.getWillpower() + 5);
                    break;
                case EARTHLING:
                    attr.setStrength(attr.getStrength() + 2);
                    attr.setDexterity(attr.getDexterity() + 2);
                    attr.setWillpower(attr.getWillpower() + 2);
                    break;
                default:
                    break;
            }
        });
    }

    private static RaceType getPlayerRace(Player player) {
        // Placeholder: implement actual race retrieval from player capability or data
        return RaceType.EARTHLING;
    }
}
