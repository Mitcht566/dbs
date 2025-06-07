package com.dragonball.airship;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs; // Correct import for new tabs
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.dragonball.planets.PlanetMod;
import net.minecraft.resources.ResourceKey; // Needed for ResourceKey

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, PlanetMod.MOD_ID);

    public static final RegistryObject<Item> AIRSHIP_SPAWNER =
        // Removed .tab() method as it's deprecated/changed in 1.20.1+.
        // Items are now added to creative tabs during CreativeModeTab registration.
        ITEMS.register("airship_spawner", () -> new AirshipSpawnerItem(new Item.Properties().stacksTo(1)));
}
