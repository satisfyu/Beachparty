package net.satisfyu.beachparty.block.enums;

import net.minecraft.util.StringRepresentable;

public enum HammockPart implements StringRepresentable {
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
    public String getSerializedName() {
        return this.name;
    }
}
