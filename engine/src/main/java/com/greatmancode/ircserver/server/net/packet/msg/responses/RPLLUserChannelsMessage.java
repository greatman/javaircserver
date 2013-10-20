package com.greatmancode.ircserver.server.net.packet.msg.responses;

public class RPLLUserChannelsMessage extends MessageUsername {

    private final int channelCount;
    public RPLLUserChannelsMessage(String nickname, int channelCount) {
        super(nickname);
        this.channelCount = channelCount;
    }

    public int getChannelCount() {
        return channelCount;
    }
}
