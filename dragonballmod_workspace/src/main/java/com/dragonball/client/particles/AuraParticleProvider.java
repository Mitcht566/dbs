package com.dragonball.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleProvider;
import net.minecraft.core.particles.SpriteSet;

public class AuraParticleProvider implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet sprites;

    public AuraParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }

    @Override
    public ParticleOptions createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z,
                                          double xSpeed, double ySpeed, double zSpeed) {
        return new AuraParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
    }
}
