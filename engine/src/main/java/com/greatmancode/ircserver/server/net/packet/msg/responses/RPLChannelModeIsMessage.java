package com.greatmancode.ircserver.server.net.packet.msg.responses;

public class RPLChannelModeIsMessage extends EndMessage {

    private final String modes;
    public RPLChannelModeIsMessage(String nickname, String channelName, String modes) {
        super(nickname, channelName);
        this.modes = modes;
    }

    public String getModes() {
        return modes;
    }
}
