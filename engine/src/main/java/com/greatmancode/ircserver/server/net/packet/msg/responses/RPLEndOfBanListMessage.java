package com.greatmancode.ircserver.server.net.packet.msg.responses;

public class RPLEndOfBanListMessage extends EndMessage {

    public RPLEndOfBanListMessage(String nickname, String channelName) {
        super(nickname, channelName);
    }
}
