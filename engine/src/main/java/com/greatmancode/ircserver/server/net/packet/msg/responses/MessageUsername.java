package com.greatmancode.ircserver.server.net.packet.msg.responses;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public abstract class MessageUsername implements Message {

    private final String nickname;

    public MessageUsername(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
