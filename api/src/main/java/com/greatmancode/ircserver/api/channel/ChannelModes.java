package com.greatmancode.ircserver.api.channel;

import java.util.HashMap;
import java.util.Map;

public enum ChannelModes {
    
    BAN("b"),
    HALFOP("h"),
    INVITE_ONLY("i"),
    INVITATION_MASK("I"),
    KEYLOCK("k"),
    LIMIT("l"),
    MODERATED("m"),
    NO_EXTERNAL_MESAGES("n"),
    OPERATOR("o"),
    CREATOR("O"),
    QUIET("q"),
    SECRET("s"),
    TOPIC_LOCK("t"),
    VOICE("v");

    private static final Map<String, ChannelModes> modeList = new HashMap<String, ChannelModes>();
    private String value;

    ChannelModes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static ChannelModes getValue(String letter) {
        return modeList.get(letter);
    }

    static {
        for (ChannelModes mode : ChannelModes.values()) {
            modeList.put(mode.getValue(), mode);
        }
    }

    public static String getConnectString() {
        String value = "";

        for (Map.Entry<String, ChannelModes> mode : modeList.entrySet()) {
            value += mode.getKey();
        }
        return value;
    }
}
