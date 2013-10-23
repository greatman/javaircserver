package com.greatmancode.ircserver.server.net.packet.msg;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class PrivmsgMessage extends MessageHeaderRepresentation {

    private final String channel, message;

    public PrivmsgMessage(String channel, String message) {
        super(null);
        this.channel = channel;
        this.message = message;
    }

    public PrivmsgMessage(String representation, String channel, String message) {
        super(representation);
        this.channel = channel;
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }
}
