package com.dragonball.airship;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab; // Still needed for class reference
import net.minecraft.world.item.CreativeModeTabs; // Correct import for new tabs
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import com.dragonball.planets.PlanetMod;
import net.minecraft.resources.ResourceKey; // Needed for ResourceKey

public class AirshipSpawnerItem extends Item {
    public AirshipSpawnerItem(Properties properties) {
        // Removed .tab() method as it's deprecated/changed in 1.20.1+.
        // Items are now added to creative tabs during CreativeModeTab registration.
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.FAIL;

        BlockPos pos = context.getClickedPos().above();
        StructureLoader.load(serverLevel, pos, "frieza_airship");

        return InteractionResult.SUCCESS;
    }
}
