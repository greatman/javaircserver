package com.greatmancode.ircserver.api.net.interfaces;

import java.io.IOException;

public abstract class MessageCodec<T extends Message> {
    private final Class<T> clazz;
    private boolean dynamic;
    private String opcode;

    public MessageCodec(Class<T> clazz, String opcode) {
        this.clazz = clazz;
        this.opcode = opcode;
    }

    public final Class<T> getType() {
        return clazz;
    }

    public final String getOpcode() {
        return opcode;
    }

    void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String encode(T message) throws IOException {
        return null;
    }

    public T decode(String[] buffer) throws IOException {
        return null;
    }
}
