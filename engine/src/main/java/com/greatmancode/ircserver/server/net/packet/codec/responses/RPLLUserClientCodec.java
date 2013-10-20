package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLLUserClientMessage;

public class RPLLUserClientCodec extends MessageCodec<RPLLUserClientMessage> {

    public RPLLUserClientCodec() {
        super(RPLLUserClientMessage.class, "251");
    }

    public String encode(RPLLUserClientMessage message) {
        return message.getUsername() + " :There are" + message.getUserCount() + " users and " + message.getServiceCount() + " services on " + message.getServerCount() + " servers";
    }
}
