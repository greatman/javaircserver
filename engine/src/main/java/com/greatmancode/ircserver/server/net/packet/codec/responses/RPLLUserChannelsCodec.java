package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLLUserChannelsMessage;

public class RPLLUserChannelsCodec extends MessageCodec<RPLLUserChannelsMessage>{

    public RPLLUserChannelsCodec() {
        super(RPLLUserChannelsMessage.class, "254");
    }

    public String encode(RPLLUserChannelsMessage message) {
        return message.getNickname() + " " + message.getChannelCount() + " :channels formed";
    }
}
