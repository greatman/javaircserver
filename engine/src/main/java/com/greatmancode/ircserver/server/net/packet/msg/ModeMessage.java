package com.greatmancode.ircserver.server.net.packet.msg;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class ModeMessage implements Message {

    //Request
    public final String channel, flag;

    public ModeMessage(String channel) {
        this.channel = channel;
        flag = null;
    }

    public ModeMessage(String channel, String flag) {
        this.channel = channel;
        this.flag = flag;
    }

    public String getChannel() {
        return channel;
    }

    public String getFlag() {
        return flag;
    }
}
