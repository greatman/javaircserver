package com.greatmancode.ircserver.api.client;

public enum UserMode {

    AWAY("a"),
    INVISIBLE("i"),
    WALLOPS("w"),
    RESTRICTED("r"),
    OPERATOR("o"),
    LOCAL_OPERATOR("O"),
    SERVER_NOTICE("s");

    private String value;
    public UserMode(String value) {
        this.value = value;
    }

}
