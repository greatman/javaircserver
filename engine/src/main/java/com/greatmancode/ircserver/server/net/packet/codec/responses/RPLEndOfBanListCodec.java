package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLEndOfBanListMessage;

public class RPLEndOfBanListCodec extends MessageCodec<RPLEndOfBanListMessage> {

    public RPLEndOfBanListCodec() {
        super(RPLEndOfBanListMessage.class, "368");
    }

    public String encode(RPLEndOfBanListMessage message) {
        return message.getNickname() + " " + message.getChannelName() + " :End of channel ban list";
    }
}
