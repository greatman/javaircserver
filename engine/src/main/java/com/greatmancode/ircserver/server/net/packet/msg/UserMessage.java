package com.greatmancode.ircserver.server.net.packet.msg;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class UserMessage implements Message {

    private final String username, unused, realName;
    private final int mode;
    public UserMessage(String username, int mode, String unused, String realName) {
        this.username = username;
        this.mode = mode;
        this.unused = unused;
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public int getMode() {
        return mode;
    }

    public String getRealName() {
        return realName;
    }

    public String getUnused() {
        return unused;
    }
}
