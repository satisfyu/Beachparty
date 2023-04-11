package net.satisfyu.beachparty.util;

import net.minecraft.state.property.EnumProperty;


public class BeachpartyProperties {
    public static final EnumProperty<BeachpartyLineConnectingType> BEACHPARTY_LINE_CONNECTING_TYPE;

    static {
        BEACHPARTY_LINE_CONNECTING_TYPE = EnumProperty.of("type", BeachpartyLineConnectingType.class);
    }
}