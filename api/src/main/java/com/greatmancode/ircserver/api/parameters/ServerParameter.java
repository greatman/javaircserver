package com.greatmancode.ircserver.api.parameters;

public class ServerParameter {

    private final String name;
    private String value;

    public ServerParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
