package com.dragonball.planets.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Mob;
import com.dragonball.planets.PlanetsMod;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlanetMobRenderer<T extends Mob & PlanetMobRenderer.IPlanetMob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    // List of all known planet names (order determines their ID)
    private static final List<String> PLANET_NAMES = Arrays.asList(
        "earth", "mars", "venus", "jupiter", "saturn", "uranus", "neptune", "pluto"
        // Add any additional planet names here, in the order you want
    );

    // Build the skin list (assumes skin files are named skin0.png, skin1.png, ...)
    private static final List<ResourceLocation> SKINS = IntStream.range(0, PLANET_NAMES.size())
            .mapToObj(i -> new ResourceLocation(PlanetsMod.MODID, "textures/entity/planetmob/skin" + i + ".png"))
            .collect(Collectors.toList());

    public PlanetMobRenderer(EntityRendererProvider.Context context, M model, float shadowSize) {
        super(context, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        int planetId = getPlanetIdForEntity(entity);
        if (planetId >= 0 && planetId < SKINS.size()) {
            return SKINS.get(planetId);
        } else {
            // Optionally, you can log a warning here if an unknown planet is used
            return SKINS.get(0);
        }
    }

    /**
     * Maps each mob to its planet's index/ID.
     * Assumes your Mob implements IPlanetMob.
     */
    private int getPlanetIdForEntity(T entity) {
        String planetName = entity.getPlanetName();
        int idx = PLANET_NAMES.indexOf(planetName.toLowerCase());
        return idx;
    }

    // Interface for planet-aware mobs. Implement this in your mob entity!
    public interface IPlanetMob {
        String getPlanetName();
    }
}