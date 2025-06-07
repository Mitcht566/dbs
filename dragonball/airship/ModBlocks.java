package com.dragonball.airship;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.dragonball.planets.PlanetMod;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, PlanetMod.MOD_ID);

    public static final RegistryObject<Block> GPS_BLOCK =
        BLOCKS.register("gps_block", () -> new GPSBlock());
}
