package com.dragonball.airship;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import com.dragonball.planets.PlanetMod;

public class StructureLoader {
    public static void load(ServerLevel level, BlockPos pos, String structureName) {
        StructureTemplateManager manager = level.getStructureManager();
        ResourceLocation location = new ResourceLocation(PlanetMod.MOD_ID + ":structures/" + structureName);
        StructureTemplate template = manager.get(location).orElse(null);

        if (template != null) {
            template.placeInWorld(level, pos, pos,
                new StructurePlaceSettings(),
                level.random, 2);
        }
    }
}
