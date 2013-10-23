package com.greatmancode.ircserver.server.net.packet.msg.responses;

import com.greatmancode.ircserver.api.net.interfaces.Message;

public class RPLNameReplyMessage implements Message {

    private final String[] clients;
    private final String channel;
    private final String nickname;
    public RPLNameReplyMessage(String nickname, String channel, String[] clients) {
        this.nickname = nickname;
        this.channel = channel;
        this.clients = clients;
    }

    public String getNickname() {
        return nickname;
    }

    public String getChannel() {
        return channel;
    }

    public String[] getClients() {
        return clients;
    }
}
