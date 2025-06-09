package com.dragonballz.core;

import com.dragonballz.client.anim.KiAuraEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import net.minecraft.resources.ResourceLocation;

public class PlayerRendererAuraHook {
    public static void registerPlayerAura() {
        Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().forEach((type, renderer) -> {
            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new KiAuraEffect(playerRenderer, new AuraGeoModel()));
            }
        });
    }
}

class AuraGeoModel extends AnimatedGeoModel {
    @Override
    public ResourceLocation getModelResource(Object entity) {
        return new ResourceLocation("dragonballz", "geo/aura.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(Object entity) {
        return new ResourceLocation("dragonballz", "textures/entity/aura.png");
    }
    @Override
    public ResourceLocation getAnimationResource(Object entity) {
        return new ResourceLocation("dragonballz", "animations/aura.animation.json");
    }
}