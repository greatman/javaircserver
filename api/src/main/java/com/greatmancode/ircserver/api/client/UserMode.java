package com.greatmancode.ircserver.api.client;

import java.util.HashMap;
import java.util.Map;

public enum UserMode {

    AWAY("a"),
    INVISIBLE("i"),
    WALLOPS("w"),
    RESTRICTED("r"),
    OPERATOR("o"),
    LOCAL_OPERATOR("O"),
    SERVER_NOTICE("s");

    private static final Map<String, UserMode> modeList = new HashMap<String, UserMode>();
    private String value;

    UserMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static UserMode getValue(String letter) {
       return modeList.get(letter);
    }

    static {
        for (UserMode mode : UserMode.values()) {
            modeList.put(mode.getValue(), mode);
        }
    }

}
