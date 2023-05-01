package satisfyu.beachparty.util;

import net.minecraft.util.StringRepresentable;


/**
* Evtl. auch BeachpartyLineConnectingType möglich?
**/

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