package com.dragonballz.client.anim;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import net.minecraft.client.renderer.MultiBufferSource;
import com.mojang.blaze3d.vertex.PoseStack;

public class KiAuraEffect extends GeoLayerRenderer<LivingEntity & IAnimatable> {
    private final AnimatedGeoModel<LivingEntity & IAnimatable> auraModel;

    public KiAuraEffect(GeoEntityRenderer<LivingEntity & IAnimatable> entityRenderer, AnimatedGeoModel<LivingEntity & IAnimatable> auraModel) {
        super(entityRenderer);
        this.auraModel = auraModel;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                      LivingEntity & IAnimatable entity, float limbSwing, float limbSwingAmount,
                      float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (KiAuraIntegration.hasAura(entity)) {
            this.auraModel.setLivingAnimations(entity, 0, 0, new AnimationEvent<>(entity, limbSwing, limbSwingAmount, partialTicks, false, null));
            renderColoredCutoutModel(this.auraModel, this.auraModel.getModelResource(entity), bufferSource, entity, 1.0f, 1.0f, 1.0f, 0.8f, packedLight, getPackedOverlay(entity, 0.0F));
        }
    }
}