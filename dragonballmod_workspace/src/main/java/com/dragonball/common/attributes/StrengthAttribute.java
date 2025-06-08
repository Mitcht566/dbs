package com.dragonball.common.attributes;

public class StrengthAttribute implements IAttribute {
    private int level = 1;

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = Math.max(1, level);
    }

    @Override
    public void increaseLevel(int amount) {
        this.level += amount;
        if (this.level < 1) this.level = 1;
    }

    @Override
    public void decreaseLevel(int amount) {
        this.level -= amount;
        if (this.level < 1) this.level = 1;
    }
}
