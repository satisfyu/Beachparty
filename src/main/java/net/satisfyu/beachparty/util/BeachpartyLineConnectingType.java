package net.satisfyu.beachparty.util;

import net.minecraft.util.StringIdentifiable;

public enum BeachpartyLineConnectingType implements StringIdentifiable {
    NONE("none"),
    MIDDLE("middle"),
    LEFT("left"),
    RIGHT("right");

    private final String name;

    private BeachpartyLineConnectingType(String type) {
        this.name = type;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
