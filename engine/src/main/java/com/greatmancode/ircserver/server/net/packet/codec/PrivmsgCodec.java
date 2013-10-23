package com.greatmancode.ircserver.server.net.packet.codec;

import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;
import com.greatmancode.ircserver.server.net.packet.msg.PrivmsgMessage;

public class PrivmsgCodec extends MessageCodec<PrivmsgMessage> {

    public PrivmsgCodec() {
        super(PrivmsgMessage.class, "PRIVMSG");
    }

    public PrivmsgMessage decode(String[] args) {
        return new PrivmsgMessage(args[0], args[1]);
    }

    public String encode(PrivmsgMessage message) {
        return message.getChannel() + " :" + message.getMessage();
    }


}
