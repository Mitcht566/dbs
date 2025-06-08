package com.dragonball.common.attributes;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public interface IAttributeStorage extends net.minecraftforge.common.capabilities.ICapabilityProvider {

    int getStrength();
    void setStrength(int strength);

    int getDexterity();
    void setDexterity(int dexterity);

    int getWillpower();
    void setWillpower(int willpower);

    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag nbt);

    class Storage implements IStorage<IAttributeStorage> {

        @Override
        public CompoundTag writeNBT(net.minecraftforge.common.capabilities.Capability<IAttributeStorage> capability, IAttributeStorage instance, net.minecraft.core.Direction side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(net.minecraftforge.common.capabilities.Capability<IAttributeStorage> capability, IAttributeStorage instance, net.minecraft.core.Direction side, net.minecraft.nbt.Tag nbt) {
            instance.deserializeNBT((CompoundTag) nbt);
        }
    }
}
