package net.satisfyu.beachparty.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum HammockPart implements StringIdentifiable {
    RIGHT("right"),
    MIDDLE("middle"),
    LEFT("left");

    private final String name;

    HammockPart(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
