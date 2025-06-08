package com.dragonball.common.attributes;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AttributeProvider extends CapabilityProvider implements ICapabilityProvider {

    @CapabilityInject(IAttributeStorage.class)
    public static Capability<IAttributeStorage> ATTRIBUTE_CAP = null;

    private final IAttributeStorage instance = new AttributeStorageImpl();
    private final LazyOptional<IAttributeStorage> optional = LazyOptional.of(() -> instance);

    public AttributeProvider() {
        super();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ATTRIBUTE_CAP) {
            return optional.cast();
        }
        return super.getCapability(cap, side);
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IAttributeStorage.class, new IAttributeStorage.Storage(), AttributeStorageImpl::new);
    }

    private static class AttributeStorageImpl implements IAttributeStorage {
        private int strength = 1;
        private int dexterity = 1;
        private int willpower = 1;

        @Override
        public int getStrength() {
            return strength;
        }

        @Override
        public void setStrength(int strength) {
            this.strength = Math.max(1, strength);
        }

        @Override
        public int getDexterity() {
            return dexterity;
        }

        @Override
        public void setDexterity(int dexterity) {
            this.dexterity = Math.max(1, dexterity);
        }

        @Override
        public int getWillpower() {
            return willpower;
        }

        @Override
        public void setWillpower(int willpower) {
            this.willpower = Math.max(1, willpower);
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putInt("Strength", strength);
            tag.putInt("Dexterity", dexterity);
            tag.putInt("Willpower", willpower);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.strength = nbt.getInt("Strength");
            this.dexterity = nbt.getInt("Dexterity");
            this.willpower = nbt.getInt("Willpower");
        }
    }
}
