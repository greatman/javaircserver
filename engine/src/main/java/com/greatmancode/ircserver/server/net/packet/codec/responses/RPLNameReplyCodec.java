package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLNameReplyMessage;

public class RPLNameReplyCodec extends MessageCodec<RPLNameReplyMessage>{

    public RPLNameReplyCodec() {
        super(RPLNameReplyMessage.class, "353");
    }

    public String encode(RPLNameReplyMessage message) {
        String result = message.getNickname() + " = " + message.getChannel() + " :";
        for (String client : message.getClients()) {
            result += client + " ";
        }
        return result;
    }
}
