package com.greatmancode.ircserver.server.net.packet.codec;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.NickMessage;

public class NickCodec extends MessageCodec<NickMessage>{

    public NickCodec() {
        super(NickMessage.class, "NICK");
    }

    @Override
    public NickMessage decode(String[] message) {
        return new NickMessage(message[0]);
    }

    @Override
    public String encode(NickMessage message) {
        return message.getNickname();
    }
}
