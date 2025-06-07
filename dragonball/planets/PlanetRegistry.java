package com.dragonball.planets;

import com.dragonball.airship.AirshipEntity;
import com.dragonball.planets.item.FriezaForceTokenItem;
import com.dragonball.planets.item.RecruitmentWandItem;
import com.dragonball.planets.mob.PlanetMobEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PlanetRegistry {
    // DeferredRegister for Items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PlanetMod.MOD_ID);

    // Register planet-related items
    public static final RegistryObject<Item> FRIEZA_FORCE_TOKEN_ITEM = ITEMS.register("frieza_force_token",
            () -> new FriezaForceTokenItem());

    public static final RegistryObject<Item> RECRUITMENT_WAND_ITEM = ITEMS.register("recruitment_wand",
            () -> new RecruitmentWandItem());

    // DeferredRegister for EntityTypes
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PlanetMod.MOD_ID);

    // Register the AirshipEntity
    public static final RegistryObject<EntityType<AirshipEntity>> AIRSHIP_ENTITY =
            ENTITY_TYPES.register("airship",
                    () -> EntityType.Builder.<AirshipEntity>of(AirshipEntity::new, MobCategory.MISC)
                            .sized(5.0F, 5.0F)
                            .clientTrackingRange(10)
                            .build(PlanetMod.MOD_ID + ":airship"));

    // Example for PlanetMobEntity (replace with actual mob registration if needed)
    public static final RegistryObject<EntityType<PlanetMobEntity>> EXAMPLE_PLANET_MOB_ENTITY =
            ENTITY_TYPES.register("example_planet_mob",
                    () -> EntityType.Builder.of(PlanetMobEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build(PlanetMod.MOD_ID + ":example_planet_mob"));


    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
        ENTITY_TYPES.register(eventBus);
        // Removed ModBlocks.init(eventBus) and ModItems.init(eventBus) as they are registered in PlanetMod.java
    }
}
