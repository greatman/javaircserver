package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLEndOfNamesMessage;

public class RPLEndOfNamesCodec extends MessageCodec<RPLEndOfNamesMessage> {

    public RPLEndOfNamesCodec() {
        super(RPLEndOfNamesMessage.class, "366");
    }

    public String encode(RPLEndOfNamesMessage message) {
        return message.getChannelName() + " :End of NAMES list";
    }
}
