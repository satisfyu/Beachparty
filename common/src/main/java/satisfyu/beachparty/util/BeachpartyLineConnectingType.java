package satisfyu.beachparty.util;

import net.minecraft.util.StringRepresentable;

public enum BeachpartyLineConnectingType implements StringRepresentable {
    NONE("none"),
    MIDDLE("middle"),
    LEFT("left"),
    RIGHT("right");

    private final String name;

    private BeachpartyLineConnectingType(String type) {
        this.name = type;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
