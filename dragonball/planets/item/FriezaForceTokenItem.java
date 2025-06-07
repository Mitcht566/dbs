package com.dragonball.planets.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs; // Correct import for new tabs
import net.minecraft.resources.ResourceKey; // Needed for ResourceKey

public class FriezaForceTokenItem extends Item {
    public FriezaForceTokenItem() {
        // Removed .tab() method as it's deprecated/changed in 1.20.1+.
        // Items are now added to creative tabs during CreativeModeTab registration.
        super(new Properties().stacksTo(64));
    }
}
