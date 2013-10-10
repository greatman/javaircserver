package com.greatmancode.ircserver.server.net.packet.msg;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class NickMessage implements Message {

    private final String nickname;
    public NickMessage(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public String toString() {
        return nickname;
    }

    public boolean equals(NickMessage object) {
        return object.getNickname().equals(getNickname());
    }
}
