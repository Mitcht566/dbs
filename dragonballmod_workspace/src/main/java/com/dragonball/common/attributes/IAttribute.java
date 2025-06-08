package com.dragonball.common.attributes;

public interface IAttribute {
    int getLevel();
    void setLevel(int level);
    void increaseLevel(int amount);
    void decreaseLevel(int amount);
}
