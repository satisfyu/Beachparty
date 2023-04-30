package satisfyu.beachparty.util;

import net.minecraft.world.level.block.state.properties.EnumProperty;


public class BeachpartyProperties {
    public static final EnumProperty<BeachpartyLineConnectingType> BEACHPARTY_LINE_CONNECTING_TYPE;

    static {
        BEACHPARTY_LINE_CONNECTING_TYPE = EnumProperty.create("type", BeachpartyLineConnectingType.class);
    }
}