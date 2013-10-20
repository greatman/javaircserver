package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLLUserOPMessage;

public class RPLLUserOPCodec extends MessageCodec<RPLLUserOPMessage> {
    public RPLLUserOPCodec() {
        super(RPLLUserOPMessage.class, "252");
    }

    public String encode(RPLLUserOPMessage message) {
        return message.getNickname() + " " + message.getOpCount() + " :operator(s) online";
    }
}
