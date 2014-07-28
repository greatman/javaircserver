package com.greatmancode.ircserver.server.net.packet.codec.responses;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.responses.RPLNicknameInUseMessage;

import java.io.IOException;

public class RPLNicknameInUseCodec extends MessageCodec<RPLNicknameInUseMessage> {

    public RPLNicknameInUseCodec() {
        super(RPLNicknameInUseMessage.class, "433");
    }

    public String encode(RPLNicknameInUseMessage message) throws IOException {
        return message.getCurrentNick() + " " + message.getNickname() + " :Nickname is already in use.";
    }
}
