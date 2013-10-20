package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLLUserMeMessage;

public class RPLLUserMeCodec extends MessageCodec<RPLLUserMeMessage>{
    public RPLLUserMeCodec() {
        super(RPLLUserMeMessage.class, "255");
    }

    public String encode(RPLLUserMeMessage message) {
        return message.getNickname() + " :I have " + message.getClientCount() + " clients and " + message.getServerCount() + " servers";
    }
}
