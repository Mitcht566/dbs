package com.dragonball.init;

import com.dragonball.items.ScouterHelmetItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, "dragonballmod");

    public static final RegistryObject<Item> SCOUTER_HELMET = ITEMS.register(
        "scouter_helmet",
        ScouterHelmetItem::new
    );
}
